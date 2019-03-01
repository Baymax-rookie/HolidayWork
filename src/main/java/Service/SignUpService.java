package Service;

import Been.User;
import Dao.UserDao;
import Dao.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Dao.JDBCUtil.close;

public class SignUpService {
    public static boolean signup(String username, String password,String email){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try {
            conn= JDBCUtil.getConnection();
            stmt=conn.createStatement();
            rs=stmt.executeQuery("select * from user where username=? and password =?");
            if (rs.getString(1).equals(username)){
                return false;
            }else {
                User user=new User(username,password,email);
                UserDao.userInsert(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,conn);
        }
        return false;
    }
}
