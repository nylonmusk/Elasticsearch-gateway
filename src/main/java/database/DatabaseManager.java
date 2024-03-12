package database;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements AutoCloseable {
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/news";
    private final String username = System.getenv("DB_USERNAME");
    private final String password = System.getenv("DB_PASSWORD");

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("연결 성공.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("연결 종료.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        disconnect();
    }

    public List<Map<String, Object>> select() {
        if (connection == null) {
            System.out.println("연결이 없습니다. connect 메소드를 호출하여 먼저 연결하세요.");
            return Collections.emptyList();
        }

        QueryRunner runner = new QueryRunner();
        final String query = "SELECT * FROM news_article";

        try {
            List<Map<String, Object>> data = runner.query(connection, query, new MapListHandler());
            return Collections.unmodifiableList(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
