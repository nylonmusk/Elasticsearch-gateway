package filter;

import java.util.List;
import java.util.Map;

public class ToLowerCaseFilter implements FilterInterface {

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getValue() instanceof String) {
                    String value = entry.getValue().toString();
                    entry.setValue(value.toLowerCase());
                }
            }
        }
    }
}
