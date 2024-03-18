package controller;

import database.DatabaseManager;
import dump.Dump;
import service.ConfigService;
import service.FilterService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class GatewayController {
    private List<Map<String, Object>> fetchedData;
    private List<Map<String, Object>> filteredData;
    private final DatabaseManager databaseManager;
    private final FilterService filterService;
    private final Dump dump;
    private final ConfigService configService;

    public GatewayController(DatabaseManager databaseManager, FilterService filterService, Dump dump, ConfigService configService) {
        this.databaseManager = databaseManager;
        this.filterService = filterService;
        this.dump = dump;
        this.configService = configService;
    }

    public void execute() throws IOException, ParseException {
        getFetchedData();
        doFilter();
        makeDump();
    }

    private void getFetchedData() {
        fetchedData = databaseManager.execute(configService.getDatabaseConfig(), configService.getFetchConfig());
    }

    private void doFilter() throws IOException, ParseException {
        filteredData = filterService.filter(fetchedData, configService.getFilterConfig());
    }

    private void makeDump() {
        dump.makeJson(filteredData);
    }
}
