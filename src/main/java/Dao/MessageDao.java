package Dao;

import Been.Message;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Dao.JDBCUtil.close;

public class MessageDao {

    private static MessageDao instance = null;

    public static MessageDao getInstance() {
        if (instance == null) {
            synchronized (MessageDao.class) {
                if (instance == null) {
                    instance = new MessageDao();
                }
            }
        }
        return instance;
    }
    public void messageInsert(Message message){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
        conn=JDBCUtil.getConnection();
        stmt = conn.prepareStatement("insert into message (username,id,pid,text) values(?,?,?,?)");

            ((PreparedStatement)stmt).setString(1,message.getUsername());
            ((PreparedStatement)stmt).setString(2,message.getUsername());
            ((PreparedStatement)stmt).setInt(3,message.getPid());
            ((PreparedStatement)stmt).setString(4,message.getText());

            ((PreparedStatement) stmt).execute();
        } catch (SQLException e) {
        e.printStackTrace();
    }finally {
            close(rs,stmt,conn);
        }
    }
    public Message messageSelect(int id){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        Message message=new Message();
        try {
            conn=JDBCUtil.getConnection();
            stmt=conn.prepareStatement("select*from message where(id=?)");
            ((PreparedStatement) stmt).setInt(1,id);
            rs=((PreparedStatement) stmt).executeQuery();
            while (rs.next()){
                message.setUsername(rs.getString("username"));
                message.setText(rs.getString("text"));
                message.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,stmt,conn);
        }
        return message;
    }
    public boolean messageDelete(int id){
        Connection conn = JDBCUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from message where (id = ?)");
            ((PreparedStatement)stmt).setInt(1,id);
            boolean result=((PreparedStatement)stmt).execute();
            if (result){
                return true;
            }else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Message> findMessageInfoByPid(int pid){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<Message>();
        try {
            stmt = conn.prepareStatement("select*from message where(pid=?)");
            stmt.setInt(1,pid);
            rs=stmt.executeQuery();
            while(rs.next()){
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setPid(rs.getInt("pid"));
                message.setText(rs.getString("text"));
                message.setUsername(rs.getString("username"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            close(rs, stmt,conn);
        }
        return list;
    }
}