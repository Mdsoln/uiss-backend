package com.uiss.home;

import java.util.UUID;

public class HomeIdGenerator {

    public static String generateHomeDetailsId() {
        // Generate a random UUID
        String uuid = UUID.randomUUID().toString();

        // Extract alphanumeric characters and take the first six characters
        String alphanumeric = uuid.replaceAll("[^a-zA-Z0-9]", "").substring(0, 6);

        // Prefix with "hm" and return
        return "hm" + alphanumeric;
    }
}
