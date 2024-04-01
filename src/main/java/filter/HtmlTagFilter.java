package filter;

import constant.FilterOrder;
import constant.HtmlTag;
import view.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HtmlTagFilter implements FilterInterface {
    private final List<String> columns;

    public HtmlTagFilter(Map<String, Object> config) {
        this.columns = getColumnsFromConfig(config.get(HtmlTag.COLUMNS.get()));
    }

    private List<String> getColumnsFromConfig(Object config) {
        if (config instanceof List) {
            return (List<String>) config;
        }

        List<String> columns = new ArrayList<>();
        columns.add((String) config);
        return columns;
    }

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.HTML_TAG;
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (String column : columns) {
            for (Map<String, Object> item : data) {
                if (item.containsKey(column)) {
                    String value = item.get(column).toString();
                    String filteredValue = replaceHtmlTag(value);
                    item.put(column, filteredValue);
                }
            }
        }
        Log.info(HtmlTagFilter.class.getName(), "HtmlTag removed successfully.");
    }

    private String replaceHtmlTag(String value) {
        return value.replaceAll(HtmlTag.HTML_TAG.get(), HtmlTag.BLANK.get())
                .replaceAll(HtmlTag.HTML_ENTITY.get(), HtmlTag.BLANK.get())
                .replaceAll(HtmlTag.WHITE_SPACE.get(), HtmlTag.BLANK.get())
                .replaceAll(HtmlTag.BACKSLASH.get(), HtmlTag.BLANK.get());
    }
}

