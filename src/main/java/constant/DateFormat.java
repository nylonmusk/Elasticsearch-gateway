package constant;

public enum DateFormat {
    COLUMNS("columns"),
    INPUT_FORMAT("yyyyMMddHHmmss"),
    FORMAT_TYPE("formatType"),
    OUTPUT_FORMAT("yyyy-MM-dd HH:mm:ss");

    private final String date;

    DateFormat(String date) {
        this.date = date;
    }

    public String get() {
        return date;
    }
}
