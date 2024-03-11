package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public void connect() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/news";
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            if (connection != null) {
                System.out.println("연결 성공!");
            } else {
                System.out.println("연결 실패.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
