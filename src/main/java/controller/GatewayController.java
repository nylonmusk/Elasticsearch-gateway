package controller;

import database.DatabaseManager;
import dump.Dump;
import service.FilterService;

import java.util.List;
import java.util.Map;

public class GatewayController {

    public GatewayController() {
    }

    public void execute() {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect();
        List<Map<String, Object>> data = databaseManager.select();
        databaseManager.disconnect();
        FilterService filterService = new FilterService();
        System.out.println(data.get(1).toString());
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        filterService.filter(data);
        System.out.println(data.get(1).toString());


        Dump dump = new Dump();
        dump.makeJson(data);

    }
}
