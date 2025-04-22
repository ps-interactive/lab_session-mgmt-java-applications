package globomantics.examples;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * This class demonstrates secure cookie configuration.
 */
public class SecureCookieExample {
    
    /**
     * Creates a session cookie with secure settings.
     */
    public static Cookie createSecureCookie(String sessionId) {
        // Create a basic cookie
        Cookie cookie = new Cookie("SESSIONID", sessionId);
        cookie.setMaxAge(3600); // 1 hour
        cookie.setPath("/");
        
        // Add security attributes
        cookie.setHttpOnly(true);  // Prevents JavaScript access
        cookie.setSecure(true);    // Ensures HTTPS-only transmission
        
        return cookie;
    }
    
    /**
     * Adds a secure cookie to an HTTP response, including the SameSite attribute.
     */
    public static void addSecureCookie(HttpServletResponse response, String sessionId) {
        Cookie cookie = createSecureCookie(sessionId);
        response.addCookie(cookie);
        
        // Set SameSite attribute (not directly supported by Cookie class)
        response.setHeader("Set-Cookie", cookie.getName() + "=" + cookie.getValue() + 
                          "; HttpOnly; Secure; SameSite=Strict; Path=" + cookie.getPath() +
                          "; Max-Age=" + cookie.getMaxAge());
    }
    
    /**
     * Explain security attributes.
     */
    public static void explainSecurityAttributes() {
        System.out.println("Cookie Security Attributes:");
        System.out.println("1. HttpOnly - Prevents JavaScript access to cookies");
        System.out.println("2. Secure - Ensures cookies are only sent over HTTPS");
        System.out.println("3. SameSite=Strict - Prevents cookies from being sent in cross-site requests");
    }
    
    /**
     * Demonstrates secure cookie configuration.
     */
    public static void main(String[] args) {
        explainSecurityAttributes();
    }
}
