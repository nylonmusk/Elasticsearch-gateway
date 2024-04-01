package constant;

public enum HtmlTag {
    HTML_TAG("<.*?>"),
    HTML_ENTITY("&.*?;"),
    WHITE_SPACE("\n"),
    BACKSLASH("\\\\"),
    BLANK(""),
    COLUMNS("columns");

    private final String regex;

    HtmlTag(String regex) {
        this.regex = regex;
    }

    public String get() {
        return regex;
    }
}