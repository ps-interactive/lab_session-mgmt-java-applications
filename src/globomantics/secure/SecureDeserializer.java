package globomantics.secure;

import globomantics.model.UserSession;
import java.io.*;

/**
 * This class demonstrates secure deserialization practices.
 */
public class SecureDeserializer {
    
    /**
     * Deserializes a UserSession object from a byte array.
     * 
     * @param serializedData the serialized data as a byte array
     * @return the deserialized UserSession object, or null if deserialization fails
     */
    public static UserSession deserializeUserSession(byte[] serializedData) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
             ObjectInputStream ois = new ObjectInputStream(bis); {
            
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
     * Custom implementation of an ObjectInputStream that validates classes.
     */
    private static class CustomValidatingObjectInputStream extends ObjectInputStream {
        
        public CustomValidatingObjectInputStream(InputStream in) throws IOException {
            super(in);
        }
        
        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            String className = desc.getName();
            
            // Only allow specific trusted classes
            if (className.equals("globomantics.model.UserSession") || 
                className.equals("java.lang.String")) {
                return super.resolveClass(desc);
            } else {
                throw new ClassNotFoundException("Class not allowed for deserialization: " + className);
            }
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
