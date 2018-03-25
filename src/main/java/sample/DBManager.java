package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager implements DBCredentials{

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }

    void insertCPUUtil(int util) throws SQLException {
        Connection con = getConnection();
        String query = "INSERT INTO CPU (value) " + "VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,util);
        ps.executeUpdate();
        ps.close();

    }


}
