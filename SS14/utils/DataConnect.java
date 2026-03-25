package Session14.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/flashsale";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}