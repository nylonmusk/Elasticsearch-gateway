package validation;

import java.util.Map;

public class ConfigValidator {

    public static boolean isValid(Map<String, Map<String, Object>> configData, String keyword) {
        return configData.get(keyword) != null && !configData.get(keyword).isEmpty();
    }
}
