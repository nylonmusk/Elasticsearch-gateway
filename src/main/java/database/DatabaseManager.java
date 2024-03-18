package database;

import constant.Database;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import view.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements AutoCloseable {
    private Connection connection;
    private static final URLClassLoader CLASS_LOADER = (URLClassLoader) ClassLoader.getSystemClassLoader();
    private static final List<URL> LOADED_URL_LIST = new ArrayList<>();
    private static final List<String> LOADED_CLASS_LIST = new ArrayList<>();

    public List<Map<String, Object>> execute(Map<String, Object> databaseData, Map<String, Object> fetchData) {
        connect(databaseData);
        List<Map<String, Object>> selectedData = select(fetchData);
        disconnect();
        return selectedData;
    }

    private void connect(Map<String, Object> databaseData) {
        try {
            String libPath = databaseData.get(Database.LIB_PATH.get()).toString();
            String lib = databaseData.get(Database.LIB.get()).toString();
            String driver = databaseData.get(Database.DRIVER.get()).toString();

            File libFile = new File(libPath, lib);
            URL classUrl = libFile.toURI().toURL();
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);

            if (!LOADED_URL_LIST.contains(classUrl)) {
                method.invoke(CLASS_LOADER, classUrl);
                LOADED_URL_LIST.add(classUrl);
            }
            if (!LOADED_CLASS_LIST.contains(driver)) {
                Class.forName(driver);
                LOADED_CLASS_LIST.add(driver);
            }

            String url = databaseData.get(Database.URL.get()).toString();
            String user = databaseData.get(Database.USER_NAME.get()).toString();
            String password = databaseData.get(Database.PASSWORD.get()).toString();

            connection = DriverManager.getConnection(url, user, password);
            Log.info(DatabaseManager.class.getName(), "Database connected successfully.");

        } catch (Exception e) {
            Log.error(DatabaseManager.class.getName(), "Database connect failed.");
        }
    }

    private void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Log.info(DatabaseManager.class.getName(), "Database disconnected successfully.");
            }
        } catch (SQLException e) {
            Log.info(DatabaseManager.class.getName(), "Database disconnect failed.");
        }
    }

    @Override
    public void close() {
        disconnect();
    }

    private List<Map<String, Object>> select(Map<String, Object> fetchData) {
        if (connection == null) {
            Log.warn(DatabaseManager.class.getName(), "No Connection");
            return Collections.emptyList();
        }

        QueryRunner runner = new QueryRunner();
        Map<String, Object> fetchSettingData = (Map<String, Object>) fetchData.get(Database.SETTING.get());
        if (fetchSettingData != null) {
            String query = fetchSettingData.get(Database.QUERY.get()).toString();
            if (query != null && !query.isEmpty()) {
                Log.info(DatabaseManager.class.getName(), "Query set successfully.");
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
