package cn.edu.ecut.dao;

import cn.edu.ecut.entity.*;
import cn.edu.ecut.helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorgeDAO {



    /**
     * 执行入库创建操作
     * @param s
     * @return 返回true or false 判断是否能插入表中
     */
    public  boolean save( Storge s) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "INSERT INTO storges ( bno , sno , number , storgePrice  ) VALUES ( ? , ? , ? ,? )" ;
        System.out.print("INSERT INTO storges ( bno , sno , number , storgePrice  ) VALUES ("+s.getBook().getBookID()+","+ s.getSales().getId()+","+s.getNumber()+"," +s.getStorgePrice()+")");
        int count = h.update( SQL , s.getBook().getBookID() , s.getSales().getId() , s.getNumber() , s.getStorgePrice());
        h.release();
        return count == 1 ;
    }


    /**
     * 根据sno查找订单
     * @param sno
     * @return 返回用户
     */
    public List<Storge> find(int sno){
        String sql = "SELECT id , bno , sno , number, storgePrice, storgeTime FROM storges WHERE sno = ?";
        return this.find(sql , sno);
    }

    public Storge findByID(int id){
        String sql = "SELECT id , bno , sno , number, storgePrice, storgeTime FROM storges WHERE id = ?";
        Storge s = null;
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, id);
        try {
            if(rs.next()){
                 s = this.wrap(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }



    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回用户
     */
    private List<Storge> find(String sql, Object param) {
        List<Storge> list = new ArrayList<>();
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            while(rs.next()){
                Storge s = this.wrap(rs);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return list;
    }

    private Storge wrap(ResultSet rs){
        Storge s= new Storge();
        try {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }










}
