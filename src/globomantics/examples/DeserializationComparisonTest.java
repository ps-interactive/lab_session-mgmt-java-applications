package globomantics.examples;

import org.apache.commons.io.input.ValidatingObjectInputStream;
import globomantics.model.UserSession;
import globomantics.vulnerable.VulnerableDeserializer;

import java.io.*;
import java.util.Date;

/**
 * This class compares vulnerable and secure deserialization approaches.
 * It demonstrates the differences and security implications.
 */
public class DeserializationComparisonTest {

    /**
     * Demonstrates vulnerable deserialization.
     */
    public static void testVulnerableDeserialization() {
        System.out.println("\n=== Testing Vulnerable Deserialization ===");
        try {
            // Create a sample UserSession
            UserSession session = new UserSession("user1", "SESSION123");
            
            // Serialize it
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            oos.close();
            byte[] serializedData = bos.toByteArray();
            
            // Deserialize using vulnerable method
            UserSession deserializedSession = VulnerableDeserializer.deserializeUserSession(serializedData);
            System.out.println("Successfully deserialized legitimate object: " + deserializedSession);
            
            System.out.println("\nVULNERABILITY ANALYSIS:");
            System.out.println("- The vulnerable deserializer accepts ANY serialized class");
            System.out.println("- An attacker can craft malicious serialized objects");
            System.out.println("- These objects could execute arbitrary code when deserialized");
            System.out.println("- This is a critical security vulnerability");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates secure deserialization using ValidatingObjectInputStream.
     */
    public static void testSecureDeserialization() {
        System.out.println("\n=== Testing Secure Deserialization ===");
        try {
            // Create a sample UserSession
            UserSession session = new UserSession("user2", "SESSION456");
            
            // Serialize it
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            oos.close();
            byte[] serializedData = bos.toByteArray();
            
            // Secure deserialization
            try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedData)) {
                // Create ValidatingObjectInputStream with whitelist
                ValidatingObjectInputStream vois = new ValidatingObjectInputStream(bis);
                
                // Configure whitelist of allowed classes
                vois.accept(UserSession.class);
                vois.accept(String.class);
                vois.accept(Date.class);
                
                // Safe deserialization
                Object obj = vois.readObject();
                System.out.println("Successfully deserialized using whitelist: " + obj);
            }
            
            System.out.println("\nSECURITY ANALYSIS:");
            System.out.println("- ValidatingObjectInputStream only allows explicitly whitelisted classes");
            System.out.println("- Attempts to deserialize non-whitelisted classes throw exceptions");
            System.out.println("- This prevents deserialization attacks");
            System.out.println("- This is a secure implementation");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Main method to run the comparison.
     */
    public static void main(String[] args) {
        System.out.println("DESERIALIZATION SECURITY COMPARISON");
        System.out.println("==================================");
        
        testVulnerableDeserialization();
        testSecureDeserialization();
        
        System.out.println("\n=== Conclusion ===");
        System.out.println("Always use ValidatingObjectInputStream or similar mechanisms");
        System.out.println("to restrict which classes can be deserialized.");
    }
}
