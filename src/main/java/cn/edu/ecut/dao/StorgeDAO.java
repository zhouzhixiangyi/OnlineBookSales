package cn.edu.ecut.dao;

import cn.edu.ecut.entity.*;
import cn.edu.ecut.helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorgeDAO {



    /**
     * 执行入库创建操作
     * @param s
     * @return 返回true or false 判断是否能插入表中
     */
    public  boolean save( Storge s) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "INSERT INTO storges ( bno , sno , number , price  ) VALUES ( ? , ? , ? ,? )" ;
        int count = h.update( SQL , s.getBook().getBookID() , s.getSales().getId() , s.getNumber() , s.getStorgePrice());
        h.release();
        return count == 1 ;
    }


    /**
     * 根据sno查找订单
     * @param sno
     * @return 返回用户
     */
    public Storge find(int sno){
        String sql = "SELECT id , bno , sno , number, price, orderTime FROM customers WHERE sno = ?";
        return this.find(sql , sno);
    }



    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回用户
     */
    private Storge find(String sql, Object param) {
        Storge s = new Storge();
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            if(rs.next()){
               s.setId(rs.getInt(1));
               int bno = rs.getInt(2);
               int sno = rs.getInt(3);
               s.setNumber(rs.getInt(4));
               s.setStorgePrice(rs.getDouble(5));
               s.setStorgeTime(rs.getTimestamp(6));
               Sales sales = new Sales();
               s.setId(sno);
               Book b = new Book();
               b.setBookID(bno);
               s.setBook(b);
               s.setSales(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return s;
    }










}
