package cibertec.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cursostec";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    private DBConnection() {
        // Constructor privado para prevenir instancias
    }

    public static synchronized Connection getConnection() {
    	if (connection == null) {
            try {
                // Esta línea asegura que el driver JDBC se cargue explícitamente
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al conectar con la base de datos", e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("No se encontró el driver de MySQL", e);
            }
        }
        return connection;
    }
}

