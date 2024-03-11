package controller;

import database.DatabaseManager;

import java.util.List;
import java.util.Map;

public class GatewayController {

    public GatewayController() {
    }

    public void execute() {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect();
        List<Map<String, Object>> articles = databaseManager.select();
        databaseManager.disconnect();




    }
}
