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
    private static final String FULL_BLOCK_INFORMATION_MODE = "2";

    private static final String LITECOIN_CLI_PATH = ApplicationProperties.getProperty("litecoin_cli_path");
    private static final String RPC_USER = ApplicationProperties.getProperty("rpc_user");
    private static final String RPC_PASSWORD = ApplicationProperties.getProperty("rpc_password");

    @Nonnull
    public String getRawBlockJson(@Nonnull String blockHash) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String[] commands = {
                LITECOIN_CLI_PATH,
                RPC_USER_ARGUMENT + RPC_USER,
                RPC_PASSWORD_ARGUMENT + RPC_PASSWORD,
                GET_BLOCK_COMMAND,
                blockHash,
                FULL_BLOCK_INFORMATION_MODE
        };

        Process litecoinCliProcess = runtime.exec(commands);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(litecoinCliProcess.getInputStream()));
        String rawBlockJson = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        bufferedReader.close();

        return rawBlockJson;
    }
}
