package filter;

import constant.Column;
import constant.Filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FilterFactory {
    public List<FilterInterface> createFilters(Set<String> filterKeywords, Map<String, Object> filterConfig) {
        List<FilterInterface> filters = new ArrayList<>();

        for (String keyword : filterKeywords) {
            if (Filter.COLUMN_APPEND.equals(keyword)) {
                addColumnAppendFilter(filters, filterConfig);
            } else if (Filter.COLUMN_SPLIT.equals(keyword)) {
                addColumnSplitFilter(filters, filterConfig);
            } else if (Filter.TRIM.equals(keyword)) {
                filters.add(new TrimFilter());
            } else if (Filter.CONVERT_CASE.equals(keyword)) {
                filters.add(new ConvertCaseFilter());
            } else if (Filter.DATE_FORMAT.equals(keyword)) {
                addDateFormatFilter(filters, filterConfig);
            } else if (Filter.HTML_TAG.equals(keyword)) {
                filters.add(new HtmlTagFilter());
            }
        }

        filters.sort(Comparator.comparingInt(filter -> filter.getFilterOrder().getOrder()));

        return filters;
    }

    private void addColumnAppendFilter(List<FilterInterface> filters, Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.COLUMN_APPEND.get());
        List<String> option = (List<String>) optionMap.get(Column.OPTION.get());
        String key1 = option.get(0);
        String key2 = option.get(1);
        String targetKey = option.get(2);
        String attacher = option.get(3);
        filters.add(new ColumnAppendFilter(key1, key2, targetKey, attacher));
    }

    private void addColumnSplitFilter(List<FilterInterface> filters, Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.COLUMN_SPLIT.get());
        List<String> option = (List<String>) optionMap.get(Column.OPTION.get());
        String target = option.get(0);
        String key1 = option.get(1);
        String key2 = option.get(2);
        String separator = option.get(3);
        filters.add(new ColumnSplitFilter(target, key1, key2, separator));
    }

    private void addDateFormatFilter(List<FilterInterface> filters, Map<String, Object> filterConfig) {
        Map<String, Object> columnMap = (Map<String, Object>) filterConfig.get(Filter.DATE_FORMAT.get());
        List<String> column = (List<String>) columnMap.get("column");
        String column1 = column.get(0);
        String column2 = column.get(1);
        filters.add(new DateFormatFilter(column1, column2));
    }
}
