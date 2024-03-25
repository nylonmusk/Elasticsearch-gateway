package filter;

import constant.FilterOrder;
import view.Log;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ColumnAppendFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.COLUMN_APPEND;
    }

    private final String key1;
    private final String key2;
    private final String targetKey;
    private final String attacher;

    public ColumnAppendFilter(String key1, String key2, String targetKey, String attacher) {
        this.key1 = key1;
        this.key2 = key2;
        this.targetKey = targetKey;
        this.attacher = attacher;
    }

    @Override
    public void filter(List<Map<String, Object>> data) throws IOException {
        for (Map<String, Object> item : data) {
            if (item.containsKey(key1) && item.containsKey(key2)) {
                String value1 = item.get(key1).toString();
                String value2 = item.get(key2).toString();

                StringBuilder appendedValue = new StringBuilder();
                appendedValue.append(value1).append(attacher).append(value2);
                item.put(targetKey, appendedValue);
            }
        }
        Log.info(ColumnAppendFilter.class.getName(), "Column appended successfully.");
    }
}
