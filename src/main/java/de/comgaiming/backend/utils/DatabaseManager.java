package de.comgaiming.backend.utils;

import de.comgaiming.backend.BackendApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private final FileManager fileAPI;
    private Connection c = null;
    private final String database;

    public DatabaseManager(String database) {
        this.database = database;
        fileAPI = new FileManager(database + ".yml");
        if (!fileAPI.exists()) {
            fileAPI.createFile();
            fileAPI.writeInNextFreeLine("database: " + database);
            fileAPI.writeInNextFreeLine("host: 127.0.0.1");
            fileAPI.writeInNextFreeLine("port: 5432");
            fileAPI.writeInNextFreeLine("username: username");
            fileAPI.writeInNextFreeLine("password: password");
        }
    }

    public String getDatabase() {;
        return database;
    }

    public void connect() {
        final String url="jdbc:mariadb://128.140.40.20:3306/" + getDatabase();
        String driver="org.mariadb.jdbc.Driver";
        try {
            Class.forName(driver);
            c=DriverManager.getConnection(url, "backend", "nÜq4jtöyfGsÄJU8r");
            BackendApplication.getLogger().logInfo("Connected to Database " + this.getDatabase() + "!");
        } catch (Exception e) {
            e.printStackTrace();
            BackendApplication.getLogger().logError(e.getClass().getName() + ": " + e.getMessage());
            System.exit(3);
        }
    }

    public Connection getConnection() {
        return c;
    }

    public void disconnect() {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}