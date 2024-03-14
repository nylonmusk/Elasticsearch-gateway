package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigService {
    private static final Logger logger = LogManager.getLogger(ConfigService.class);

    public Map<String, Map<String, Object>> loadJsonFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, Object>> result;

        result = objectMapper.readValue(new File(filePath), new TypeReference<Map<String, Map<String, Object>>>(){});
        logger.info("Config 파일 읽기 성공.");
        return result;
    }
}
