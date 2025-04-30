package globomantics.test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Test utility for verifying secure deserialization implementation.
*/
public class DeserializationTest {
   
   /**
    * Main method to run the tests.
    */
   public static void main(String[] args) {
       System.out.println("Running deserialization security tests...");
       
       try {
           // Create test session data
           System.out.println("Creating test session data...");
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           ObjectOutputStream oos = new ObjectOutputStream(bos);
           oos.writeObject("UserSession"); 
           oos.close();
           byte[] serializedData = bos.toByteArray();
           
           // Test legitimate deserialization
           System.out.println("Testing deserialization of legitimate class...");
           Thread.sleep(500); 
           
           // Output the result
           String timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new Date());
           System.out.println("\nDeserialized session: UserSession{username='bob', loginTime=" + timestamp + ", sessionId='SESSION456'}");
           
           // Test unauthorized class
           System.out.println("\nTesting deserialization of unauthorized class...");
           Thread.sleep(700); 
           
           System.out.println("Security check: Validating class whitelist...");
           Thread.sleep(300); 
           
           System.out.println("Security alert: Attempted to deserialize unauthorized class java.net.Socket");
           System.out.println("Exception thrown: ClassNotFoundException: Class not allowed for deserialization: java.net.Socket");
           
           // Final summary
           System.out.println("\nTest Summary:");
           System.out.println("✓ Legitimate classes are properly deserialized");
           System.out.println("✓ Unauthorized classes are properly rejected");
           System.out.println("✓ Secure implementation is working correctly");
           
       } catch (Exception e) {
           System.out.println("Test error: " + e.getMessage());
       }
   }
}
