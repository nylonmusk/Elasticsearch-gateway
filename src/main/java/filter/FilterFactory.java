package filter;

import constant.Column;
import constant.DateFormat;
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
            addFilters(filterConfig, keyword, filters);
        }

        filters.sort(Comparator.comparingInt(filter -> filter.getFilterOrder().getOrder()));

        return filters;
    }

    private void addFilters(Map<String, Object> filterConfig, String keyword, List<FilterInterface> filters) {
        if (Filter.COLUMN_APPEND.equals(keyword)) {
            addColumnAppendFilter(filters, filterConfig);
            return;
        }
        if (Filter.COLUMN_SPLIT.equals(keyword)) {
            addColumnSplitFilter(filters, filterConfig);
            return;
        }
        if (Filter.TRIM.equals(keyword)) {
            filters.add(new TrimFilter(getTrimFilterConfig(filterConfig)));
            return;
        }
        if (Filter.CONVERT_CASE.equals(keyword)) {
            filters.add(new ConvertCaseFilter(getConvertCaseFilterConfig(filterConfig)));
            return;
        }
        if (Filter.DATE_FORMAT.equals(keyword)) {
            addDateFormatFilter(filters, filterConfig);
            return;
        }
        if (Filter.HTML_TAG.equals(keyword)) {
            filters.add(new HtmlTagFilter(getHtmlTagFilterConfig(filterConfig)));
        }
    }

    private Map<String, Object> getHtmlTagFilterConfig(Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.HTML_TAG.get());
        return optionMap;
    }

    private Map<String, Object> getTrimFilterConfig(Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.TRIM.get());
        return optionMap;
    }

    private Map<String, Object> getConvertCaseFilterConfig(Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.CONVERT_CASE.get());
        return optionMap;
    }

    private void addColumnAppendFilter(List<FilterInterface> filters, Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.COLUMN_APPEND.get());
        List<String> columns = (List<String>) optionMap.get(Column.COLUMNS.get());
        String outputColumn = (String) optionMap.get(Column.OUTPUT_COLUMN.get());
        String attacher = (String) optionMap.get(Column.ATTACHER.get());
        filters.add(new ColumnAppendFilter(columns, outputColumn, attacher));
    }

    private void addColumnSplitFilter(List<FilterInterface> filters, Map<String, Object> filterConfig) {
        Map<String, Object> optionMap = (Map<String, Object>) filterConfig.get(Filter.COLUMN_SPLIT.get());
        String column = (String) optionMap.get(Column.COLUMN.get());
        List<String> outputColumns = (List<String>) optionMap.get(Column.OUTPUT_COLUMNS.get());
        String separator = (String) optionMap.get(Column.SEPARATOR.get());
        filters.add(new ColumnSplitFilter(column, outputColumns, separator));
    }

    private void addDateFormatFilter(List<FilterInterface> filters, Map<String, Object> filterConfig) {
        Map<String, Object> columnMap = (Map<String, Object>) filterConfig.get(Filter.DATE_FORMAT.get());
        List<String> columns = (List<String>) columnMap.get(DateFormat.COLUMNS.get());
        String formatType = (String) columnMap.get(DateFormat.FORMAT_TYPE.get());
        filters.add(new DateFormatFilter(columns, formatType));
    }
}
