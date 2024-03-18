package filter;

import java.util.List;
import java.util.Map;

public class ConvertCaseFilter implements FilterInterface {

    @Override
    public void filter(List<Map<String, Object>> data, Map<String, Object> config) {
        for (Map<String, Object> item : data) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                entry.setValue(entry.getValue().toString().toLowerCase());
            }
        }
    }
}
