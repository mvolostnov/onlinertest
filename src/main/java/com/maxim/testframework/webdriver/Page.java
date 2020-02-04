package com.maxim.testframework.webdriver;

import com.maxim.testframework.webdriver.WebDriverInstance;
import lombok.extern.log4j.Log4j;

import java.util.concurrent.TimeUnit;

@Log4j
public class Page extends WebDriverInstance {
    /**
     * Page interaction method
     */
    public String getTitle(){
        return driver.getTitle();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void refresh(){
        log.info("[Page] Refresh page");
        driver.navigate().refresh();
    }

    public boolean containsText(String text){
        if (driver.getPageSource().contains(text)){
            return true;
        } else {
            return false;
        }
    }

    public void waitForPageToLoad(int timeSecond){
        driver.manage().timeouts().pageLoadTimeout(timeSecond, TimeUnit.SECONDS);
    }

    public void selectWindow(String iframeName){
        driver.switchTo().window(iframeName);
    }
}
