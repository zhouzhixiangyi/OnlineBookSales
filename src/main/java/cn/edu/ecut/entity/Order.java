package cn.edu.ecut.entity;

import java.sql.Timestamp;

public class Order {
    private Integer id ;
    private Book book;
    private Customer customer;
    private Timestamp orderTime;
    private Integer number;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", book=" + book +
                ", customer=" + customer +
                ", orderTime=" + orderTime +
                ", number=" + number +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
