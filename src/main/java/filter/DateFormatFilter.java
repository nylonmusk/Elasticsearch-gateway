package filter;

import constant.DateFormat;
import constant.FilterOrder;
import view.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DateFormatFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.DATE_FORMAT;
    }

    private final SimpleDateFormat inputFormat = new SimpleDateFormat(DateFormat.INPUT_FORMAT.get());
    private final SimpleDateFormat outputFormat;
    private final List<String> columns;

    public DateFormatFilter(List<String> columns, String formatType) {
        this.columns = columns;
        this.outputFormat = new SimpleDateFormat(formatType);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        try {
            for (Map<String, Object> item : data) {
                for (String column : columns) {
                    format(item, column);
                }
            }
            Log.info(DateFormatFilter.class.getName(), "Date Formatted successfully.");
        } catch (ParseException e) {
            Log.error(DateFormatFilter.class.getName(), "Date Formatted failed.");
        }
    }

    private void format(Map<String, Object> item, String column) throws ParseException {
        if (item.containsKey(column)) {
            String columnValue = (String) item.get(column);
            Date date = inputFormat.parse(columnValue);
            String iso8601Date = outputFormat.format(date);
            item.put(column, iso8601Date);
        }
    }
}
