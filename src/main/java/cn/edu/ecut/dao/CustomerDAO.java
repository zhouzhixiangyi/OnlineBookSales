package cn.edu.ecut.dao;

import cn.edu.ecut.entity.Customer;
import cn.edu.ecut.helper.JdbcHelper;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {



    /**
     * 执行用户创建操作
     * @param c
     * @return 返回true or false 判断是否能插入表中
     */
    public  boolean save( Customer c) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "INSERT INTO customers ( name , password ) VALUES ( ? , ?)" ;
        int count = h.update( SQL , c.getName() , c.getPassword());
        h.release();
        return count == 1 ;
    }


    /**
     * 执行用户更新操作
     * @param c
     * @return
     */
    public  boolean update( Customer c ) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "UPDATE  customers SET password=? ,address=? WHERE id=?" ;
        int count = h.update( SQL , c.getPassword(), c.getAddress() , c.getId());
        h.release();
        return count == 1 ;
    }

    /**
     *  查询用户是否存在
     * @param name 被查询的用户名
     * @return 如果指定用户名已经在数据库表中存在就返回 true，否则就返回 false
     */
    public boolean exists( String name ) {
        boolean e = false ; // 假设 username 在数据库中不存在
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query( "SELECT id FROM customers WHERE name = ? " , name );
        try {
            e = rs.next(); // 当结果集中第一行存在数据时，返回 true，否则返回 false
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        h.release();
        return e ;
    }

    /**
     * 根据ID查找用户
     * @param id
     * @return 返回用户
     */
    public Customer find(int id){
        String sql = "SELECT id , name , password , address FROM customers WHERE id = ?";
        return this.find(sql , id);
    }

    /**
     * 根据用户名查找用户
     * @param name 用户名
     * @return 返回用户
     */
    public Customer find(String name){
        String sql = "SELECT id , name , password , address FROM customers WHERE name = ?";
        return this.find(sql , name);
    }

    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回用户
     */
    private Customer find(String sql, Object param) {
        Customer c = null;
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            if(rs.next()){
                c = this.wrap(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return c;
    }
    private Customer wrap(ResultSet rs){
        Customer c = new Customer();
        try {
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setPassword(rs.getString(3));
                c.setAddress(rs.getString(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }










}
