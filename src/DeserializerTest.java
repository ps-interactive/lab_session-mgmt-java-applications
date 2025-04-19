import globomantics.model.UserSession;
import globomantics.secure.SecureDeserializer;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Test file for validating SecureDeserializer implementation.
 * This checks if your implementation properly validates classes during deserialization.
 */
public class DeserializerTest {

    public static void main(String[] args) {
        try {
            System.out.println("Testing secure deserialization implementation...");
            
            // Test with valid class
            testValidClass();
            
            // Try to provide a path to a malicious class if available
            // This should fail with a proper security implementation
            testInvalidClass();
            
            System.out.println("All tests completed. Verify the output to ensure security.");
        } catch (Exception e) {
            System.out.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testValidClass() throws Exception {
        System.out.println("\nTest 1: Deserializing valid UserSession class");
        
        // Create and serialize a UserSession
        UserSession session = new UserSession("testuser", "SESSION789");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(session);
        oos.close();
        byte[] serializedData = bos.toByteArray();
        
        // Try to deserialize with secure implementation
        UserSession result = SecureDeserializer.deserializeUserSession(serializedData);
        
        if (result != null && "testuser".equals(result.getUsername())) {
            System.out.println("✓ Success: Valid UserSession was properly deserialized");
        } else {
            System.out.println("✗ Failed: Valid UserSession could not be deserialized");
            System.out.println("  Make sure your whitelist includes UserSession");
        }
    }
    
    private static void testInvalidClass() {
        System.out.println("\nTest 2: Attempting to deserialize invalid class");
        System.out.println("Note: This test should fail with security exceptions");
        
        try {
            // In a real test, we'd try to deserialize a dangerous class
            // For lab purposes, we'll just print guidance
            System.out.println("Your implementation should:");
            System.out.println("1. Use ValidatingObjectInputStream");
            System.out.println("2. Configure it to only accept trusted classes (e.g., UserSession, UserCredentials)");
            System.out.println("3. Reject all other classes during deserialization");
            
            System.out.println("\nIf you've implemented it correctly, unrecognized classes will cause:");
            System.out.println("- ClassNotFoundException, or");
            System.out.println("- InvalidClassException");
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
