package by.onliner.webapp;

import by.onliner.test.BaseTest;
import by.onliner.webapp.pages.OnlinerHomePage;

public class WebApplication extends BaseTest {

    public OnlinerHomePage openHomePage() {

        return new OnlinerHomePage(driver);

    }
/*
    public RCHomePage RCHomePage() {

        return new RCHomePage(driver);

    }

 */
}
