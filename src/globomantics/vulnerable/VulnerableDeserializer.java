package globomantics.vulnerable;

import globomantics.model.UserSession;

import java.io.*;

/**
 * This class demonstrates vulnerable deserialization practices.
 * It accepts any serialized class, making it vulnerable to attacks.
 */
public class VulnerableDeserializer {
    
    /**
     * Deserializes a UserSession object from a byte array without any validation.
     * This is VULNERABLE to deserialization attacks!
     * 
     * @param serializedData the serialized data as a byte array
     * @return the deserialized UserSession object, or null if deserialization fails
     */
    public static UserSession deserializeUserSession(byte[] serializedData) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            
            // VULNERABLE: This code accepts any serialized class
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
     * Demonstrates how to use the vulnerable deserializer.
     */
    public static void main(String[] args) {
        try {
            // Create a sample UserSession
            UserSession session = new UserSession("alice", "SESSION123");
            
            // Serialize it
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            oos.close();
            byte[] serializedData = bos.toByteArray();
            
            // Deserialize it using the vulnerable method
            UserSession deserializedSession = deserializeUserSession(serializedData);
            System.out.println("Deserialized session: " + deserializedSession);
            
            // In a real attack, an attacker would craft malicious serialized data
            // that could execute arbitrary code when deserialized
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
