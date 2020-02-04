package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import com.maxim.testframework.webdriver.htmlelements.Element;
import lombok.extern.log4j.Log4j;

@Log4j
public class OnlinerHomePage extends WebDriverInstance {

    private final Element topMenuSectionTemplate = new Element("//nav[@class='b-top-navigation']//a[span[contains(text(),'%s')]]");

    public OnlinerCatalogPage openTopMenuSection(String topMenuSectionName) {
        topMenuSectionTemplate.setLocatorVariable(topMenuSectionName).waitForToBeDisplayed().click();
        log.info("Open Top Menu Section: " + topMenuSectionName);
        return new OnlinerCatalogPage();
    }

}
