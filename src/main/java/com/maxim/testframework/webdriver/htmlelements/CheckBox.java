package com.maxim.testframework.webdriver.htmlelements;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

public class CheckBox extends Element{

    public CheckBox(String locator) {
        super(locator);
    }

    public boolean isSelected() {
        try{
            if (getElement().isSelected()) {
                return true;
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            //          log.error(e.getMessage(), e);
            //           System.out.println(e.getMessage(), e);
            return false;
        }
        return false;
    }

    public void select() {
        if (!isSelected()) {
            getElement().click();
        }
    }

    public void deselect() {
        if (isSelected()) {
            getElement().click();
        }
    }

    public void set(boolean value) {
        if (value) {
            select();
        } else {
            deselect();
        }
    }

}
