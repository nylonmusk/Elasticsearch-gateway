package filter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface FilterInterface {
    void filter(List<Map<String, Object>> data, Map<String, Object> config) throws IOException, ParseException;
}
