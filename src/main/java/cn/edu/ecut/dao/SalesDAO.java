package cn.edu.ecut.dao;

import cn.edu.ecut.entity.Sales;
import cn.edu.ecut.helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesDAO {

    /**
     * 保存方法
     * @param s 销售商s
     * @return 返回true or false
     */
    public boolean save(Sales s){
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "INSERT INTO sales ( name , password ) VALUES ( ? , ?)" ;
        int count = h.update( SQL , s.getName() , s.getPassword());
        h.release();
        return count == 1 ;
    }

    /**
     * 执行用户更新操作
     * @param s
     * @return
     */
    public  boolean update( Sales s ) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "UPDATE  sales SET password=?  WHERE id=?" ;
        int count = h.update( SQL , s.getPassword() , s.getId());
        h.release();
        return count == 1 ;
    }

    /**
     *  查询销售商是否存在
     * @param name 被查询的销售商名
     * @return 如果指定销售商名已经在数据库表中存在就返回 true，否则就返回 false
     */
    public boolean exists( String name ) {
        boolean e = false ; // 假设 username 在数据库中不存在
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query( "SELECT id FROM sales WHERE name = ? " , name );
        try {
            e = rs.next(); // 当结果集中第一行存在数据时，返回 true，否则返回 false
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        h.release();
        return e ;
    }

    /**
     * 根据ID查找销售商
     * @param id
     * @return 返回销售商
     */
    public Sales find(int id){
        String sql = "SELECT id , name , password FROM sales WHERE id = ?";
        return this.find(sql , id);
    }

    /**
     * 根据用户名查找销售商
     * @param name 销售商名
     * @return 返回销售商
     */
    public Sales find(String name){
        String sql = "SELECT id , name , password  FROM sales WHERE name = ?";
        return this.find(sql , name);
    }

    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回销售商
     */
    private Sales find(String sql, Object param) {
        Sales s = null;
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            if(rs.next()){
              s = this.wrap(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return s;
    }
    private Sales wrap(ResultSet rs){
        Sales s = new Sales();
        try {
            s.setId(rs.getInt(1));
            s.setName(rs.getString(2));
            s.setPassword(rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;

    }



}
