package com.maxim.testframework.webdriver.htmlelements;


import org.openqa.selenium.interactions.Actions;

public class Input extends Element {

    public Input(String locator){
        super(locator);
    }

    public void setValue(String value){
        getElement().sendKeys(value);
    }

    public Input setValue(int value){
        final String str = String.valueOf(value);
        final Actions actions = new Actions(driver);
        actions.moveToElement(getElement()).click().perform();
        for (int i=0; i<str.length(); i++) {
            getElement().sendKeys(String.valueOf(str.charAt(i)));
        }
        return this;
    }

    public Input setValue(double value){
        final String str = String.valueOf(value);
        final Actions actions = new Actions(driver);
        actions.moveToElement(getElement()).click().perform();
        for (int i=0; i<str.length(); i++) {
            getElement().sendKeys(String.valueOf(str.charAt(i)));
        }
        return this;
    }

    public void submit(){
        getElement().submit();
    }

    public Input clear(){
        getElement().submit();
        return this;
    }

    public Input waitForToBeDisplayed() {
        super.waitForToBeDisplayed();
        return this;
    }

}

