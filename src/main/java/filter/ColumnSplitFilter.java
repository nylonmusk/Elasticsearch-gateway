package filter;

import java.util.List;
import java.util.Map;

public class ColumnSplitFilter implements FilterInterface {

    private final String sourceKey = "";
    private final String targetKey1= "";
    private final String targetKey2= "";
    private final String separator= "";

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            if (item.containsKey(sourceKey)) {
                Object value = item.get(sourceKey);

                if (value instanceof String) {
                    String[] splitValues = ((String) value).split(separator, 2);

                    if (splitValues.length == 2) {
                        item.put(targetKey1, splitValues[0]);
                        item.put(targetKey2, splitValues[1]);
                    }
                }
            }
        }
    }
}
