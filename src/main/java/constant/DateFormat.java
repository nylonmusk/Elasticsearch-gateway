package constant;

public enum DateFormat {
    COLUMN("column"),
    INPUT_FORMAT("yyyyMMddHHmmss"),
    OUTPUT_FORMAT("yyyy-MM-dd HH:mm:ss");

    private final String date;

    DateFormat(String date) {
        this.date = date;
    }

    public String get() {
        return date;
    }
}
