package com.tencent.commons.utils;

import java.util.UUID;

public abstract class UUIDGenerator {


    private static IdsRandomStringGenerator randomStringGenerator = new IdsRandomStringGenerator(8);


    private UUIDGenerator() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "") + randomStringGenerator.generate();
    }


    /**
     * Always return a random positive number
     */
    public static long generateNumber() {
        long number;
        while (true) {
            number = UUID.randomUUID().getMostSignificantBits();
            if (number > 0) {
                return number;
            }
        }
    }

}
