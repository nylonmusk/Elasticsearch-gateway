package controller;

import database.DatabaseManager;
import dump.Dump;
import service.FilterService;

import java.util.List;
import java.util.Map;

public class GatewayController {

    private final DatabaseManager databaseManager;
    private final FilterService filterService;
    private final Dump dump;

    public GatewayController(DatabaseManager databaseManager, FilterService filterService, Dump dump) {
        this.databaseManager = databaseManager;
        this.filterService = filterService;
        this.dump = dump;
    }

    public void execute() {
        databaseManager.connect();
        List<Map<String, Object>> data = databaseManager.select();
        databaseManager.disconnect();
        filterService.filter(data);
        dump.makeJson(data);
    }
}
