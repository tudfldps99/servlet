package hello.servlet.web.frontcontroller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 프론트 컨트롤러
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")      // /front-controller/v1 를 포함한 하위 모든 요청은 이 서블릿에서 받아들임
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServlet1.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI();                
        ControllerV1 controller = controllerMap.get(requestURI);    // requestURI를 조회하여 실제 호출 할 컨트롤러를 controllerMap에서 찾음

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);   // 없다면 404 상태 코드 반환
            return;
        }

        controller.process(request, response);      // 해당 컨트롤러 실행
    }
}
