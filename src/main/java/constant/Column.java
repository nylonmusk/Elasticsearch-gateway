package constant;

public enum Column {
    COLUMNS("columns"),
    OUTPUT_COLUMN("outputColumn"),
    ATTACHER("attacher"),

    COLUMN("column"),
    OUTPUT_COLUMNS("outputColumns"),
    SEPARATOR("separator"),

    OPTION("option");

    private final String keyword;

    Column(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }
}
