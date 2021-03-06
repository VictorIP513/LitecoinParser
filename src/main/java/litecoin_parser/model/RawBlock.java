package litecoin_parser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RawBlock {

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("confirmations")
    private Integer countConfirmations;

    @JsonProperty("strippedsize")
    private Integer strippedSize;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("version")
    private Integer version;

    @JsonProperty("versionHex")
    private String versionHex;

    @JsonProperty("merkleroot")
    private String merkleRoot;

    @JsonProperty("tx")
    private List<RawTransaction> transactions;

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("mediantime")
    private Integer medianTime;

    @JsonProperty("nonce")
    private Long nonce;

    @JsonProperty("bits")
    private String bits;

    @JsonProperty("difficulty")
    private Double difficulty;

    @JsonProperty("chainwork")
    private String chainWork;

    @JsonProperty("nTx")
    private Integer transactionsCount;

    @JsonProperty("previousblockhash")
    private String previousBlockHash;

    @JsonProperty("nextblockhash")
    private String nextBlockHash;
}
