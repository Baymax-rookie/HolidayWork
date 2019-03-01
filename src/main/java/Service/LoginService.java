package Service;


import Dao.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Dao.JDBCUtil.close;

public class LoginService {

    public static boolean login(String username,String password){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= JDBCUtil.getConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery("select * from user where username='"+username+"'and password='"+password+"'");
            while (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
          close(rs,stmt,conn);
        }
        return true;
    }
}
