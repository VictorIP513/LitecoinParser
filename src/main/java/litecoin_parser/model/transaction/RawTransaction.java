package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RawTransaction {

    @JsonProperty("raw_transaction")
    private String rawTransactionData;

    @JsonProperty("decoded_raw_transaction")
    private DecodedRawTransaction decodedRawTransaction;
}
