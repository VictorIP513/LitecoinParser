package litecoin_parser.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RawBlock {

    @JsonProperty("raw_block")
    private String rawBlockData;

    @JsonProperty("decoded_raw_block")
    private DecodedRawBlock decodedRawBlock;
}
