package cn.edu.ecut.entity;

import java.sql.Timestamp;

public class Message {
    private Integer id;
    private  Book book;
    private Customer customer;
    private Timestamp messageTime;
    private String content;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", book=" + book +
                ", customer=" + customer +
                ", messageTime=" + messageTime +
                ", content='" + content + '\'' +
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

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
