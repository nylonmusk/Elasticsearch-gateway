package filter;

import view.Log;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ColumnSplitFilter implements FilterInterface {

    @Override
    public void filter(List<Map<String, Object>> data, Map<String, Object> config) {
        final String target = config.get("target").toString();
        final String key1 = config.get("key1").toString();
        final String key2 = config.get("key2").toString();
        final String separator = config.get("separator").toString();

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
