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

    private final DatabaseManager databaseManager;
    private final FilterService filterService;
    private final Dump dump;
    private static final Logger logger = LogManager.getLogger(GatewayController.class);

    public GatewayController(DatabaseManager databaseManager, FilterService filterService, Dump dump) {
        this.databaseManager = databaseManager;
        this.filterService = filterService;
        this.dump = dump;
    }

    public void execute() throws IOException {
        ConfigService configService = new ConfigService();
        Map<String, Map<String, Object>> jsonData = configService.loadJsonFromFile("C:\\Users\\mayfarm\\Documents\\config.json");

        databaseManager.connect(jsonData.get(Keyword.DATABASE.get()));
        List<Map<String, Object>> selectedData = databaseManager.select(jsonData.get(Keyword.FETCH.get()));
        databaseManager.disconnect();

        Set<String> filterKeywords = jsonData.get(Keyword.FILTER.get()).keySet();
        FilterFactory filterFactory = new FilterFactory();

        List<FilterInterface> filters = filterFactory.createFilters(filterKeywords);
        List<Map<String, Object>> filteredData = filterService.filter(selectedData, filters);

        dump.makeJson(filteredData);
    }
}