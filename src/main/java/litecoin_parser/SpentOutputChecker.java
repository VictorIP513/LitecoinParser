package litecoin_parser;

import litecoin_parser.model.RawBlock;
import litecoin_parser.model.RawTransaction;
import litecoin_parser.model.TransactionOutput;
import litecoin_parser.service.DatabaseService;
import litecoin_parser.service.LitecoinCli;
import litecoin_parser.service.MessageMapper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

@Slf4j
public class SpentOutputChecker {

    private static final LitecoinCli litecoinCli = new LitecoinCli();
    private static final MessageMapper messageMapper = new MessageMapper();
    private static final DatabaseService databaseService = new DatabaseService();

    public static void main(String[] args) throws IOException {
        String blockHash = "5b298c4221dddff44f84ff1a836571ae72f88fe4191e56d9d144bdbcbb408b69";

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

            List<TransactionOutput> transactionOutputs = transaction.getOutputs();
            for (TransactionOutput transactionOutput : transactionOutputs) {
                boolean isSpent = litecoinCli.isSpentOutput(transactionId, transactionOutput.getOutputNumber());
                databaseService.updateSpentOutputInformation(transactionId, transactionOutput.getOutputNumber(), isSpent);
            }
        }
    }
}
