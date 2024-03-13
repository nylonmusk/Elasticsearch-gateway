package builder;

import controller.GatewayController;
import database.DatabaseManager;
import dump.Dump;
import service.FilterService;

public class GatewayBuilder {

    private DatabaseManager databaseManager;
    private FilterService filterService;
    private Dump dump;

    public GatewayBuilder databaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        return this;
    }

    public GatewayBuilder filterService(FilterService filterService) {
        this.filterService = filterService;
        return this;
    }

    public GatewayBuilder dump(Dump dump) {
        this.dump = dump;
        return this;
    }

    public GatewayController build() {
        return new GatewayController(databaseManager, filterService, dump);
    }
}
