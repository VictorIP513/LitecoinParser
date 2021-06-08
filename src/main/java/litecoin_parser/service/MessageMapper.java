package litecoin_parser.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import litecoin_parser.database.*;
import litecoin_parser.model.RawBlock;
import litecoin_parser.model.RawTransaction;
import litecoin_parser.model.TransactionInput;
import litecoin_parser.model.TransactionOutput;

import javax.annotation.Nonnull;

public class MessageMapper {

    private final ObjectMapper objectMapper;

    public MessageMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Nonnull
    public RawBlock parseRawBlockJson(@Nonnull String json) throws JsonProcessingException {
        return objectMapper.readValue(json, RawBlock.class);
    }

    @Nonnull
    public Block mapRawBlockToDatabase(@Nonnull RawBlock rawBlock) {
        return new Block()
                .setHash(rawBlock.getHash())
                .setCountConfirmations(rawBlock.getCountConfirmations())
                .setStrippedSize(rawBlock.getStrippedSize())
                .setBlockSize(rawBlock.getSize())
                .setWeight(rawBlock.getWeight())
                .setHeight(rawBlock.getHeight())
                .setVersion(rawBlock.getVersion())
                .setVersionHex(rawBlock.getVersionHex())
                .setMerkleRoot(rawBlock.getMerkleRoot())
                .setBlockTime(rawBlock.getTime())
                .setMedianTime(rawBlock.getMedianTime())
                .setNonce(rawBlock.getNonce())
                .setBits(rawBlock.getBits())
                .setDifficulty(rawBlock.getDifficulty())
                .setChainWork(rawBlock.getChainWork())
                .setTransactionCount(rawBlock.getTransactionsCount())
                .setPreviousBlockHash(rawBlock.getPreviousBlockHash())
                .setNextBlockHash(rawBlock.getNextBlockHash());
    }

    @Nonnull
    public Transaction mapRawTransactionToDatabase(@Nonnull RawTransaction rawTransaction, @Nonnull String blockHash) {
        return new Transaction()
                .setTxId(rawTransaction.getId())
                .setTxHash(rawTransaction.getHash())
                .setBlockHash(blockHash)
                .setTxVersion(rawTransaction.getVersion())
                .setTxSize(rawTransaction.getSize())
                .setTxVSize(rawTransaction.getVsize())
                .setWeight(rawTransaction.getWeight())
                .setLockTime(rawTransaction.getLockTime());
    }

    @Nonnull
    public TxInput mapTransactionInputToDatabase(@Nonnull TransactionInput transactionInput,
                                                 @Nonnull String transactionId) {
        return (TxInput) new TxInput()
                .setScriptSigAsm(transactionInput.getScriptSig().getAsm())
                .setScriptSigHex(transactionInput.getScriptSig().getHex())
                .setSequenceNumber(transactionInput.getSequenceNumber())
                .setTxId(transactionId)
                .setTransactionInputId(transactionInput.getTransactionId())
                .setVout(transactionInput.getVout());
    }

    @Nonnull
    public TxCoinbaseInput mapTransactionCoinbaseInputToDatabase(@Nonnull TransactionInput transactionInput,
                                                                 @Nonnull String transactionId) {
        return new TxCoinbaseInput()
                .setTxId(transactionId)
                .setCoinbase(transactionInput.getCoinbase())
                .setSequenceNumber(transactionInput.getSequenceNumber());
    }

    @Nonnull
    public TxOutput mapTransactionOutputToDatabase(@Nonnull TransactionOutput transactionOutput,
                                                   @Nonnull String transactionId) {
        return (TxOutput) new TxOutput()
                .setOutValue(transactionOutput.getValue())
                .setScriptAsm(transactionOutput.getScriptPubKey().getAsm())
                .setScriptHex(transactionOutput.getScriptPubKey().getHex())
                .setScriptReqSignatures(transactionOutput.getScriptPubKey().getRequiresSignatures())
                .setScriptType(transactionOutput.getScriptPubKey().getType())
                .setTxId(transactionId)
                .setOutputNumber(transactionOutput.getOutputNumber());
    }

    @Nonnull
    public Address mapAddressToDatabase(@Nonnull String address) {
        return new Address()
                .setWalletAddress(address);
    }

    public TxInputWitness mapWitnessDataToDatabase(@Nonnull String witnessData,
                                                   @Nonnull String transactionId,
                                                   @Nonnull TransactionInput transactionInput) {
        return new TxInputWitness()
                .setTxId(transactionId)
                .setTxInputId(transactionInput.getTransactionId())
                .setVout(transactionInput.getVout())
                .setWitnessData(witnessData);
    }

    @Nonnull
    public TxOutAddress createTxOutAddress(@Nonnull String address, @Nonnull String transactionId, int outputNumber) {
        return new TxOutAddress()
                .setWalletAddress(address)
                .setTxId(transactionId)
                .setOutputNumber(outputNumber);
    }
}
