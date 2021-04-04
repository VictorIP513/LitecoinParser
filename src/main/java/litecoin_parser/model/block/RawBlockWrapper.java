package litecoin_parser.model.block;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class RawBlockWrapper {

    private Map<String, RawBlock> data;
}
