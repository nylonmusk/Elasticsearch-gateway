package filter;

import constant.FilterOrder;
import constant.HtmlTagRegex;
import view.Log;

import java.util.List;
import java.util.Map;

public class HtmlTagFilter implements FilterInterface {

    @Override
    public FilterOrder getFilterOrder() {
        return FilterOrder.HTML_TAG;
    }

    @Override
    public void filter(List<Map<String, Object>> data) {
        for (Map<String, Object> item : data) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                String value = entry.getValue().toString();
                String filteredValue = replaceHtmlTag(value);
                entry.setValue(filteredValue);
            }
        }
        Log.info(HtmlTagFilter.class.getName(), "HtmlTag removed successfully.");
    }

    private String replaceHtmlTag(String value) {
        return value.replaceAll(HtmlTagRegex.HTML_TAG.get(), "")
                .replaceAll(HtmlTagRegex.HTML_ENTITY.get(), "")
                .replaceAll(HtmlTagRegex.WHITE_SPACE.get(), "")
                .replaceAll(HtmlTagRegex.BACKSLASH.get(), "");
    }
}

