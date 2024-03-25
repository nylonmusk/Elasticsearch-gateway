import controller.GatewayController;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        final String configFilePath = args[0];
        GatewayController gatewayController = new GatewayController(configFilePath);
        gatewayController.execute();
    }
}
