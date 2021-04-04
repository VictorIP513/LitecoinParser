package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionOutput {

    @JsonProperty("value")
    private Double value;

    @JsonProperty("n")
    private Integer outputNumber;

    @JsonProperty("scriptPubKey")
    private ScriptPubKey scriptPubKey;
}
