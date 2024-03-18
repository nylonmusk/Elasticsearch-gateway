package filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ColumnAppendFilter implements FilterInterface {

    @Override
    public void filter(List<Map<String, Object>> data, Map<String, Object> config) throws IOException {
        final String key1 = config.get("key1").toString();
        final String key2 = config.get("key2").toString();
        final String targetKey = config.get("targetkey").toString();
        final String attacher = config.get("attacher").toString();

        for (Map<String, Object> item : data) {
            if (item.containsKey(key1) && item.containsKey(key2)) {
                Object value1 = item.get(key1);
                Object value2 = item.get(key2);

                StringBuilder appendedValue = new StringBuilder();
                appendedValue.append(value1).append(attacher).append(value2);
                item.put(targetKey, appendedValue);
            }
        }
    }
}
