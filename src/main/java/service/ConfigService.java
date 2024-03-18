package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.Keyword;
import view.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigService {
    private final String filePath;
    private final Map<String, Map<String, Object>> configData;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ConfigService(String filePath) throws IOException {
        this.filePath = filePath;
        this.configData = loadJsonFromFile(filePath);
    }

    private Map<String, Map<String, Object>> loadJsonFromFile(String filePath) throws IOException {
        Map<String, Map<String, Object>> result;
        result = objectMapper.readValue(new File(filePath), new TypeReference<Map<String, Map<String, Object>>>() {
        });
        Log.info(ConfigService.class.getName(), "read Config File.");

        return result;
    }

    public Map<String, Object> getFilterConfig() {
        return configData.get(Keyword.FILTER.get());
    }

    public Map<String, Object> getFetchConfig() {
        return configData.get(Keyword.FETCH.get());
    }

    public Map<String, Object> getDatabaseConfig() {
        return configData.get(Keyword.DATABASE.get());
    }
}

