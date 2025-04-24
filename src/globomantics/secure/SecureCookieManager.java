package globomantics.secure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * This class demonstrates secure cookie configuration practices.
 */
public class SecureCookieManager {
    
    /**
     * Creates a session cookie with secure settings.
     * 
     * @param sessionId the session ID to store in the cookie
     * @return a Cookie object with the session ID and security attributes
     */
    public static Cookie createSessionCookie(String sessionId) {
        // Create a basic cookie
        Cookie cookie = new Cookie("SESSIONID", sessionId);
        cookie.setMaxAge(3600); // 1 hour
        cookie.setPath("/");
        
        // TODO: Add security attributes here
        
        return cookie;
    }
    
    /**
     * Adds the session cookie to an HTTP response.
     * 
     * @param response the HttpServletResponse to add the cookie to
     * @param sessionId the session ID to store in the cookie
     */
    public static void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = createSessionCookie(sessionId);
        response.addCookie(cookie);
        
        // TODO: Set SameSite attribute here
    }
}
