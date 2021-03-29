package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RawTransaction {

    @JsonProperty("raw_transaction")
    private String rawTransactionData;

    @JsonProperty("decoded_raw_transaction")
    private DecodedRawTransaction decodedRawTransaction;
}
