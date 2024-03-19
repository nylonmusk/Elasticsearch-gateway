package filter;

import constant.FilterOrder;
import view.Log;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ColumnSplitFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.COLUMN_SPLIT;
    }

    private final String target;
    private final String key1;
    private final String key2;
    private final String separator;

    public ColumnSplitFilter(String target, String key1, String key2, String separator) {
        this.target = target;
        this.key1 = key1;
        this.key2 = key2;
        this.separator = separator;
    }

    @Override
    public void filter(List<Map<String, Object>> data, Map<String, Object> config) {
        for (Map<String, Object> item : data) {
            if (item.containsKey(target)) {
                StringTokenizer st = new StringTokenizer(item.get(target).toString(), separator);
                String value1 = st.nextToken();
                String value2 = st.nextToken();

                item.put(key1, value1);
                item.put(key2, value2);
            }
        }
        Log.info(ColumnSplitFilter.class.getName(), "Column split successfully.");
    }
}
