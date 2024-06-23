package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 프론트 컨트롤러
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")      // /front-controller/v4 를 포함한 하위 모든 요청은 이 서블릿에서 받아들임
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 생성자
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // /front-controller/v4/members
        String requestURI = request.getRequestURI();                
        ControllerV4 controller = controllerMap.get(requestURI);    // requestURI를 조회하여 실제 호출 할 컨트롤러를 controllerMap에서 찾음

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);   // 없다면 404 상태 코드 반환
            return;
        }

        Map<String, String> paramMap = createParamMap(request);     // Ctrl + Alt + M 단축키를 통해 메소드로 추출
        Map<String, Object> model = new HashMap<>();    // 추가

        String viewName = controller.process(paramMap, model);
        MyView view = ViewResolver(viewName);       // "/WEB-INF/views/new-form.jsp"

        view.render(model, request, response);
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
