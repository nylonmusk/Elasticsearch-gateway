package service;

import filter.FilterFactory;
import filter.FilterInterface;
import view.Log;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FilterService {
    private final FilterFactory filterFactory;

    public FilterService(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    public List<Map<String, Object>> filter(List<Map<String, Object>> selectedData, Map<String, Object> filterConfig) throws ParseException {
        try {
            List<FilterInterface> filters = filterFactory.createFilters(filterConfig.keySet(), filterConfig);

            for (FilterInterface filter : filters) {
                filter.filter(selectedData);
            }
            Log.info(FilterService.class.getName(), "filtered successfully.");
            return Collections.unmodifiableList(selectedData);
        } catch (IOException e) {
            Log.error(FilterService.class.getName(), "Error occurred during filtering: " + e.getMessage());
        }
        return Collections.EMPTY_LIST;
    }
}
