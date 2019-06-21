package cn.edu.ecut.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet( "/image/show" )
public class ImageShowServlet extends HttpServlet {

    private static final String path = "D:/images" ;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 页面上通过 /image/show?filename=xxxx.jpg 的形式向当前 servlet 传递参数
        String filename = req.getParameter( "filename" );

        Path source = Paths.get( path , filename );

        if( Files.exists( source ) && Files.isRegularFile( source ) ) {
            resp.setContentType( "image/jpeg" );
            // 获得可以向客户端(浏览器)输出字节数据的输出流
            OutputStream output = resp.getOutputStream();
            // 将 source 对应的路径所表示的文件内容 复制到 output 流中，从而实现向页面上输出
            Files.copy( source , output );
        }

    }

}
