package filter;

import java.util.List;
import java.util.Map;

public class ColumnAppendFilter implements FilterInterface {

    private final String key1 = "";
    private final String key2 = "";
    private final String targetKey = "";

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            if (item.containsKey(key1) && item.containsKey(key2)) {
                Object value1 = item.get(key1);
                Object value2 = item.get(key2);

                if (value1 instanceof String && value2 instanceof String) {
                    String concatenatedValue = (String) value1 + (String) value2;
                    item.put(targetKey, concatenatedValue);
                }
            }
        }
    }
}
