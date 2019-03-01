package Dao;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtil {
    private static String url = "jdbc:mysql://localhost/hello?serverTimezone=GMT%2B8&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "";
    private static ArrayList<Connection> connlist = new ArrayList<Connection>();

    static {
        for (int i = 0; i < 5; i++) {
            Connection conn = createConnection();
            connlist.add(conn);
        }
    }

    public static Connection getConnection() {
        if (connlist.isEmpty() == false) {
            Connection conn = connlist.get(0);
            connlist.remove(conn);
            return conn;
        } else
            return null;
    }

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        closeResult(rs);
        closeStatement(stmt);
        closeConnection(conn);
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        closeResult(rs);
        closeStatement(stmt);
        closeConnection(conn);
    }

    public static void close(Statement stmt, Connection conn) {
        closeStatement(stmt);
        closeConnection(conn);
    }

    public static void closeResult(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(PreparedStatement stmt) {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn) {
        connlist.add(conn);
    }
}


