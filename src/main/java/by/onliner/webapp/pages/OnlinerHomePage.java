package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class OnlinerHomePage extends WebDriverInstance {

    public WebElement topMenuSection(String topMenuSectionName) {
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(By.xpath(String.format("//nav[@class='b-top-navigation']//a[span[contains(text(),'%s')]]", topMenuSectionName))));
        return driver.findElement(By.xpath(String.format("//nav[@class='b-top-navigation']//a[span[contains(text(),'%s')]]", topMenuSectionName)));
    }

    public OnlinerCatalogPage openTopMenuSection(String topMenuSectionName) {
        topMenuSection(topMenuSectionName).click();
        log.info("Open Top Menu Section: " + topMenuSectionName);

        return new OnlinerCatalogPage();

    }

}
