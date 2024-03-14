package database;

import constant.Database;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements AutoCloseable {

    private final Logger logger = LogManager.getLogger(DatabaseManager.class);
    private Connection connection;

    public void connect(Map<String, Object> databaseData) {
        try {

            String jdbcUrl = databaseData.get(Database.URL.get()).toString();
            String username = databaseData.get(Database.USER_NAME.get()).toString();
            String password = databaseData.get(Database.PASSWORD.get()).toString();
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            logger.info("연결 성공.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("연결 종료.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        disconnect();
    }

    public List<Map<String, Object>> select(Map<String, Object> fetchData) {
        if (connection == null) {
            System.out.println("연결이 없습니다. connect 메소드를 호출하여 먼저 연결하세요.");
            return Collections.emptyList();
        }

        QueryRunner runner = new QueryRunner();
        Map<String, Object> fetchSettingData = (Map<String, Object>) fetchData.get(Database.SETTING.get());
        if (fetchSettingData != null) {
            String query = fetchSettingData.get(Database.QUERY.get()).toString();
            if (query != null && !query.isEmpty()) {
                logger.info("query 설정 성공.");
                try {
                    List<Map<String, Object>> data = runner.query(connection, query, new MapListHandler());
                    return Collections.unmodifiableList(data);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return Collections.emptyList();
    }
}
