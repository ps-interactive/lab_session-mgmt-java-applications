package globomantics.secure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * This class demonstrates secure cookie configuration practices.
 * Students will modify this file to implement secure cookie attributes.
 */
public class SecureCookieManager {
    
    /**
     * Creates a session cookie with secure settings.
     * 
     * TODO: Implement secure cookie configuration by adding:
     * - HttpOnly flag (prevents JavaScript access)
     * - Secure flag (ensures HTTPS-only transmission)
     * - SameSite attribute (prevents CSRF)
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
     * For SameSite attribute, you'll need to use this method
     * since the Cookie class doesn't directly support SameSite.
     * 
     * @param response the HttpServletResponse to add the cookie to
     * @param sessionId the session ID to store in the cookie
     */
    public static void addSessionCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = createSessionCookie(sessionId);
        response.addCookie(cookie);
        
        // TODO: For SameSite attribute, you can use the following:
        // response.setHeader("Set-Cookie", cookie.getName() + "=" + cookie.getValue() + 
        //                    "; HttpOnly; Secure; SameSite=Strict; Path=" + cookie.getPath());
    }
}
