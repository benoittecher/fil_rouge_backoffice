package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servlet.LoginServlet;

import java.io.IOException;

@WebFilter(urlPatterns = "/utilisateurs/*")
public class AuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        if(session != null && session.getAttribute("utilisateur") != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            resp.sendRedirect(LoginServlet.URL);
        }
    }
}