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

    private final String column;
    private final List<String> outputColumns;
    private final String separator;

    public ColumnSplitFilter(String column, List<String> outputColumns, String separator) {
        this.column = column;
        this.outputColumns = outputColumns;
        this.separator = separator;
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            if (item.containsKey(column)) {
                String[] values = item.get(column).toString().split(separator);
                for (int i = 0; i < outputColumns.size(); i++) {
                    item.put(outputColumns.get(i), values[i]);
                }
            }
        }
        Log.info(ColumnSplitFilter.class.getName(), "Column split successfully.");
    }
}
