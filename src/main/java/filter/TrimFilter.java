package filter;

import constant.FilterOrder;
import view.Log;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TrimFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.TRIM;
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            Iterator<Map.Entry<String, Object>> iterator = item.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                String value = entry.getValue().toString();
                entry.setValue(value.trim());
            }
        }
        Log.info(TrimFilter.class.getName(), "trimmed successfully.");
    }
}
