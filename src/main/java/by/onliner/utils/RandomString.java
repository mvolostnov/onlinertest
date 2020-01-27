package by.onliner.utils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomString {

    public static String randomString(int count) {
        String generatedstring = RandomStringUtils.randomAlphabetic(count);
        return (generatedstring);
    }
}