package filter;

import java.util.List;
import java.util.Map;

public class RemoveHtmlTagFilter implements FilterInterface {

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getValue() instanceof String) {
                    String value = (String) entry.getValue();
                    String filteredValue = value.replaceAll("<.*?>", "").replaceAll("&.*?;", "").replaceAll("\n", "");
                    entry.setValue(filteredValue);
                }
            }
        }
    }
}

