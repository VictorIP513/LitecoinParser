package litecoin_parser.model.transaction;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class RawTransactionWrapper {

    private Map<String, RawTransaction> data;
}
