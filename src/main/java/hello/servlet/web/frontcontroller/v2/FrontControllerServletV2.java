package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 프론트 컨트롤러
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")      // /front-controller/v2 를 포함한 하위 모든 요청은 이 서블릿에서 받아들임
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // /front-controller/v2/members
        String requestURI = request.getRequestURI();                
        ControllerV2 controller = controllerMap.get(requestURI);    // requestURI를 조회하여 실제 호출 할 컨트롤러를 controllerMap에서 찾음

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);   // 없다면 404 상태 코드 반환
            return;
        }

        MyView view = controller.process(request, response);      // 해당 컨트롤러 실행
        view.render(request, response);
    }
}
