package com.maxim.testframework.webdriver.htmlelements;



public class Input extends Element {

    public Input(String locator) {
        super(locator);
    }

    public void setValue(String text){
        getElement().sendKeys(text);
    }

    public Input waitForToBeDisplayed(){
        super.waitForToBeDisplayed();
        return this;
    }


}

