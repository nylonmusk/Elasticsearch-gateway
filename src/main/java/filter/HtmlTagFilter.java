package filter;

import constant.FilterOrder;
import constant.HtmlTag;
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
        return value.replaceAll(HtmlTag.HTML_TAG.get(), HtmlTag.BLANK.get())
                .replaceAll(HtmlTag.HTML_ENTITY.get(), HtmlTag.BLANK.get())
                .replaceAll(HtmlTag.WHITE_SPACE.get(), HtmlTag.BLANK.get())
                .replaceAll(HtmlTag.BACKSLASH.get(), HtmlTag.BLANK.get());
    }
}

