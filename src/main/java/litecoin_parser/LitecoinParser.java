package litecoin_parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import litecoin_parser.http.HttpClient;
import litecoin_parser.model.block.RawBlockWrapper;
import litecoin_parser.model.transaction.RawTransactionWrapper;

import java.io.IOException;

public class LitecoinParser {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpClient httpClient = new HttpClient();

        String transactionHash = "7a87e76dc87b553846c42848f063e891c7b02b3855dae113e75859ed9ecfe80f";
        String rawTransaction = httpClient.getRawTransaction(transactionHash);
        System.out.println(rawTransaction);
        System.out.println();

        RawTransactionWrapper rawTransactionWrapper = objectMapper.readValue(rawTransaction, RawTransactionWrapper.class);
        System.out.println(rawTransactionWrapper);

        int blockNumber = 2023129;
        String rawBlock = httpClient.getRawBlock(blockNumber);
        System.out.println(rawBlock);

        RawBlockWrapper rawBlockWrapper = objectMapper.readValue(rawBlock, RawBlockWrapper.class);
        System.out.println(rawBlockWrapper);
    }
}
