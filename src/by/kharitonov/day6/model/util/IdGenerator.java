package by.kharitonov.day6.model.util;

import java.util.UUID;

public class IdGenerator {
    private IdGenerator() {
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
