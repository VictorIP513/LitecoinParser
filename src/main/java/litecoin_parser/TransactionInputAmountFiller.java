package litecoin_parser;

import litecoin_parser.model.RawBlock;
import litecoin_parser.model.RawTransaction;
import litecoin_parser.model.TransactionInput;
import litecoin_parser.service.DatabaseService;
import litecoin_parser.service.LitecoinCli;
import litecoin_parser.service.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.Utils;
import org.bitcoinj.params.TestNet3Params;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
public class TransactionInputAmountFiller {

    private static final LitecoinCli litecoinCli = new LitecoinCli();
    private static final MessageMapper messageMapper = new MessageMapper();
    private static final DatabaseService databaseService = new DatabaseService();

    public static void main(String[] args) throws IOException {
        String blockHash = "d144e3f3d5c7321472a67685ba931804f453e09d89f95fdcfa75881107626e09";

        for (int i = 0; i < 1000; ++i) {
            log.info("{}/1000: {}", i, blockHash);

            String rawBlockJson = litecoinCli.getRawBlockJson(blockHash);
            RawBlock rawBlock = messageMapper.parseRawBlockJson(rawBlockJson);
            processTransactions(rawBlock.getTransactions());

            blockHash = rawBlock.getNextBlockHash();
        }
    }

    private static void processTransactions(@Nonnull List<RawTransaction> transactions) throws IOException {
        for (RawTransaction transaction : transactions) {
            String transactionId = transaction.getId();

            List<TransactionInput> transactionInputs = transaction.getInputs();
            for (TransactionInput transactionInput : transactionInputs) {
                if (nonNull(transactionInput.getCoinbase())) {
                    continue;
                }

                String hexInputTransaction = litecoinCli.getRawTransaction(transactionInput.getTransactionId());
                byte[] rawTransactionBytes = Utils.HEX.decode(hexInputTransaction);

                // get input amount with bitcoinj
                Transaction bitcoinjInputTransaction = new Transaction(TestNet3Params.get(), rawTransactionBytes);
                TransactionOutput transactionOutput = bitcoinjInputTransaction.getOutput(transactionInput.getVout());
                double amount = Double.parseDouble(transactionOutput.getValue().toPlainString());

                databaseService.updateInputAmountInformation(transactionId, transactionInput.getTransactionId(),
                        transactionInput.getVout(), amount);
            }
        }
    }
}
