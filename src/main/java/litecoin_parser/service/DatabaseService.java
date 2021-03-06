package litecoin_parser.service;

import litecoin_parser.database.*;
import litecoin_parser.model.RawBlock;
import litecoin_parser.model.RawTransaction;
import litecoin_parser.model.TransactionInput;
import litecoin_parser.model.TransactionOutput;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.nonNull;

public class DatabaseService {

    private final EntityManager entityManager;
    private final MessageMapper messageMapper;

    public DatabaseService() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("litecoin_parser");
        entityManager = entityManagerFactory.createEntityManager();

        this.messageMapper = new MessageMapper();
    }

    public void saveBlock(@Nonnull RawBlock rawBlock) {
        entityManager.getTransaction().begin();

        Block block = messageMapper.mapRawBlockToDatabase(rawBlock);
        entityManager.persist(block);

        List<RawTransaction> rawTransactions = rawBlock.getTransactions();
        rawTransactions.forEach(rawTransaction -> {
            Transaction transaction = messageMapper.mapRawTransactionToDatabase(rawTransaction, rawBlock.getHash());
            entityManager.persist(transaction);

            List<TransactionInput> transactionInputs = rawTransaction.getInputs();
            transactionInputs.forEach(transactionInput -> saveTransactionInput(transactionInput, rawTransaction.getId()));

            List<TransactionOutput> transactionOutputs = rawTransaction.getOutputs();
            transactionOutputs.forEach(transactionOutput -> saveTransactionOutput(transactionOutput, rawTransaction.getId()));
        });

        entityManager.getTransaction().commit();
    }

    public void updateSpentOutputInformation(@Nonnull String transactionId, int outputNumber, boolean isSpent) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("UPDATE LTC$TX_OUTPUTS SET IS_SPENT = :isSpent " +
                "WHERE TX_ID = :transactionId AND OUTPUT_NUMBER = :outputNumber");
        query.setParameter("isSpent", isSpent);
        query.setParameter("transactionId", transactionId);
        query.setParameter("outputNumber", outputNumber);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    public void updateInputAmountInformation(@Nonnull String transactionId,
                                             @Nonnull String transactionInputId,
                                             int vout,
                                             double amount) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery("UPDATE LTC$TX_INPUTS SET IN_VALUE = :amount " +
                "WHERE TX_ID = :transactionId AND TX_INPUT_ID = :transactionInputId AND VOUT = :vout");
        query.setParameter("amount", amount);
        query.setParameter("transactionId", transactionId);
        query.setParameter("transactionInputId", transactionInputId);
        query.setParameter("vout", vout);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    private void saveTransactionInput(@Nonnull TransactionInput transactionInput, @Nonnull String transactionId) {
        if (nonNull(transactionInput.getCoinbase())) {
            TxCoinbaseInput txCoinbaseInput = messageMapper.mapTransactionCoinbaseInputToDatabase(
                    transactionInput, transactionId);
            entityManager.persist(txCoinbaseInput);
            return;
        }

        TxInput txInput = messageMapper.mapTransactionInputToDatabase(transactionInput, transactionId);
        entityManager.persist(txInput);

        List<String> witnessData = transactionInput.getWitness();
        if (nonNull(witnessData)) {
            witnessData.forEach(witness -> {
                TxInputWitness txInputWitness = messageMapper.mapWitnessDataToDatabase(
                        witness, transactionId, transactionInput);
                entityManager.persist(txInputWitness);
            });
        }
    }

    private void saveTransactionOutput(@Nonnull TransactionOutput transactionOutput, @Nonnull String transactionId) {
        TxOutput txOutput = messageMapper.mapTransactionOutputToDatabase(transactionOutput, transactionId);
        entityManager.persist(txOutput);

        List<String> addresses = transactionOutput.getScriptPubKey().getAddresses();
        if (nonNull(addresses)) {
            addresses.forEach(address -> {
                Address databaseAddress = messageMapper.mapAddressToDatabase(address);
                entityManager.merge(databaseAddress);

                TxOutAddress txOutAddress = messageMapper.createTxOutAddress(
                        address, transactionId, transactionOutput.getOutputNumber());
                entityManager.persist(txOutAddress);
            });
        }
    }
}
