package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.BookDAO;
import cn.edu.ecut.dao.StorgeDAO;
import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Sales;
import cn.edu.ecut.entity.Storge;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/sales/books/search")
public class SalesBooksSearchServlet extends DispatcherServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Sales sales = (Sales)session.getAttribute("sale");
        if(sales == null){
            session.setAttribute("fail", "请先登录!");
            resp.sendRedirect("/pages/index.vm");
            return;
        }
        StorgeDAO sd = new StorgeDAO();
        BookDAO bd =new BookDAO();
        int id = Integer.parseInt(req.getParameter("id"));
        //List<Book> bookList = bd.findBySID(sales.getId());
        Storge s =  sd.findByID(id);

            Book b = s.getBook();
            Book book = bd.find(b.getBookID());
            s.setBook(book);

        session.setAttribute("storge", s);
        resp.sendRedirect("/pages/saleman_booksale_search.vm");



    }
}
