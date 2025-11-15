package org.example.contactos.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection;
    private static String user = "root";
    private static String pass = "root";
    static private String host="localhost";
    static private int port = 3306;
    static private String dbName= "prueba";
    private static String url = String.format("jdbc:mysql://%s:%d/%s",host,port,dbName);


    public DBConnector() {
        getConnection();
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()){
                createConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }
    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e){
            System.out.println("Error accediendo a la base de datos");
            System.out.println(e.getMessage());
        }
    }
}
