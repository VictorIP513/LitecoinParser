package litecoin_parser.model.transaction;

import lombok.Data;

import java.util.Map;

@Data
public class RawTransactionWrapper {

    private Map<String, RawTransaction> data;
}
