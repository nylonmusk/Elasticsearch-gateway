package database;

import constant.Database;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
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
            String libPath = databaseData.get("libPath").toString().replaceAll("\\\\", "/");
            String lib = databaseData.get("lib").toString();
            if (!libPath.endsWith("/")) libPath += "/";

            File libFile = new File(libPath, lib);

            // URLClassLoader를 생성하여 MySQL JDBC 드라이버를 클래스패스에 추가
//            URL classURL = new URL("jar:" + libFile.toURI().toURL() + "!/");
//            logger.info(classURL);
//            URLClassLoader classLoader = new URLClassLoader(new URL[]{classURL});
            URLClassLoader classLoader = new URLClassLoader(new URL[]{libFile.toURI().toURL()});

            // MySQL JDBC 드라이버 클래스를 로드
            Class.forName("com.mysql.cj.jdbc.Driver", true, classLoader);

            // 나머지 연결 설정
            String url = (String) databaseData.get("url");
            String user = (String) databaseData.get("username");
            String password = (String) databaseData.get("password");

            // DriverManager를 통해 연결
            connection = DriverManager.getConnection(url, user, password);
            logger.info("연결 성공.");

        } catch (Exception e) {
            logger.error("연결 실패: " + e.getMessage());
        }
    }
//    public void connect(Map<String, Object> databaseData) {
//        try {
//
//            String jdbcUrl = databaseData.get(Database.URL.get()).toString();
//            String username = databaseData.get(Database.USER_NAME.get()).toString();
//            String password = databaseData.get(Database.PASSWORD.get()).toString();
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//            logger.info("연결 성공.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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
            logger.warn("연결이 없습니다.");
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
