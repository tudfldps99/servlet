package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    // ctrl + o 로 function "service" 검색 -> protected 선택
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(req, resp);

        // ctrl + alt + m 로 method 로 분리 가능
        printStartLine(request);

        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        // 요청 url = http://localhost:8080/request-header?username=hello
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod());             // GET
        System.out.println("request.getProtocol() = " + request.getProtocol());         // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme());             // http
        System.out.println("request.getRequestURL() = " + request.getRequestURL());     // http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());     // /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString());   // username=hello
        System.out.println("request.isSecure() = " + request.isSecure());               // false - https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        // 과거 사용하던 방식
        // Enumeration<String> headerNames = request.getHeaderNames();
        // while (headerNames.hasMoreElements()) {
        //     String headerName = headerNames.nextElement();
        //     System.out.println(headerName + ": " + request.getHeader(headerName));

             // 출력
             // host: localhost:8080
             // connection: keep-alive
             // cache-control: max-age=0
             // sec-ch-ua: "Not/A)Brand";v="8", "Chromium";v="126", "Google Chrome";v="126"
             // sec-ch-ua-mobile: ?0
             // sec-ch-ua-platform: "Windows"
             // upgrade-insecure-requests: 1
             // user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36
             // accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
             //              sec-fetch-site: none
             //              sec-fetch-mode: navigate
             //              sec-fetch-user: ?1
             //              sec-fetch-dest: document
             //              accept-encoding: gzip, deflate, br, zstd
             //              accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
         // }

        // 최근 사용하는 방식
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");

        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더       // request.getServerName() = localhost
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더       // request.getServerPort() = 8080
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));              // locale = ko_KR
                                                                                                    // locale = ko
                                                                                                    // locale = en_US
                                                                                                    // locale = en
        System.out.println("request.getLocale() = " + request.getLocale());                         // request.getLocale() = ko_KR
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());               // request.getContentType() = null  - GET 방식은 content 를 별도로 넘기지 않기 때문에 null 출력
        System.out.println("request.getContentLength() = " + request.getContentLength());           // request.getContentLength() = -1
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());   // request.getCharacterEncoding() = UTF-8
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); // request.getRemoteHost() = 0:0:0:0:0:0:0:1
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); // request.getRemoteAddr() = 0:0:0:0:0:0:0:1
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); // request.getRemotePort() = 55959
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName());   // request.getLocalName() = 0:0:0:0:0:0:0:1
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());   // request.getLocalAddr() = 0:0:0:0:0:0:0:1
        System.out.println("request.getLocalPort() = " + request.getLocalPort());   // request.getLocalPort() = 8080

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}