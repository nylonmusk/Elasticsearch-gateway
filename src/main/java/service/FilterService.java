package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.Filter;
import filter.FilterFactory;
import filter.FilterInterface;
import view.Log;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class FilterService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FilterFactory filterFactory;

    public FilterService(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    public List<Map<String, Object>> filter(List<Map<String, Object>> selectedData, Map<String, Object> filterConfig) throws IOException, ParseException {
        List<FilterInterface> filters = filterFactory.createFilters(filterConfig.keySet());

        for (FilterInterface filter : filters) {
            Map<String, Object> specificFilterConfig = getSpecificFilterConfig(filterConfig, filter);
            filter.filter(selectedData, specificFilterConfig);
        }
        Log.info(FilterService.class.getName(), "filtered successfully.");
        return selectedData;
    }

    private Map<String, Object> getSpecificFilterConfig(Map<String, Object> filterConfig, FilterInterface filter) {
        Filter keyword = getFilterKeyword(filter);
        if (keyword != null && filterConfig.containsKey(keyword.get())) {
            Object configValue = filterConfig.get(keyword.get());
            if (configValue != null && !configValue.toString().isEmpty()) {
                return parseSpecificFilterConfig(configValue);
            }
        }
        return new HashMap<>();
    }

    private Filter getFilterKeyword(FilterInterface filter) {
        for (Filter keyword : Filter.values()) {
            if (keyword.get().equals(getFilterName(filter.toString()))) {
                return keyword;
            }
        }
        return null;
    }

    private String getFilterName(String filterName) {
        String[] parts = filterName.split("@");
        String name = parts[0].substring(parts[0].lastIndexOf('.') + 1);
        return name.toLowerCase().replace("filter", "");
    }

    private Map<String, Object> parseSpecificFilterConfig(Object configValue) {
        try {
            String specificConfigJson = objectMapper.writeValueAsString(configValue);
            return objectMapper.readValue(specificConfigJson, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
