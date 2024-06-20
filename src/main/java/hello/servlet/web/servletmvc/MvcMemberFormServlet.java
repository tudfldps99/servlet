package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 회원 등록 폼 - 컨트롤러
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);      // 다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출 발생

        /**
         * redirect vs forward
         * redirect 는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청
         * 따라서 클라이언트가 인지할 수 있고, URL 경로도 실제로 변경
         * forward 는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못함
         */
    }
}
