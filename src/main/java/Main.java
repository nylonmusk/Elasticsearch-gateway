import builder.FilterServiceBuilder;
import builder.GatewayBuilder;
import controller.GatewayController;
import database.DatabaseManager;
import dump.Dump;
import filter.*;
import service.FilterService;

public class Main {
    public static void main(String[] args) {
        FilterService filterService = createFilterService();
        GatewayController gatewayController = createGatewayController(filterService);

        gatewayController.execute();
    }

    private static FilterService createFilterService() {
        return new FilterServiceBuilder()
                .columnAppendFilter(new ColumnAppendFilter())
                .columnSplitFilter(new ColumnSplitFilter())
                .dateFormatFilter(new DateFormatFilter())
                .removeHtmlTagFilter(new RemoveHtmlTagFilter())
                .toLowerCaseFilter(new ToLowerCaseFilter())
                .trimFilter(new TrimFilter())
                .build();
    }

    private static GatewayController createGatewayController(FilterService filterService) {
        return new GatewayBuilder()
                .databaseManager(new DatabaseManager())
                .filterService(filterService)
                .dump(new Dump())
                .build();
    }
}
