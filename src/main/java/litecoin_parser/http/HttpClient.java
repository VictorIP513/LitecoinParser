package litecoin_parser.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class HttpClient {

    private static final String BLOCKCHAIR_URL = "https://api.blockchair.com/litecoin";

    private static final String RAW_TRANSACTION_URL_PATTERN = "/raw/transaction/%s";
    private static final String RAW_BLOCK_URL_PATTERN = "/raw/block/%s";

    public String getRawTransaction(String transactionHash) throws IOException {
        return executeGetRequest(BLOCKCHAIR_URL + String.format(RAW_TRANSACTION_URL_PATTERN, transactionHash));
    }

    public String getRawBlock(int blockNumber) throws IOException {
        return executeGetRequest(BLOCKCHAIR_URL + String.format(RAW_BLOCK_URL_PATTERN, blockNumber));
    }

    private String executeGetRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        bufferedReader.close();

        return response;
    }
}
