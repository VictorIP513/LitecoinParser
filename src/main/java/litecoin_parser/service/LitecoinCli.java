package litecoin_parser.service;

import litecoin_parser.properties.ApplicationProperties;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class LitecoinCli {

    private static final String RPC_USER_ARGUMENT = "-rpcuser=";
    private static final String RPC_PASSWORD_ARGUMENT = "-rpcpassword=";
    private static final String GET_BLOCK_COMMAND = "getblock";
    private static final String GET_TX_OUT_COMMAND = "gettxout";
    private static final String GET_RAW_TRANSACTION_COMMAND = "getrawtransaction";
    private static final String FULL_BLOCK_INFORMATION_MODE = "2";

    private static final String LITECOIN_CLI_PATH = ApplicationProperties.getProperty("litecoin_cli_path");
    private static final String RPC_USER = ApplicationProperties.getProperty("rpc_user");
    private static final String RPC_PASSWORD = ApplicationProperties.getProperty("rpc_password");

    @Nonnull
    public String getRawBlockJson(@Nonnull String blockHash) throws IOException {
        String[] commands = {
                LITECOIN_CLI_PATH,
                RPC_USER_ARGUMENT + RPC_USER,
                RPC_PASSWORD_ARGUMENT + RPC_PASSWORD,
                GET_BLOCK_COMMAND,
                blockHash,
                FULL_BLOCK_INFORMATION_MODE
        };

        return executeLitecoinCliProcess(commands);
    }

    public boolean isSpentOutput(@Nonnull String transactionId, int outputNumber) throws IOException {
        String[] commands = {
                LITECOIN_CLI_PATH,
                RPC_USER_ARGUMENT + RPC_USER,
                RPC_PASSWORD_ARGUMENT + RPC_PASSWORD,
                GET_TX_OUT_COMMAND,
                transactionId,
                String.valueOf(outputNumber)
        };

        String output = executeLitecoinCliProcess(commands);
        return output.isEmpty();
    }

    @Nonnull
    public String getRawTransaction(@Nonnull String transactionId) throws IOException {
        String[] commands = {
                LITECOIN_CLI_PATH,
                RPC_USER_ARGUMENT + RPC_USER,
                RPC_PASSWORD_ARGUMENT + RPC_PASSWORD,
                GET_RAW_TRANSACTION_COMMAND,
                transactionId
        };

        return executeLitecoinCliProcess(commands);
    }

    @Nonnull
    private String executeLitecoinCliProcess(@Nonnull String[] commands) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process litecoinCliProcess = runtime.exec(commands);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(litecoinCliProcess.getInputStream()));
        String output = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        bufferedReader.close();

        return output;
    }
}
