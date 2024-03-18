import builder.GatewayBuilder;
import controller.GatewayController;
import database.DatabaseManager;
import dump.Dump;

import filter.FilterFactory;
import service.ConfigService;
import service.FilterService;
import view.Log;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Log.info(Main.class.getName(), "execute.");

        GatewayController gatewayController = createGatewayController();
        gatewayController.execute();
    }

    private static GatewayController createGatewayController() throws IOException {
        final String configFilePath = "C:\\Users\\mayfarm\\Documents\\config.json";

        return new GatewayBuilder()
                .databaseManager(new DatabaseManager())
                .filterService(new FilterService(new FilterFactory()))
                .dump(new Dump())
                .configService(new ConfigService(configFilePath))
                .build();
    }
}
