import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Connection;

public class RegistrationDatabase {

    private Connection conn = null;
    private Map<UUID, Registration> registrations = new HashMap<UUID, Registration>();

    public RegistrationDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:C:\\Users\\olive\\source\\repos\\INTELLIJ\\Student Manager\\databases\\Registrations.db";

            this.conn = DriverManager.getConnection(url);
            this.setupDatabase();

            System.out.println("Connection to SQLite has been established");

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setupDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS registrations (\n"
                + "uuid VARCHAR(50) PRIMARY KEY,\n"
                + "firstName VARCHAR(50) NOT NULL,\n"
                + "lastName VARCHAR(50) NOT NULL,\n"
                + "dateOfBirth DATE NOT NULL\n"
                + ")";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addRegistration(Registration r) {
        registrations.put(r.getUUID(), r);
    }

    public Registration getRegistration(UUID uuid) {
        return registrations.get(uuid);
    }

    public void removeRegistration(UUID uuid) {
        registrations.remove(uuid);
    }

    public Collection<Registration> listRegistrations() {
        return this.registrations.values();
    }

}
