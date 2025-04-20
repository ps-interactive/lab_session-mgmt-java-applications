# Secure Session Management for Java Applications

This repository contains exercise files for the Secure Session Management for Java Applications lab. The lab covers:

1. Implementing secure deserialization with ValidatingObjectInputStream
2. Configuring secure session cookies with HttpOnly, Secure, and SameSite attributes

## Structure

- `/src/globomantics/vulnerable/` - Contains vulnerable code implementations
- `/src/globomantics/secure/` - Contains files to implement secure solutions 
- `/src/globomantics/model/` - Contains model classes used in the exercises
- `/src/` - Contains test files to validate implementations

## Filetree
```
/
├── README.md
├── pom.xml
├── src/
│   ├── globomantics/
│   │   ├── vulnerable/
│   │   │   ├── VulnerableDeserializer.java
│   │   │   └── VulnerableCookieManager.java
│   │   ├── secure/
│   │   │   ├── SecureDeserializer.java
│   │   │   └── SecureCookieManager.java
│   │   └── model/
│   │       ├── UserSession.java
│   │       └── UserCredentials.java
│   ├── DeserializerTest.java
│   └── CookieManagerTest.java
├── Title 1.mdoc
├── Title 2.mdoc
├── Title 3.mdoc
├── Title 4.mdoc
├── Title 5.mdoc
├── Title 6.mdoc
├── Title 7.mdoc
└── lab-layout.vsix
```

## Prerequisites

- Java 11 or higher
- Basic understanding of Java web applications
- Familiarity with HTTP sessions and cookies
