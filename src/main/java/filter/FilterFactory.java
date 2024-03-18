package filter;

import constant.Filter;
import constant.FilterOrder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


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
                filters.add(new HtmlTagFilter());
            }

        }
        filters.sort(Comparator.comparingInt(filter -> {
            for (FilterOrder order : FilterOrder.values()) {
                if (order.name().equals(filter.getClass().getSimpleName())) {
                    return order.getOrder();
                }
            }
            return FilterOrder.FIRST.getOrder();
        }));


        return filters;
    }
}

