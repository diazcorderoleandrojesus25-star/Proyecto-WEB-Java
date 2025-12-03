package com.Jobxpress.Jobxpress.Config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CacheConfig implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();

        // ⛔ NO aplicar a login ni recursos estáticos
        boolean isPublic =
                path.startsWith("/login") ||
                path.startsWith("/registro") ||
                path.startsWith("/css") ||
                path.startsWith("/js") ||
                path.startsWith("/images");

        if (!isPublic) {

           
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, no-transform");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            
            response.setHeader("Referrer-Policy", "no-referrer");
        }

        chain.doFilter(request, response);

        
        if (!isPublic) {
            response.getWriter().write("");
        }
    }
}
