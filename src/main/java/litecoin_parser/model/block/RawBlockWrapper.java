package litecoin_parser.model.block;

import lombok.Data;

import java.util.Map;

@Data
public class RawBlockWrapper {

    private Map<String, RawBlock> data;
}
