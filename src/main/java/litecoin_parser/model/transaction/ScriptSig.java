package litecoin_parser.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ScriptSig {

    @JsonProperty("asm")
    private String asm;

    @JsonProperty("hex")
    private String hex;
}
