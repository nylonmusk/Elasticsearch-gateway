package filter;

import java.util.Map;

public class TrimFilter implements Filter {

    @Override
    public Map<String, Object> filter(Map<String, Object> data) {
        for (String key : data.keySet()) {
            data.put(key, data.get(key).toString().trim());
        }
        return data;
    }
}
