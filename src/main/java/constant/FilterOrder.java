package constant;

public enum FilterOrder {
    FIRST(0),
    HTML_TAG(1),
    CONVERT_CASE(2),
    DATEFORMAT(3),
    COLUMN_APPEND(4),
    COLUMN_SPLIT(5),
    TRIM(6);

    private final int order;

    FilterOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
