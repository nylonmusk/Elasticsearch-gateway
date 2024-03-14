package constant;

public enum Database {
    URL("url"),
    USER_NAME("username"),
    PASSWORD("password"),
    SETTING("setting"),
    QUERY("query");

    private final String keyword;

    Database(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }
}
