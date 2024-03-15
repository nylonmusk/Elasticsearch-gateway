package controller;

import constant.Keyword;
import database.DatabaseManager;
import dump.Dump;
import filter.FilterFactory;
import filter.FilterInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FilterService;
import service.ConfigService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GatewayController {
    private final String PathOfJsonFile = "C:\\Users\\mayfarm\\Documents\\config.json";
    private final DatabaseManager databaseManager;
    private final FilterService filterService;
    private final Dump dump;
    private final ConfigService configService;
    private final FilterFactory filterFactory;
    private static final Logger logger = LogManager.getLogger(GatewayController.class);

    public GatewayController(DatabaseManager databaseManager, FilterService filterService, Dump dump, ConfigService configService, FilterFactory filterFactory) {
        this.databaseManager = databaseManager;
        this.filterService = filterService;
        this.dump = dump;
        this.configService = configService;
        this.filterFactory = filterFactory;
    }

    public void execute() throws IOException {
        Map<String, Map<String, Object>> jsonData = configService.loadJsonFromFile(PathOfJsonFile);

        databaseManager.connect(jsonData.get(Keyword.DATABASE.get()));
        List<Map<String, Object>> selectedData = databaseManager.select(jsonData.get(Keyword.FETCH.get()));
        databaseManager.disconnect();

        Set<String> filterKeywords = jsonData.get(Keyword.FILTER.get()).keySet();

        List<FilterInterface> filters = filterFactory.createFilters(filterKeywords);
        List<Map<String, Object>> filteredData = filterService.filter(selectedData, filters);

        dump.makeJson(filteredData);
    }
}
