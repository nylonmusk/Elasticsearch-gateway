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
    private final ColumnAppendFilter columnAppendFilter = new ColumnAppendFilter();
    private final ColumnSplitFilter columnSplitFilter = new ColumnSplitFilter();
    private final DateFormatFilter dateFormatFilter = new DateFormatFilter();
    private final RemoveHtmlTagFilter removeHtmlTagFilter = new RemoveHtmlTagFilter();
    private final ToLowerCaseFilter toLowerCaseFilter = new ToLowerCaseFilter();
    private final TrimFilter trimFilter = new TrimFilter();

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