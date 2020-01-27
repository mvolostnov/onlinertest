package by.onliner.webapp;

import by.onliner.test.BaseTest;
import by.onliner.webapp.pages.OnlinerHomePage;
import by.onliner.webapp.pages.RCCreateAccountPage;

public class WebApplication{

    public OnlinerHomePage openHomePage() {
        return new OnlinerHomePage();
    }

    public RCCreateAccountPage openRCCreateAccountPage() {
        return new RCCreateAccountPage();
    }

}
