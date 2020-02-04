package com.maxim.testframework.webdriver;

import by.onliner.test.BaseTest;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.Alert;
import org.openqa.selenium.remote.BrowserType;

@Log4j
public class Browser extends BaseTest {
    private Alert alert;
/*
    public Browser(BrowserType browserType){
        super(browserType);
    }
 */
    public Browser openUrl(String url){
        log.info("[Browser] Navigate to url [" + url + "].");
        return this;
    }

}
