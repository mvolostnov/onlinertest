package com.maxim.testframework.webdriver.htmlelements;

import com.maxim.testframework.webdriver.LocatorHandler;
import com.maxim.testframework.webdriver.WebDriverInstance;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


//@AllArgsConstructor
@Log4j
public class Element extends WebDriverInstance {

    //@Getter
    protected static final LocatorHandler lh = new LocatorHandler();
    protected String locator;


    public Element(String locator) {
        this.locator = locator;
    }

    protected WebElement getElement() {
        return driver.findElement(lh.getByType(locator));
    }

    public Element setLocatorVariable(Object var) {
        return new Element(String.format(locator, var));
    }

    public boolean isPresent() {
        try{
            if (getElement() != null) {
                return true;
        }
    } catch (NoSuchElementException | StaleElementReferenceException e) {
           log.error(e.getMessage(), e);
            return false;
        }
        return false;
    }

    public boolean isVisible() {
        try{
            if (getElement().isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
                      log.error(e.getMessage(), e);
            return false;
        }
        return false;
    }

    public void click(){
            getElement().click();
        }

    public String getText(){
        return getElement().getText();
                    }

    public Element waitForToBeDisplayed() {
        new WebDriverWait(driver, 10).until(visibilityOfElementLocated(lh.getByType(locator)));
        return this;
    }

    public Element waitForToBeClickable() {
        new WebDriverWait(driver, 10).until(elementToBeClickable(lh.getByType(locator)));
        return this;
    }

    public Element scrollTo(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(lh.getByType(locator)));
        return this;
    }


    public Element clickMenuItem() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(lh.getByType(locator)));
        return this;
    }

    public Element listOfElements(){
        List<WebElement> element = driver.findElements(lh.getByType(locator));
        return this;
        //return new List<WebElement> =(driver.findElements(By.xpath("//div[@class='schema-product__part schema-product__part_2']//span[contains(text(),'р.')]")));
    }

    public Element mouseHover(){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement()).perform();
        return this;
    }


}
