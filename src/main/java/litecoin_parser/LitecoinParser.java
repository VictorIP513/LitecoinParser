package litecoin_parser;

import litecoin_parser.http.HttpClient;

import java.io.IOException;

public class LitecoinParser {

    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();

        String transactionHash = "7a87e76dc87b553846c42848f063e891c7b02b3855dae113e75859ed9ecfe80f";
        String rawTransaction = httpClient.getRawTransaction(transactionHash);
        System.out.println(rawTransaction);
        System.out.println();

        int blockNumber = 2023129;
        String rawBlock = httpClient.getRawBlock(blockNumber);
        System.out.println(rawBlock);
    }
}
