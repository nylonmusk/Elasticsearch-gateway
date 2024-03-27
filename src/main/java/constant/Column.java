package constant;

public enum Column {
    OPTION("option");

    private final String keyword;

    Column(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }
}
