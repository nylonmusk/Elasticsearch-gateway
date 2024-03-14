package constant;

public enum Filter {
    HTML_TAG("htmltag"),
    TRIM("trim"),
    DATE_FORMAT("dateformat"),
    COLUMN_APPEND("columnappend"),
    COLUMN_SPLIT("columnsplit"),
    CONVERT_CASE("convertcase");

    private final String keyword;

    Filter(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }

}
