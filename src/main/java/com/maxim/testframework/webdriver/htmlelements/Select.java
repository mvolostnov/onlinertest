package com.maxim.testframework.webdriver.htmlelements;


import org.openqa.selenium.NoSuchElementException;


public class Select extends Element {
    public Select(String locator) {
        super(locator);
    }

    public Select selectValue(){
//        selectByValue();
        return new Select(locator);

    }

    public boolean isSelected(){
        try {
            if (getElement().isSelected()){
                return true;
            }
        }catch (NoSuchElementException e){
            return false;
        }
        return false;
    }

    /*
    *Getting selected option @label
     */
    public String getSelectedOption(){
        return new org.openqa.selenium.support.ui.Select(getElement()).getFirstSelectedOption().getText();
    }

    /*
     *Getting selected option @value
     */
    public String getSelectedValue(){
        return new org.openqa.selenium.support.ui.Select(getElement()).getFirstSelectedOption().getAttribute("value");
    }

    /*
     *Select specific option by their @textLabel
     */
    public void selectOption(final String textLabel){
        new org.openqa.selenium.support.ui.Select(getElement()).selectByVisibleText(textLabel);
    }

    /*
     *Select specific option by their @textLabel
     */
    public void selectValue(final String attrValue){
        new org.openqa.selenium.support.ui.Select(getElement()).selectByValue(attrValue);
    }

    public Select waitForToBeDisplayed() {
        super.waitForToBeDisplayed();
        return this;
    }


}
