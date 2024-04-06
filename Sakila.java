import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sakila {
    public static void main(String[] args) {
        try {
            Singleton singleton = Singleton.getInstance();
            Singleton singleton2 = Singleton.getInstance();
            //Se evidencian que las direcciones de memoria son las mismas por lo tanto singleton funciona
            System.out.println(singleton);
            System.out.println(singleton2);
            try (Statement statement = singleton.getStatement()) {
                statement.setQueryTimeout(30);  // set timeout to 30 sec.

                ResultSet rs = statement.executeQuery("select * from film");
                while (rs.next()) {
                    // read the result set
                    //System.out.println("title = " + rs.getString("title"));
                }
            }
            singleton.close();
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }
}
