package filter;

import java.util.Map;

public class RemoveHtmlTagFilter implements Filter {

    @Override
    public Map<String, Object> filter(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() instanceof String) {
                String value = (String) entry.getValue();
                String filteredValue = value.replaceAll("<.*?>", "");
                entry.setValue(filteredValue);
            }
        }
        return data;
    }
}
