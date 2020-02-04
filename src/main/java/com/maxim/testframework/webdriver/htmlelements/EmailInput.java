package com.maxim.testframework.webdriver.htmlelements;

import org.openqa.selenium.WebElement;

public class EmailInput extends Element {
    public EmailInput(String locator) {
        super(locator);
    }
    public void setEmailValue(String email) throws InterruptedException {

        WebElement emailInput = getElement();
        Thread.sleep(200);
        for(int i = 0; i < email.length(); i++){
            emailInput.sendKeys(String.valueOf(email.charAt(i)));
            Thread.sleep(20);
        }

    }

    public EmailInput waitForToBeDisplayed() {
        super.waitForToBeDisplayed();
        return this;
    }

}
