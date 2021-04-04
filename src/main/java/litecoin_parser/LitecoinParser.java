package litecoin_parser;

import litecoin_parser.service.LitecoinCli;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LitecoinParser {

    private static final LitecoinCli litecoinCli = new LitecoinCli();


    public static void main(String[] args) throws IOException {
        String rawBlockJson = litecoinCli.getRawBlockJson("1f2b06dfe433e72761a65c495a145bc449e2b7d71accbb4ed9590e295047308b");
        log.info(rawBlockJson);
    }
}
