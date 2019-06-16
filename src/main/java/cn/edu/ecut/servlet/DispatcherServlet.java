package cn.edu.ecut.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class DispatcherServlet extends HttpServlet {
    protected void redirect(HttpServletRequest request, HttpServletResponse response , String url , String name , Object value )
            throws ServletException, IOException {
        HttpSession session = request.getSession(); // 获得与当前请求关联的会话对象
        session.setAttribute( name , value ); // 将 name-value 对应的 键-值对 放入到 会话对象
        response.sendRedirect( url );
    }
}
