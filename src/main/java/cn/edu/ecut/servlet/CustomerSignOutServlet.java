package cn.edu.ecut.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet( "/user/sign/out" )
public class CustomerSignOutServlet extends DispatcherServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession() ; // 获得与当前请求关联的会话对象

        // 废弃整个会话对象
        session.invalidate();

        // 重新返回到 首页
        resp.sendRedirect( "/pages/index.vm" );
    }
}
