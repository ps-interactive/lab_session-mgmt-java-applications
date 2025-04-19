package globomantics.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class that represents a user session.
 * This class is serializable and will be used in deserialization exercises.
 */
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private Date loginTime;
    private String sessionId;
    
    public UserSession(String username, String sessionId) {
        this.username = username;
        this.sessionId = sessionId;
        this.loginTime = new Date();
    }
    
    public String getUsername() {
        return username;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    @Override
    public String toString() {
        return "UserSession{" +
                "username='" + username + '\'' +
                ", loginTime=" + loginTime +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
