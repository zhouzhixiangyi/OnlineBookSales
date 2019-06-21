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


@WebServlet("/user/sign/in")
public class CustomerSignInServlet extends DispatcherServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String url = "/pages/index.vm"; // 确定注册失败后去往哪里
        HttpSession session = req.getSession(); // 获得与当前请求关联的会话对象

        String username =req.getParameter("username");
        String password =req.getParameter("password");
        String role = req.getParameter("role");
        System.out.println(role);
        if(role.equals("sales")){
            SalesDAO sd = new SalesDAO();
            Sales s = new Sales();
            s = sd.find(username);
            if(s==null){
                this.redirect(req,resp,url,"failUser","销售商" + username + "不存在,请重新输入");
                return;
            }
            if(password.equals(s.getPassword())){
                session.setAttribute("sale",s);
                resp.sendRedirect("/pages/SalesSkip.vm");
            }
            else{
                this.redirect(req,resp,url,"failPwd","密码有误，请重新输入");
                System.out.println(password);
                return;
            }

        }else{
            CustomerDAO cd=new CustomerDAO();
            Customer c=new Customer();
            c = cd.find(username);
            if(c==null){
                this.redirect(req,resp,url,"failUser","用户" + username + "不存在,请重新输入");
                return;
            }
            if(password.equals(c.getPassword())){
                session.setAttribute("customer",c);
                resp.sendRedirect("/pages/home.vm");
            }
            else{
                this.redirect(req,resp,url,"failPwd","密码有误，请重新输入");
                System.out.println(password);
                return;
            }
        }





    }
}
