package filter;

import constant.ConvertCase;
import constant.FilterOrder;
import view.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConvertCaseFilter implements FilterInterface {
    private final List<String> columns;

    public ConvertCaseFilter(Map<String, Object> config) {
        this.columns = getColumnsFromConfig(config.get(ConvertCase.COLUMNS.get()));
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
        return FilterOrder.CONVERT_CASE;
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (String column : columns) {
            for (Map<String, Object> item : data) {
                if (item.containsKey(column)) {
                    String value = item.get(column).toString().toLowerCase();
                    item.put(column, value);
                }
            }
        }
        Log.info(ConvertCaseFilter.class.getName(), "Converted Case successfully.");
    }
}
