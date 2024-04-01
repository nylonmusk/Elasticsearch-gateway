package constant;

public enum ConvertCase {
    COLUMNS("columns");

    private final String keyword;

    ConvertCase(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }
}
