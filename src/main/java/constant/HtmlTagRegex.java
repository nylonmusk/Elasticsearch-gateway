package constant;

public enum HtmlTagRegex {
    HTML_TAG("<.*?>"),
    HTML_ENTITY("&.*?;"),
    WHITE_SPACE("\n"),
    BACKSLASH("\\\\");

    private final String regex;

    HtmlTagRegex(String regex) {
        this.regex = regex;
    }

    public String get() {
        return regex;
    }
}