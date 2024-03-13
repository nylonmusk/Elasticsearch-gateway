package service;

import filter.ColumnAppendFilter;
import filter.ColumnSplitFilter;
import filter.DateFormatFilter;
import filter.RemoveHtmlTagFilter;
import filter.ToLowerCaseFilter;
import filter.TrimFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterService {
    private final ColumnAppendFilter columnAppendFilter;
    private final ColumnSplitFilter columnSplitFilter;
    private final DateFormatFilter dateFormatFilter;
    private final RemoveHtmlTagFilter removeHtmlTagFilter;
    private final ToLowerCaseFilter toLowerCaseFilter;
    private final TrimFilter trimFilter;

    public FilterService(ColumnAppendFilter columnAppendFilter, ColumnSplitFilter columnSplitFilter,
                         DateFormatFilter dateFormatFilter, RemoveHtmlTagFilter removeHtmlTagFilter,
                         ToLowerCaseFilter toLowerCaseFilter, TrimFilter trimFilter) {
        this.columnAppendFilter = columnAppendFilter;
        this.columnSplitFilter = columnSplitFilter;
        this.dateFormatFilter = dateFormatFilter;
        this.removeHtmlTagFilter = removeHtmlTagFilter;
        this.toLowerCaseFilter = toLowerCaseFilter;
        this.trimFilter = trimFilter;
    }


    public List<Map<String, Object>> filter(List<Map<String, Object>> data) {
        List<Map<String, Object>> filteredData = new ArrayList<>(data);
        columnAppendFilter.filter(filteredData);
        columnSplitFilter.filter(filteredData);
        dateFormatFilter.filter(filteredData);
        removeHtmlTagFilter.filter(filteredData);
        toLowerCaseFilter.filter(filteredData);
        trimFilter.filter(filteredData);
        return filteredData;
    }
}