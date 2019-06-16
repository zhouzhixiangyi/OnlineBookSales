package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.CustomerDAO;
import cn.edu.ecut.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 需改
 */
@WebServlet("")

public class CustomerSignUpServlet extends DispatcherServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String url = "/pages/sign-up.vm" ; // 确定注册失败后去往哪里
        HttpSession session = req.getSession() ; // 获得与当前请求关联的会话对象

        String username = req.getParameter( "username" );
        String password = req.getParameter( "password" );
        String confirm = req.getParameter( "confirm" );

        CustomerDAO customerDAO = new CustomerDAO();
        if(customerDAO.exists(username)){
            this.redirect( req , resp , url , "fail" , "用户名 " + username + " 已经被占用" );
            return ;
        }

        Customer customer = new Customer(username , password);

        if( customerDAO.save(customer) ) {
            this.redirect( req , resp , "/pages/sign-in.vm" , "success" , "注册成功" );
        } else {
            this.redirect( req , resp , url , "fail" , "注册失败，请重试" );
        }

    }
}
