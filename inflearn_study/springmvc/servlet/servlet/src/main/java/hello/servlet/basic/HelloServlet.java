package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain"); // header 정보
        response.setCharacterEncoding("utf-8");// header 정보
        response.getWriter().write("hello " + username);// http message body에 들어감
    }
}

/**HTTP 요청 메시지
 * POST /save HTTP/1.1
 * Host: localhost:8080
 * Content-Type: application/x-www-form-urlencoded
 *
 * username=kim&age=20
 *---------------------------------------------------
 * START LINE
 * - HTTP 메소드
 * - URL
 * - 쿼리 스트링
 * - 스키마, 프로토콜
 *
 * 헤더
 * - 헤더 정보
 *
 * 바디
 * - form 파라미터 형식 조회
 * - message body 데이터 직접 조회
 *
 * 임시 저장소 기능: 해당 HTTP 요청이 시작부터 끝날 때 까지 유지되는 임시 저장소 기능
 * - 저장: request.setAttribute(name, value);
 * - 조회: request.getAttribute(name);
 * 세션 관리기능
 * - request.getSession(create: true);
 *
 * (중요) HttpServletRequest request, HttpServletResponse response를 사용하는 이유는
 * -> HTTP 응답 메시지를 편리하게 사용하도록 도와주는 객체라는 점
 * */