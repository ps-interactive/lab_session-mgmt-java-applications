package globomantics.examples;

import org.apache.commons.io.input.ValidatingObjectInputStream;
import globomantics.model.UserSession;
import globomantics.model.UserCredentials;

import java.io.*;
import java.util.Date;

/**
 * This class demonstrates secure deserialization using ValidatingObjectInputStream.
 * It only allows trusted classes to be deserialized.
 */
public class SecureDeserializationExample {
    
    /**
     * Securely deserializes an object by only allowing specific classes.
     */
    public static Object secureDeserialize(byte[] data) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data)) {
            // Create ValidatingObjectInputStream with whitelist
            ValidatingObjectInputStream vois = new ValidatingObjectInputStream(bis);
            
            // Configure whitelist of allowed classes
            vois.accept(UserSession.class);
            vois.accept(UserCredentials.class);
            vois.accept(String.class);
            vois.accept(Date.class);
            
            // Safe deserialization
            return vois.readObject();
        } catch (Exception e) {
            System.out.println("Error during secure deserialization: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Demonstrates how to use the secure deserializer.
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
            
            // Deserialize using our secure method
            Object result = secureDeserialize(serializedData);
            System.out.println("Securely deserialized: " + result);
            
            // Try to deserialize a non-whitelisted class (would fail in real scenarios)
            System.out.println("In a real attack, deserialization of non-whitelisted classes would fail");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
