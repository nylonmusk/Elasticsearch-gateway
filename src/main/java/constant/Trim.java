package constant;

public enum Trim {
    COLUMNS("columns");

    private final String keyword;

    Trim(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }
}
