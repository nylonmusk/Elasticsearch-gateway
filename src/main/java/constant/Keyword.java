package constant;

public enum Keyword {
    DATABASE("database"),
    FETCH("fetch"),
    FILTER("filter"),
    DUMP("dump");

    private final String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }

}


