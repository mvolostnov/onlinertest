package com.maxim.testframework.webdriver.htmlelements;

import org.openqa.selenium.By;


public class Select extends Element {
    public Select(String locator) {
        super(locator);
    }

    public Select selectValue(){
//        selectByValue();
        return new Select(locator);

    }

}
