package com.uiss.home;

import java.util.UUID;

public class IDsGenerator {

    public static String generateIds() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("[^a-zA-Z0-9]", "").substring(0, 15);
    }
}
