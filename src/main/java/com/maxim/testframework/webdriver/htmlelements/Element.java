package com.maxim.testframework.webdriver.htmlelements;

import com.maxim.testframework.webdriver.LocatorHandler;
import com.maxim.testframework.webdriver.WebDriverInstance;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import lombok.extern.log4j.Log4j;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

//@AllArgsConstructor
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

    public boolean isPresent() {
        try{
            if (getElement() != null) {
                return true;
        }
    } catch (NoSuchElementException | StaleElementReferenceException e) {
 //          log.error(e.getMessage(), e);
 //           System.out.println(e.getMessage(), e);
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
            //          log.error(e.getMessage(), e);
            //           System.out.println(e.getMessage(), e);
            return false;
        }
        return false;
    }

    public void click(){
        if (isVisible()){
            getElement().click();
            return;
        }
        throw new IllegalArgumentException("[Element] Element [" + locator + "] is NOT present in DOM or is NOT visible");
    }

    public Element waitForToBeDisplayed() {
        new WebDriverWait(driver, 10).until(visibilityOfElementLocated(lh.getByType(locator)));
        return this;
    }

    public Element selectValue(String locator){
        getElement().click();
        return this;
    }

    public Element setLocatorVariable(Object var) {
        return new Element(String.format(locator, var));
    }



}
