package roomescape.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import roomescape.auth.JwtTokenProvider;

public class AdminInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    public AdminInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = getTokenFromCookie(request);
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String role = getUserRoleFromToken(token);
        if (!"ADMIN".equals(role)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (var cookie : request.getCookies()) {
            if ("token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public String getUserRoleFromToken(String token) {
        return jwtTokenProvider.getClaimValue(token, "role");
    }
}
