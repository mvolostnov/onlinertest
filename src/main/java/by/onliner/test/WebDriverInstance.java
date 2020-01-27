package by.onliner.test;

import org.openqa.selenium.WebDriver;

public class WebDriverInstance {
    protected static WebDriver driver;

    public static void setDriver(WebDriver driver){
        WebDriverInstance.driver = driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
