//package builder;
//
//import filter.*;
//import service.FilterService;
//
//public class FilterServiceBuilder {
//    private ColumnAppendFilter columnAppendFilter;
//    private ColumnSplitFilter columnSplitFilter;
//    private DateFormatFilter dateFormatFilter;
//    private RemoveHtmlTagFilter removeHtmlTagFilter;
//    private ConvertCaseFilter convertCaseFilter;
//    private TrimFilter trimFilter;
//
//    public FilterServiceBuilder columnAppendFilter(ColumnAppendFilter columnAppendFilter) {
//        this.columnAppendFilter = columnAppendFilter;
//        return this;
//    }
//
//    public FilterServiceBuilder columnSplitFilter(ColumnSplitFilter columnSplitFilter) {
//        this.columnSplitFilter = columnSplitFilter;
//        return this;
//    }
//
//    public FilterServiceBuilder dateFormatFilter(DateFormatFilter dateFormatFilter) {
//        this.dateFormatFilter = dateFormatFilter;
//        return this;
//    }
//
//    public FilterServiceBuilder removeHtmlTagFilter(RemoveHtmlTagFilter removeHtmlTagFilter) {
//        this.removeHtmlTagFilter = removeHtmlTagFilter;
//        return this;
//    }
//
//    public FilterServiceBuilder toLowerCaseFilter(ConvertCaseFilter convertCaseFilter) {
//        this.convertCaseFilter = convertCaseFilter;
//        return this;
//    }
//
//    public FilterServiceBuilder trimFilter(TrimFilter trimFilter) {
//        this.trimFilter = trimFilter;
//        return this;
//    }
//
//    public FilterService build() {
//        return new FilterService(columnAppendFilter, columnSplitFilter, dateFormatFilter, removeHtmlTagFilter, convertCaseFilter, trimFilter);
//    }
//}
