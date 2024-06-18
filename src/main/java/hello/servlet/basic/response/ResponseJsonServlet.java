package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    // ctrl + o 로 function "service" 검색 -> protected 선택
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(req, resp);

        // Content-Type: application/json
        response.setContentType("application/json");        // HTTP 응답으로 JSON을 반환할 때는 content-type을 application/json으로 지정
        response.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("park");
        helloData.setAge(20);

        // {"username":"park", "age":20}
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
        
    }
}
