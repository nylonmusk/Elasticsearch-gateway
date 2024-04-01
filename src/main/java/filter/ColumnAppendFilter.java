package filter;

import constant.FilterOrder;
import view.Log;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ColumnAppendFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.COLUMN_APPEND;
    }

    private final List<String> columns;
    private final String outputColumn;
    private final String attacher;

    public ColumnAppendFilter(List<String> columns, String outputColumn, String attacher) {
        this.columns = columns;
        this.outputColumn = outputColumn;
        this.attacher = attacher;
    }

    @Override
    public void filter(List<Map<String, Object>> data) throws IOException {
        for (Map<String, Object> item : data) {
            StringBuilder outputValue = new StringBuilder();
            for (String column : columns) {
                if (item.containsKey(column)) {
                    outputValue.append(item.get(column).toString()).append(attacher);
                }
            }
            if (outputValue.length() != 0) {
                String value = outputValue.toString().substring(0, outputValue.length() - attacher.length());
                item.put(outputColumn, value);
            }
        }
        Log.info(ColumnAppendFilter.class.getName(), "Column appended successfully.");
    }
}
