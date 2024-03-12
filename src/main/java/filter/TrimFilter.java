package filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TrimFilter implements FilterInterface {

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            Iterator<Map.Entry<String, Object>> iterator = item.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (entry.getValue() instanceof String) {
                    String value = (String) entry.getValue();
                    entry.setValue(value.trim());
                }
            }
        }
    }
}
