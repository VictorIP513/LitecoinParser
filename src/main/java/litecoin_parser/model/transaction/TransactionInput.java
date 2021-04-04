package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionInput {

    @JsonProperty("txid")
    private String transactionId;

    @JsonProperty("vout")
    private Integer vout;

    @JsonProperty("scriptSig")
    private ScriptSig scriptSig;

    @JsonProperty("sequence")
    private Long sequenceNumber;
}
