package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.BookDAO;
import cn.edu.ecut.dao.CustomerDAO;
import cn.edu.ecut.dao.OrderDAO;
import cn.edu.ecut.entity.Book;
import cn.edu.ecut.entity.Customer;
import cn.edu.ecut.entity.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/order/confirm")
public class ConfirmOrderServlet extends DispatcherServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Book b = (Book)session.getAttribute("book");
        BookDAO bd = new BookDAO();
        Customer c = (Customer)session.getAttribute("customer");
        if(c == null){
            session.setAttribute("fail", "请先登录!");
            resp.sendRedirect("/pages/index.vm");
            return;
        }
        CustomerDAO cd = new CustomerDAO();
        Order o = new Order();
        OrderDAO od = new OrderDAO();
        o.setBook(b);
        o.setCustomer(c);
        od.save(o);

        List<Order> orderList = od.find(c.getId());
        for (Order order:
            orderList ) {
            Book book = order.getBook();
            Book book1 = bd.find(book.getBookID());
            order.setBook(book1);
            Customer customer = order.getCustomer();
            Customer customer1 = cd.find(customer.getId());
            order.setCustomer(customer1);
        }

        session.setAttribute("orderList" , orderList);


        resp.sendRedirect("/pages/order.vm");


    }
}
