import controller.GatewayController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        GatewayController gatewayController = new GatewayController();
        gatewayController.execute();
    }
}