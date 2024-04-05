import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Singleton {
    // La instancia única de la clase
    private static Singleton instancia;

    private Connection connection;
    private Statement statement;

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private Singleton() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:./sqlite-sakila.db");
        statement = connection.createStatement();
    }

    // Método estático para obtener la instancia única de la clase
    public static Singleton getInstance() throws SQLException {
        // Si la instancia aún no ha sido creada, la creamos
        if (instancia == null) {
            instancia = new Singleton();
        }
        // Retornamos la instancia única
        return instancia;
    }

    public Statement getStatement() {
        return statement;
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
