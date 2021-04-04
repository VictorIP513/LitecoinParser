package litecoin_parser.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import litecoin_parser.model.RawBlock;

import javax.annotation.Nonnull;

public class MessageMapper {

    private final ObjectMapper objectMapper;

    public MessageMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Nonnull
    public RawBlock parseRawBlockJson(@Nonnull String json) throws JsonProcessingException {
        return objectMapper.readValue(json, RawBlock.class);
    }
}
