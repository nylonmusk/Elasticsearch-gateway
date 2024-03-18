package filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class DateFormatFilter implements FilterInterface {

    private final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateFormatFilter() {
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void filter(List<Map<String, Object>> data, Map<String, Object> config) {
        try {
            List<String> columns = (List<String>) config.get("column");
            for (Map<String, Object> item : data) {
                for (String columnName : columns) {
                    if (item.containsKey(columnName)) {
                        String columnValue = (String) item.get(columnName);
                        Date date = inputFormat.parse(columnValue);
                        String iso8601Date = outputFormat.format(date);
                        item.put(columnName, iso8601Date);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
