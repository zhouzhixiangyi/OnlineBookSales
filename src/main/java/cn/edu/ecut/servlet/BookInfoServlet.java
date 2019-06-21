package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.BookDAO;
import cn.edu.ecut.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/book/info")
public class BookInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int bookID = Integer.parseInt(req.getParameter("bookID"));
        System.out.println(bookID);
        BookDAO bd = new BookDAO();
        Book b = null;
        b = bd.find(bookID);
        System.out.println(b);
        session.setAttribute("book" , b);
        resp.sendRedirect("/pages/detail.vm");
    }
}
