package controller;

import database.MySQLConnection;

public class GatewayController {

    public GatewayController() {
    }

    public void execute() {
        MySQLConnection mySQLConnection = new MySQLConnection();
        mySQLConnection.connect();
    }
}
