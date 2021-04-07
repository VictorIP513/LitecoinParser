package litecoin_parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TransactionInput {

    @JsonProperty("txid")
    private String transactionId;

    @JsonProperty("vout")
    private Integer vout;

    @JsonProperty("scriptSig")
    private ScriptSig scriptSig;

    @JsonProperty("coinbase")
    private String coinbase;

    @JsonProperty("txinwitness")
    private List<String> witness;

    @JsonProperty("sequence")
    private Long sequenceNumber;
}
