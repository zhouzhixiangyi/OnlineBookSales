package cn.edu.ecut.dao;

import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Customer;
import cn.edu.ecut.entity.Order;
import cn.edu.ecut.helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {



    /**
     * 执行订单创建操作
     * @param o
     * @return 返回true or false 判断是否能插入表中
     */
    public  boolean save( Order o) {
        JdbcHelper h = JdbcHelper.getInstance();
        BookDAO bookDAO = new BookDAO();
        String SQL = "INSERT INTO orders ( bno , cno , number  ) VALUES ( ? , ? , ?  )" ;
        int count = h.update( SQL ,o.getBook().getBookID() , o.getCustomer().getId() , o.getNumber());
        h.release();
        return count == 1  && bookDAO.divideNumber(o.getBook().getBookID() ,  o.getNumber());
    }


    /**
     * 根据cno查找订单
     * @param cno
     * @return 返回用户
     */
    public Order find(int cno){
        String sql = "SELECT id , bno , cno , number, orderTime FROM customers WHERE cno = ?";
        return this.find(sql , cno);
    }



    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回用户
     */
    private Order find(String sql, Object param) {
        Order o = new Order();
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            if(rs.next()){
               o.setId(rs.getInt(1));
               int bno = rs.getInt(2);
               int cno = rs.getInt(3);
               o.setNumber(rs.getInt(4));
               o.setOrderTime(rs.getTimestamp(5));
               Customer c  = new Customer();
               c.setId(cno);
               Book b = new Book();
               b.setBookID(bno);
               o.setBook(b);
               o.setCustomer(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return o;
    }










}
