package litecoin_parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RawTransaction {

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
    private Long lockTime;

    @JsonProperty("vin")
    private List<TransactionInput> inputs;

    @JsonProperty("vout")
    private List<TransactionOutput> outputs;
}
