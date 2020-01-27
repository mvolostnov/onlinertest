package by.onliner.utils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomNumeric {
    public static String randomNum(int count) {
        String generatedString = RandomStringUtils.randomNumeric(count);
        return (generatedString);
    }
}
