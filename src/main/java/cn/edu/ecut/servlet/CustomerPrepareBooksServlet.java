package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.BookDAO;
import cn.edu.ecut.dao.SalesDAO;
import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Sales;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet( urlPatterns = "/prepared" , loadOnStartup = 2 )
public class CustomerPrepareBooksServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println( "PreparedServlet 初始化" );
        ServletContext application = this.getServletContext();

        BookDAO bd = new BookDAO();
        SalesDAO sd = new SalesDAO();

        List<Book> list = bd.findAll();

        for (Book b:
             list) {
            Sales s = b.getSales();
            Sales sales  = sd.find(s.getId());
            b.setSales(sales);
        }
        application.setAttribute( "bookList" , list );

    }
}
