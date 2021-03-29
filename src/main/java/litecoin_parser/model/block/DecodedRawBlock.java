package litecoin_parser.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DecodedRawBlock {

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
    private List<String> transactions;

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("mediantime")
    private Integer medianTime;

    @JsonProperty("nonce")
    private Integer nonce;

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
