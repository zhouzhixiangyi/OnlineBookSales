package cn.edu.ecut.dao;

import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Customer;
import cn.edu.ecut.entity.Message;
import cn.edu.ecut.helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {
    /**
     * 执行留言创建操作
     * @param m
     * @return 返回true or false 判断是否能插入表中
     */
    public  boolean save( Message m) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "INSERT INTO messages ( bno  , cno , content ) VALUES ( ? , ? , ? )" ;
        int count = h.update( SQL , m.getBook().getBookID() , m.getCustomer().getId() , m.getContent());
        h.release();
        return count == 1 ;
    }

    public Message find(int bno){
        String sql = "SELECT id , bno , cno , content , messageTime FROM messages WHERE bno = ?";
        return this.find(sql , bno);
    }
    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回用户
     */
    private Message find(String sql, Object param) {
        Message m = null;
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            if(rs.next()){
                m.setId(rs.getInt(1));
                int bno = rs.getInt(2);
                int cno = rs.getInt(3);
                m.setContent(rs.getString(4));
                m.setMessageTime(rs.getTimestamp(5));
                Book b = new Book();
                b.setBookID(bno);
                Customer c  = new Customer();
                c.setId(cno);
                m.setBook(b);
                m.setCustomer(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return m;
    }


}
