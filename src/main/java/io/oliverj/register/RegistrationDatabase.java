package io.oliverj.register;

import java.sql.*;
import java.util.*;
import java.sql.Connection;

public class RegistrationDatabase {

    private Connection conn = null;
    private Map<UUID, Registration> registrations = new HashMap<UUID, Registration>();

    /**
     * This class is used to manage the database.
     * You can use {@code RegistrationDatabase.addRegistration(Registration r)}
     * to add a Registration to the database
     * @see Registration
     * @author olliejohnson
    **/
    public RegistrationDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:/sqlite/db/registrations.db";

            this.conn = DriverManager.getConnection(url);
            this.setupDatabase();

            System.out.println("Connection to SQLite has been established");

        } catch(SQLException | ClassNotFoundException e) {
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

    /**
     * This is a method that you will never need to use.
     * @author olliejohnson
     **/
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

    /**
     * This method is used to add a {@link Registration} to the database.
     *
     * @see Registration
     * @author olliejohnson
     */
    public void addRegistration(Registration r) {
        registrations.put(r.getUUID(), r);
    }

    /**
     * You use this function to get a {@link Registration}.
     * You input that users {@link UUID} and it returns a {@link Registration}
     *
     * @see UUID
     * @see Registration
     *
     * @author olliejohnson
     */
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
