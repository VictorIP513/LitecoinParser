package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DecodedRawTransaction {

    @JsonProperty("txid")
    private String id;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("vsize")
    private Integer vsize;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("locktime")
    private Integer lockTime;

    @JsonProperty("vin")
    private List<TransactionInput> inputs;

    @JsonProperty("vout")
    private List<TransactionOutput> outputs;
}
