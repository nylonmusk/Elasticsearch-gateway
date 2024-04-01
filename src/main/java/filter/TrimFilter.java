package filter;

import constant.FilterOrder;
import constant.Trim;
import view.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrimFilter implements FilterInterface {
    private final List<String> columns;

    public TrimFilter(Map<String, Object> config) {
        this.columns = getColumnsFromConfig(config.get(Trim.COLUMNS.get()));
    }

    private List<String> getColumnsFromConfig(Object config) {
        if (config instanceof List) {
            return (List<String>) config;
        }

        List<String> columns = new ArrayList<>();
        columns.add((String) config);
        return columns;
    }

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.TRIM;
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (String column : columns) {
            for (Map<String, Object> item : data) {
                if (item.containsKey(column)) {
                    String value = item.get(column).toString().trim();
                    item.put(column, value);
                }
            }
        }
        Log.info(TrimFilter.class.getName(), "trimmed successfully.");
    }
}
