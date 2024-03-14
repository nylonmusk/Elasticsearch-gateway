import builder.GatewayBuilder;
import controller.GatewayController;
import database.DatabaseManager;
import dump.Dump;

import filter.FilterFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ConfigService;
import service.FilterService;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("실행");

        GatewayController gatewayController = createGatewayController();
        gatewayController.execute();
    }

    private static GatewayController createGatewayController() {
        return new GatewayBuilder()
                .databaseManager(new DatabaseManager())
                .filterService(new FilterService())
                .dump(new Dump())
                .configService(new ConfigService())
                .filterFactory(new FilterFactory())
                .build();
    }
}
