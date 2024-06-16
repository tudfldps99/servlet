package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")      // name: 서블릿 이름, urlPatterns: URL 매핑 (name != urlPatterns)
public class HelloServlet extends HttpServlet {
    
    // ctrl + o 로 function 검색
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(req, resp);

        // 요청 url = http://localhost:8080/hello
        System.out.println("HelloServlet.service");         // HelloServlet.service
        System.out.println("request = " + request);         // org.apache.catalina.connector.RequestFacade@27390e6b
        System.out.println("response = " + response);       // org.apache.catalina.connector.ResponseFacade@49c57f50

        // 요청 url = http://localhost:8080/hello?username=park
        String username = request.getParameter("username");
        System.out.println("username = " + username);       // username = park

        // 응답
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
