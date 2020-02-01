package com.maxim.testframework.webdriver.htmlelements;

public class Link extends Element{
    public Link(String locator) {
        super(locator);
    }

    public String getReference() {
        return getElement().getAttribute("href");
    }
}
