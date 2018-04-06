package server;

import java.sql.*;

public class DBManager implements DBCredentials {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    void insertCPUUtil(int util) throws SQLException {
        Connection con = getConnection();
        String query = "INSERT INTO CPU (value) " + "VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, util);
        ps.executeUpdate();
        ps.close();
        con.close();
    }


    int receiveLastRecordInserted() throws SQLException {
        int cpuUtil = 0;
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM CPU ORDER BY id DESC LIMIT 1";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            cpuUtil = Integer.parseInt(rs.getString("value"));
        }
        rs.close();
        stmt.close();
        con.close();
        return cpuUtil;
    }


}
