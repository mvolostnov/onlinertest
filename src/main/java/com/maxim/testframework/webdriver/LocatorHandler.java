package com.maxim.testframework.webdriver;

import org.openqa.selenium.By;

public class LocatorHandler {
    public enum LocatorType {
        XPATH,
        CSS_SELECTOR,
        ID,
        CLASS_NAME,
        NAME,
        TAG_NAME,
        LINK_TEXT,
        PARTIAL_LINK_TEXT,
        DOM
    }

    public final LocatorType getLocatorType(String locator) {
        if (locator.startsWith("xpath=") || locator.startsWith("/")) {
            return LocatorType.XPATH;
        } else if (locator.startsWith("css=") || locator.startsWith("#") || locator.startsWith(".")) {
            return LocatorType.CSS_SELECTOR;
        } else if (locator.startsWith("id=") || locator.contains(":id/")) {
            return LocatorType.ID;
        } else if (locator.startsWith("class=")) {
            return LocatorType.CLASS_NAME;
        } else if (locator.startsWith("name=")) {
            return LocatorType.NAME;
        } else if (locator.startsWith("tag=")) {
            return LocatorType.TAG_NAME;
        } else if (locator.startsWith("link=")) {
            return LocatorType.LINK_TEXT;
        } else if (locator.startsWith("dom")) {
            return LocatorType.DOM;
        } else {
            throw new IllegalArgumentException("[LocatorHandler] Equivalent selenium LocotorType.* for [" +locator+ "] locator is NOT found!");
        }
    }

    public By getByType(String locator) {
        switch (getLocatorType(locator)) {
            case XPATH:
                if (locator.startsWith("xpath=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.xpath(locator);
            case CSS_SELECTOR:
                if (locator.startsWith("css=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.cssSelector(locator);
            case ID:
                if (locator.startsWith("id=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.id(locator);
            case NAME:
                if (locator.startsWith("name=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.name(locator);
            case CLASS_NAME:
                if (locator.startsWith("class=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.className(locator);
            case TAG_NAME:
                if (locator.startsWith("tag=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.tagName(locator);
            case LINK_TEXT:
                if (locator.startsWith("link=")) {
                    locator = locator.split("=", 2)[1];
                }
                return By.linkText(locator);
            default:
                if (locator.startsWith("dom:name=")) {
                    locator = locator.split("=", 2)[1];
                    return By.xpath("//form[@name='" + locator + "']");
                } else if (locator.startsWith("dom:index=")) {
                    locator = locator.split("=", 2)[1];
                    return By.xpath("(//form)[" + locator + "]");
                }
        }
        throw new IllegalArgumentException("[LocatorHandler] Equivalent selenium By.* method for [" +locator+ "] locator is NOT found!");
    }

}

