package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ScriptPubKey {

    @JsonProperty("asm")
    private String asm;

    @JsonProperty("hex")
    private String hex;

    @JsonProperty("reqSigs")
    private Integer requiresSignatures;

    @JsonProperty("type")
    private String type;

    @JsonProperty("addresses")
    private List<String> addresses;
}
