package service;

import filter.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class FilterService {
    private final Logger logger = LogManager.getLogger(FilterService.class);

    public FilterService() {
    }

    public List<Map<String, Object>> filter(List<Map<String, Object>> selectedData, List<FilterInterface> filters) {
        for (FilterInterface filter : filters) {
            filter.filter(selectedData);
        }
        logger.info("filter 성공.");
        return selectedData;
    }
}