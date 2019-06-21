package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.BookDAO;
import cn.edu.ecut.dao.CustomerDAO;
import cn.edu.ecut.dao.SalesDAO;
import cn.edu.ecut.dao.StorgeDAO;
import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Customer;
import cn.edu.ecut.entity.Sales;
import cn.edu.ecut.entity.Storge;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book/upload")
@MultipartConfig(location = "D:/images")
public class SalesAddBooksServlet extends HttpServlet {
    static  int number = 14 ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter w = resp.getWriter();
        w.println("不支持GET方式上传图片");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain;charset=UTF-8");
        HttpSession session = req.getSession(); // 获得与当前请求关联的会话对象
        SalesDAO sd = new SalesDAO();
        Sales s = new Sales();
        s= (Sales) session.getAttribute("sale");
        if(s == null){
            session.setAttribute("fail", "请先登录!");
            resp.sendRedirect("/pages/index.vm");
            return;
        }
        BookDAO bd = new BookDAO();
        Book b = new Book();

        String bookname = req.getParameter("bookname");
        String author = req.getParameter("author");
        double oprice = Double.parseDouble(req.getParameter("oprice"));
        double price = Double.parseDouble(req.getParameter("price"));
        int number = Integer.parseInt(req.getParameter("number"));
        String kind = req.getParameter("kind");
        String introduction = req.getParameter("introduction");
        String picture = req.getParameter("picture");


        b.setBookName(bookname);
        b.setAuthor(author);
        b.setPrice(price);
        b.setNumber(number);
        b.setCategory(kind);
        b.setIntroduction(introduction);
        b.setSales(s);
        System.out.println(s);
        Book book = null ;
        if((book =bd.find(bookname , b.getSales().getId())) != null){
            bd.addNumber(book.getBookID(),number);
        }else{
            String na = "upfile";
            b = write(req, na, b);
            bd.save(b);
        }





        Book b1 = bd.find(bookname,b.getSales().getId());

        System.out.println(b1);
        StorgeDAO storgeDAO = new StorgeDAO();
        Storge storge = new Storge();
        storge.setBook(b1);
        storge.setSales(s);
        storge.setNumber(number);
        storge.setStorgePrice(oprice);

        storgeDAO.save(storge);





//        b.getSales().setCount_good(g.getUser().getCount_good() + 1);
//        sd.update(b.getSales());
        resp.sendRedirect( "/sales/books/list");

    }

    private Book write(HttpServletRequest req, String up, Book b) {


        try {

                String temp=up;
                Part p = req.getPart(temp); // <input type="file" name="upfile">
                System.out.println(temp);

                if (p != null && p.getSize() > 0) {
                    String extension = ".jpg";
                    String filename = b.getSales().getId() +number+ extension;
                    number++;
                    p.write(filename);
                    b.setPicture(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }



}
