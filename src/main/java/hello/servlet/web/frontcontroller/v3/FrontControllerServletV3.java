package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 프론트 컨트롤러
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")      // /front-controller/v3 를 포함한 하위 모든 요청은 이 서블릿에서 받아들임
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // /front-controller/v3/members
        String requestURI = request.getRequestURI();                
        ControllerV3 controller = controllerMap.get(requestURI);    // requestURI를 조회하여 실제 호출 할 컨트롤러를 controllerMap에서 찾음

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);   // 없다면 404 상태 코드 반환
            return;
        }

        Map<String, String> paramMap = createParamMap(request);     // Ctrl + Alt + M 단축키를 통해 메소드로 추출
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();         // 논리이름 new-form
        MyView view = ViewResolver(viewName);       // "/WEB-INF/views/new-form.jsp"

        view.render(mv.getModel(), request, response);
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private static MyView ViewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
