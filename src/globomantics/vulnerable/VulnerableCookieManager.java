package globomantics.vulnerable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class VulnerableCookieManager {

    public static Cookie createSessionCookie(String sessionId) {
        // VULNERABLE: This cookie lacks security attributes
        Cookie cookie = new Cookie("SESSIONID", sessionId);
        cookie.setMaxAge(3600); // 1 hour
        cookie.setPath("/");
        
        
        return cookie;
    }
    
    public static void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = createSessionCookie(sessionId);
        response.addCookie(cookie);
    }
}
