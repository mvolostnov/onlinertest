package com.maxim.testframework.webdriver;

import org.openqa.selenium.WebDriver;
import lombok.extern.log4j.Log4j;

public class WebDriverInstance {
    protected static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        WebDriverInstance.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }



}
