package cn.edu.ecut.servlet;

import cn.edu.ecut.dao.CustomerDAO;
import cn.edu.ecut.dao.SalesDAO;
import cn.edu.ecut.entity.Customer;
import cn.edu.ecut.entity.Sales;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 需改
 */
@WebServlet("/user/sign/up")

public class CustomerSignUpServlet extends DispatcherServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         final String url = "/pages/regist.vm" ; // 确定注册失败后去往哪里
        HttpSession session = req.getSession() ; // 获得与当前请求关联的会话对象

        String username = req.getParameter( "username" );
        String password = req.getParameter( "password" );
        String confirm = req.getParameter( "confirm" );
        String role = req.getParameter("role");
        if(role.equals("sales")) {
            SalesDAO salesDAO = new SalesDAO();
            if(salesDAO.exists(username)){
                this.redirect( req , resp , url , "fail" , "销售商名 " + username + " 已经被占用" );
                return ;
            }
            Sales sales = new Sales();
            sales.setName(username);
            sales.setPassword(password);
            if(salesDAO.save(sales)){
                this.redirect( req , resp , "/pages/index.vm" , "success" , "注册成功" );
            } else {
                this.redirect( req , resp , url , "fail" , "注册失败，请重试" );
            }
        }else{
            CustomerDAO customerDAO = new CustomerDAO();
            if(customerDAO.exists(username)){
                this.redirect( req , resp , url , "fail" , "用户名 " + username + " 已经被占用" );
                return ;
            }

            Customer customer = new Customer();
            customer.setName(username);
            customer.setPassword(password);

            if( customerDAO.save(customer) ) {
                this.redirect( req , resp , "/pages/index.vm" , "success" , "注册成功" );
            } else {
                this.redirect( req , resp , url , "fail" , "注册失败，请重试" );
            }
        }



    }
}
