package litecoin_parser.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RawBlock {

    @JsonProperty("raw_block")
    private String rawBlockData;

    @JsonProperty("decoded_raw_block")
    private DecodedRawBlock decodedRawBlock;
}
