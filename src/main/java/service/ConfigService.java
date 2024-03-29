package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.Keyword;
import validation.ConfigValidator;
import view.Log;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class ConfigService {
    private final Map<String, Map<String, Object>> configData;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ConfigService(String filePath) {
        this.configData = loadJsonFromFile(filePath);
    }

    private Map<String, Map<String, Object>> loadJsonFromFile(String filePath) {
        try {
            Map<String, Map<String, Object>> data = objectMapper.readValue(new File(filePath), new TypeReference<Map<String, Map<String, Object>>>() {
            });
            Log.info(ConfigService.class.getName(), "Read Config File successful");
            return data;
        } catch (IOException e) {
            Log.error(ConfigService.class.getName(), "Failed to read config file: " + e.getMessage());
        }
        return Collections.emptyMap();
    }

    public Map<String, Object> getFilterConfig() {
        if (ConfigValidator.isValid(configData, Keyword.FILTER.get())) {
            return configData.get(Keyword.FILTER.get());
        }
        return Collections.EMPTY_MAP;
    }

    public Map<String, Object> getFetchConfig() {
        if (ConfigValidator.isValid(configData, Keyword.FETCH.get())) {
            return configData.get(Keyword.FETCH.get());
        }
        return Collections.EMPTY_MAP;
    }

    public Map<String, Object> getDatabaseConfig() {
        if (ConfigValidator.isValid(configData, Keyword.DATABASE.get())) {
            return configData.get(Keyword.DATABASE.get());
        }
        return Collections.EMPTY_MAP;
    }

    public Map<String, Object> getDumpConfig() {
        if (ConfigValidator.isValid(configData, Keyword.DUMP.get())) {
            return configData.get(Keyword.DUMP.get());
        }
        return Collections.EMPTY_MAP;
    }
}

