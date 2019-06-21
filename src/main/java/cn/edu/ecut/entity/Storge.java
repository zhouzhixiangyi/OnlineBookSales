package cn.edu.ecut.entity;

import java.sql.Timestamp;

public class Storge {
    private Integer id;
    private Sales sales;
    private Timestamp storgeTime;
    private Integer number;
    private Double storgePrice;
    private Book book;

    public Double getStorgePrice() {
        return storgePrice;
    }

    public void setStorgePrice(Double storgePrice) {
        this.storgePrice = storgePrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Timestamp getStorgeTime() {
        return storgeTime;
    }

    public void setStorgeTime(Timestamp storgeTime) {
        this.storgeTime = storgeTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }



}
