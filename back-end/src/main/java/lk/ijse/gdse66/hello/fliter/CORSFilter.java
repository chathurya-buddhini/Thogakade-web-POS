package lk.ijse.gdse66.hello.fliter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String origin = req.getHeader("origin");
        if(origin == null){
            res.sendError(HttpServletResponse.SC_BAD_REQUEST,"CORS Policy Violation");
            return;
        }
        res.addHeader("Access-Control-Allow-Origin", origin);
        res.addHeader("Access-Control-Allow-Headers", "Content-Type");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTION, HEAD");
        chain.doFilter(req,res);
    }
}
