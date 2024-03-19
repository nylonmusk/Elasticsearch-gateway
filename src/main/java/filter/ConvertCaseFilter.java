package filter;

import constant.FilterOrder;
import view.Log;

import java.util.List;
import java.util.Map;

public class ConvertCaseFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.CONVERT_CASE;
    }

    @Override
    public void filter(List<Map<String, Object>> data, Map<String, Object> config) {
        for (Map<String, Object> item : data) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                entry.setValue(entry.getValue().toString().toLowerCase());
            }
        }
        Log.info(ConvertCaseFilter.class.getName(), "Converted Case successfully.");
    }
}
