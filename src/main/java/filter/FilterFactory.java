package filter;

import constant.Filter;

import java.util.*;

public class FilterFactory {

    public List<FilterInterface> createFilters(Set<String> filterKeywords) {

        List<FilterInterface> filters = new ArrayList<>();

        for (String keyword : filterKeywords) {
            if (keyword.equals(Filter.COLUMN_APPEND.get())) {
                filters.add(new ColumnAppendFilter());
            }
            if (keyword.equals(Filter.COLUMN_SPLIT.get())) {
                filters.add(new ColumnSplitFilter());
            }
            if (keyword.equals(Filter.TRIM.get())) {
                filters.add(new TrimFilter());
            }
            if (keyword.equals(Filter.CONVERT_CASE.get())) {
                filters.add(new ConvertCaseFilter());
            }
            if (keyword.equals(Filter.DATE_FORMAT.get())) {
                filters.add(new DateFormatFilter());
            }
            if (keyword.equals(Filter.HTML_TAG.get())) {
                filters.add(new RemoveHtmlTagFilter());
            }
        }
        return filters;
    }
}

