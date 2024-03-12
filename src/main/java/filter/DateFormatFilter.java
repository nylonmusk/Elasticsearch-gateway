package filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class DateFormatFilter implements FilterInterface {

    private final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public void filter(List<Map<String, Object>> data) {
        data.forEach(item -> {
            if (item.containsKey("crawl_date")) {
                Object value = item.get("crawl_date");
                if (value instanceof String) {
                    try {
                        String dateString = (String) value;
                        item.put("crawl_date", outputFormat.format(inputFormat.parse(dateString)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
