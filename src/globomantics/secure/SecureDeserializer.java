package globomantics.secure;

import globomantics.model.UserSession;
import java.io.*;

/**
 * This class demonstrates secure deserialization practices.
 * Students will modify this file to implement secure deserialization.
 */
public class SecureDeserializer {
    
    /**
     * Deserializes a UserSession object from a byte array.
     * 
     * TODO: Implement secure deserialization to only allow trusted classes.
     * 
     * @param serializedData the serialized data as a byte array
     * @return the deserialized UserSession object, or null if deserialization fails
     */
    public static UserSession deserializeUserSession(byte[] serializedData) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            
            // INSECURE: This code accepts any serialized class
            Object obj = ois.readObject();
            
            if (obj instanceof UserSession) {
                return (UserSession) obj;
            } else {
                System.out.println("Unexpected object type: " + obj.getClass().getName());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error deserializing user session: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Demonstrates how to use the deserializer.
     */
    public static void main(String[] args) {
        try {
            // Create a sample UserSession
            UserSession session = new UserSession("bob", "SESSION456");
            
            // Serialize it
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            oos.close();
            byte[] serializedData = bos.toByteArray();
            
            // Deserialize it using the method that needs to be secured
            UserSession deserializedSession = deserializeUserSession(serializedData);
            System.out.println("Deserialized session: " + deserializedSession);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
