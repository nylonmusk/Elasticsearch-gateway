package constant;

public enum Database {
    LIB_PATH("libPath"),
    LIB("lib"),
    DRIVER("driver"),
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
