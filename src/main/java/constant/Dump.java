package constant;

public enum Dump {
    FILE_PATH("file_path");

    private final String keyword;

    Dump(String keyword) {
        this.keyword = keyword;
    }

    public String get() {
        return keyword;
    }
}
