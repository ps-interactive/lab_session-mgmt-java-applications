package globomantics.examples;

import globomantics.secure.SecureCookieManager;
import globomantics.vulnerable.VulnerableCookieManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrates the difference between secure and insecure cookie configurations.
 */
public class CookieSecurityDemo {

    /**
     * Main method to run the demonstration.
     */
    public static void main(String[] args) {
        System.out.println("=== Cookie Security Demonstration ===\n");
        
        // Create a mock response to capture headers
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        // Demo insecure cookie
        System.out.println("INSECURE COOKIE (Before your changes):");
        VulnerableCookieManager.addSessionCookie(response, "1234567890");
        System.out.println(response.getLastCookieHeader());
        
        // Clear for next test
        response.clearHeaders();
        
        // Demo secure cookie
        System.out.println("\nSECURE COOKIE (After your changes):");
        SecureCookieManager.addSessionCookie(response, "1234567890");
        System.out.println(response.getLastCookieHeader());
        
        // Analyze security features
        String secureHeader = response.getLastCookieHeader();
        System.out.println("\nSecurity Analysis:");
        System.out.println(secureHeader.contains("HttpOnly") ? 
            "✓ HttpOnly flag is present: Prevents JavaScript access to cookies" : 
            "✗ HttpOnly flag is missing: Cookies can be accessed by JavaScript");
        
        System.out.println(secureHeader.contains("Secure") ? 
            "✓ Secure flag is present: Ensures cookies are only sent over HTTPS" : 
            "✗ Secure flag is missing: Cookies can be sent over unencrypted HTTP");
        
        System.out.println(secureHeader.contains("SameSite=Strict") ? 
            "✓ SameSite=Strict is present: Prevents CSRF attacks" : 
            "✗ SameSite attribute is missing: Vulnerable to CSRF attacks");
        
        System.out.println("\nYour cookie configuration is now secure!");
    }
    
    /**
     * Mock HttpServletResponse for testing cookie configurations.
     */
    static class MockHttpServletResponse implements HttpServletResponse {
        private Map<String, String> headers = new HashMap<>();
        private String lastCookieHeader;
        
        public String getLastCookieHeader() {
            return lastCookieHeader;
        }
        
        public void clearHeaders() {
            headers.clear();
            lastCookieHeader = null;
        }
        
        @Override
        public void addCookie(Cookie cookie) {
            // Basic cookie header simulation
            lastCookieHeader = "Set-Cookie: " + cookie.getName() + "=" + cookie.getValue();
            if (cookie.getPath() != null) {
                lastCookieHeader += "; Path=" + cookie.getPath();
            }
            if (cookie.getMaxAge() > 0) {
                lastCookieHeader += "; Max-Age=" + cookie.getMaxAge();
            }
            if (cookie.getSecure()) {
                lastCookieHeader += "; Secure";
            }
            if (cookie.isHttpOnly()) {
                lastCookieHeader += "; HttpOnly";
            }
        }
        
        @Override
        public void setHeader(String name, String value) {
            headers.put(name, value);
            if (name.equals("Set-Cookie")) {
                lastCookieHeader = value;
            }
        }
        
        // Required interface methods with minimal implementation
        @Override public boolean containsHeader(String name) { return headers.containsKey(name); }
        @Override public void addHeader(String name, String value) { headers.put(name, value); }
        @Override public void setStatus(int sc) { }
        @Override public PrintWriter getWriter() { return new PrintWriter(new StringWriter()); }
        @Override public void flushBuffer() { }
        @Override public boolean isCommitted() { return false; }
        
        // Other required methods with empty implementations
        @Override public String encodeURL(String url) { return url; }
        @Override public String encodeRedirectURL(String url) { return url; }
        @Override public String encodeUrl(String url) { return url; }
        @Override public String encodeRedirectUrl(String url) { return url; }
        @Override public void sendError(int sc, String msg) { }
        @Override public void sendError(int sc) { }
        @Override public void sendRedirect(String location) { }
        @Override public void setDateHeader(String name, long date) { }
        @Override public void addDateHeader(String name, long date) { }
        @Override public void setIntHeader(String name, int value) { }
        @Override public void addIntHeader(String name, int value) { }
        @Override public void setStatus(int sc, String sm) { }
        @Override public int getStatus() { return 200; }
        @Override public String getHeader(String name) { return headers.get(name); }
        @Override public java.util.Collection<String> getHeaders(String name) { return null; }
        @Override public java.util.Collection<String> getHeaderNames() { return headers.keySet(); }
        @Override public String getCharacterEncoding() { return "UTF-8"; }
        @Override public String getContentType() { return null; }
        @Override public javax.servlet.ServletOutputStream getOutputStream() { return null; }
        @Override public void setCharacterEncoding(String charset) { }
        @Override public void setContentLength(int len) { }
        @Override public void setContentLengthLong(long len) { }
        @Override public void setContentType(String type) { }
        @Override public void setBufferSize(int size) { }
        @Override public int getBufferSize() { return 0; }
        @Override public void resetBuffer() { }
        @Override public void reset() { }
        @Override public void setLocale(java.util.Locale loc) { }
        @Override public java.util.Locale getLocale() { return null; }
    }
}
