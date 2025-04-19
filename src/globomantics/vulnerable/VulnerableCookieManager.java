package globomantics.vulnerable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * This class demonstrates vulnerable cookie configuration practices.
 * It creates cookies without security attributes, making them vulnerable to various attacks.
 */
public class VulnerableCookieManager {
    
    /**
     * Creates a session cookie with vulnerable settings.
     * This method does NOT set HttpOnly, Secure, or SameSite attributes.
     * 
     * @param sessionId the session ID to store in the cookie
     * @return a Cookie object with the session ID
     */
    public static Cookie createSessionCookie(String sessionId) {
        // VULNERABLE: This cookie lacks security attributes
        Cookie cookie = new Cookie("SESSIONID", sessionId);
        cookie.setMaxAge(3600); // 1 hour
        cookie.setPath("/");
        
        // Missing security attributes:
        // - HttpOnly flag (prevents JavaScript access)
        // - Secure flag (ensures HTTPS-only transmission)
        // - SameSite attribute (prevents CSRF)
        
        return cookie;
    }
    
    /**
     * Adds the vulnerable session cookie to an HTTP response.
     * 
     * @param response the HttpServletResponse to add the cookie to
     * @param sessionId the session ID to store in the cookie
     */
    public static void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = createSessionCookie(sessionId);
        response.addCookie(cookie);
    }
}
