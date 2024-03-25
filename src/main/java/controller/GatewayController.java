package controller;

import database.DatabaseManager;
import dump.DumpBuilder;
import filter.FilterFactory;
import service.ConfigService;
import service.FilterService;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GatewayController {

    private final FilterFactory filterFactory = new FilterFactory();
    private final DatabaseManager databaseManager = new DatabaseManager();
    private final DumpBuilder dumpBuilder = new DumpBuilder();
    private final FilterService filterService;
    private final ConfigService configService;

    public GatewayController(String configFilePath) {
        this.configService = new ConfigService(configFilePath);
        this.filterService = new FilterService(filterFactory);
    }

    public void execute() throws ParseException {
        List<Map<String, Object>> fetchedData = getFetchedData();
        List<Map<String, Object>> filteredData = doFilter(fetchedData);
        makeDump(filteredData);
    }

    private List<Map<String, Object>> getFetchedData() {
        return Collections.unmodifiableList(databaseManager.execute(configService.getDatabaseConfig(), configService.getFetchConfig()));
    }

    private List<Map<String, Object>> doFilter(List<Map<String, Object>> fetchedData) throws ParseException {
        return Collections.unmodifiableList(filterService.filter(fetchedData, configService.getFilterConfig()));
    }

    private void makeDump(List<Map<String, Object>> filteredData) {
        dumpBuilder.makeJson(filteredData, configService.getDumpConfig());
    }
}
