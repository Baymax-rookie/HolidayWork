package Dao;

import Been.User;

import java.sql.*;

import static Dao.JDBCUtil.close;

public class UserDao {
    private static UserDao instance=null;
    public static UserDao getInstance(){
        if(instance == null){
            synchronized (UserDao.class){
                if(instance == null){
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }
    public static User userSelect(String username){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        User user=null;
        try {
            conn= JDBCUtil.getConnection();
            stmt=conn.prepareStatement("select * from user where (username=?)");
            stmt.setString(1,username);
            rs=stmt.executeQuery();
            while (rs.next()){
               user=new User(rs.getString("username"),rs.getString("password"),rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,conn);
        }
        return user;
    }
    public static void userInsert(User user){
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        java.util.Date date=new java.util.Date();
        Date currentdate=new Date(date.getTime());
        try {
            conn=JDBCUtil.getConnection();
            stmt=conn.prepareStatement("insert into user (username,password,emailName) value (?,?,?)");
            stmt.setString(1,user.getUsername());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getEmail());
            stmt.setDate(3,currentdate);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,conn);
        }
    }


}
