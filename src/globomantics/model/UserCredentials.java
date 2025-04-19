package globomantics.model;

import java.io.Serializable;

/**
 * Class that represents user credentials.
 * This class is serializable and will be used in deserialization exercises.
 */
public class UserCredentials implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String hashedPassword;
    
    public UserCredentials(String username, String hashedPassword) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getHashedPassword() {
        return hashedPassword;
    }
}
