package cn.edu.ecut.dao;

import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Sales;
import cn.edu.ecut.helper.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    /**
     * 执行书创建操作
     * @param b
     * @return 返回true or false 判断是否能插入表中
     */
    public  boolean save( Book b) {
        JdbcHelper h = JdbcHelper.getInstance();
        String SQL = "INSERT INTO books ( bookName , price , picture , number , introduction , category , sno , author ) VALUES ( ? , ? , ? , ? , ? , ? ,? ,?)" ;
        int count = h.update( SQL ,b.getBookName() , b.getPrice() , b.getPicture() , b.getNumber() , b.getIntroduction() , b.getCategory() ,b.getSales().getId() , b.getAuthor());
        h.release();
        return count == 1 ;
    }

    /**
     *  查询书是否存在
     * @param name 被查询的书名
     * @return 如果指定书已经在数据库表中存在就返回 true，否则就返回 false
     */
    public boolean exists( String name ) {
        boolean e = false ; // 假设 username 在数据库中不存在
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query( "SELECT bookID FROM books WHERE bookName = ? " , name );
        try {
            e = rs.next(); // 当结果集中第一行存在数据时，返回 true，否则返回 false
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        h.release();
        return e ;
    }


    /**
     * 根据ID查找书
     * @param id
     * @return 返回书
     */
    public Book find(int id){
        String sql = "SELECT bookID , bookName , price , picture , number , introduction , category , sno , author FROM books WHERE id = ?";
        return this.find(sql , id);
    }

    /**
     * 根据书名查找书
     * @param name 书名
     * @return 返回书
     */
    public Book find(String name){
        String sql = "SELECT bookID , bookName , price , picture , number , introduction , category , sno , author FROM books WHERE name = ?";
        return this.find(sql , name);
    }


    /**
     * 查询的具体实现
     * @param sql 查询语句
     * @param param 查询的参数
     * @return 返回用户
     */
    private Book find(String sql, Object param) {
        Book b = new Book();
        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query(sql, param);
        try {
            if(rs.next()){
               b = this.wrap(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        h.release( rs );
        h.release();
        return b;
    }

    /**
     * 订单减少库存
     * @param bno
     * @param num
     * @return
     */
    public boolean divideNumber(int bno , int num){
        JdbcHelper h = JdbcHelper.getInstance();
        String sql = "UPDATE books SET number -= num Where bookID = ? ";
        int count = h.update(sql, bno);
        h.release();
        return count == 1 ;
    }


    /**
     *分类查询图书
     * @param good_kind
     * @return
     */
    public List<Book> kindfind(String good_kind) {

        List<Book> Books = new ArrayList<>();

        String SQL = "SELECT bookID , bookName , price , picture , number , introduction , category , sno , author FROM books WHERE category = ?" ;

        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query( SQL,good_kind);
        try {
            while (rs.next()) {
                Book b = this.wrap(rs);
                Books.add(b);
            }
        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
        h.release( rs );
        h.release();

        return Books ;
    }


    /**
     * 查找全部图书
     * @return
     */
    public List<Book> findAll() {

        List<Book> books = new ArrayList<>();

        String SQL =  "SELECT bookID , bookName , price , picture , number , introduction , category , sno , author FROM books";

        JdbcHelper h = JdbcHelper.getInstance();
        ResultSet rs = h.query( SQL);
        try {
            while (rs.next()) {
                Book b = this.wrap(rs);
                books.add(b);
            }
        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
        h.release( rs );
        h.release();

        return books ;
    }


    /**
     *
     * @param rs
     * @return
     */
    private Book wrap( ResultSet rs ) {
        Book b=new Book();
        try
        {
            b.setBookID(rs.getInt(1));
            b.setBookName(rs.getString(2));
            b.setPrice(rs.getDouble(3));
            b.setPicture(rs.getString(4));
            b.setNumber(rs.getInt(5));
            b.setIntroduction(rs.getString(6));
            b.setCategory(rs.getString(7));
            int sno = rs.getInt("sno");
            Sales s = new Sales();
            s.setId(sno);
            b.setSales(s);
            b.setAuthor(rs.getString(9));
        }catch ( SQLException e ) {
            e.printStackTrace();
        }
        return b;
    }






}
