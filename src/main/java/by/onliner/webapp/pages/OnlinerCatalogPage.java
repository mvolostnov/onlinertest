package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import com.maxim.testframework.webdriver.htmlelements.Element;
import lombok.extern.log4j.Log4j;

@Log4j
public class OnlinerCatalogPage extends WebDriverInstance {

//    public WebElement catalogCategoryName (String catalogCategoryName) {
//        return driver.findElement(By.xpath(String.format("//ul[contains(@class,'catalog-navigation-classifier')]//span[text()='%s']", catalogCategoryName)));
//    }
    private final Element catalogCategoryNameTemplate = new Element("//ul[contains(@class,'catalog-navigation-classifier')]//span[text()='%s']");
    private final Element catalogSubCategoryNameTemplate = new Element("//div[@class='catalog-navigation-list  catalog-navigation-list_active catalog-navigation-list_opened']//div[contains(text(),'%s')]");
    private final Element productTypeTemplate = new Element("//div[@class='catalog-navigation-list  catalog-navigation-list_active catalog-navigation-list_opened']//span[contains(text(),'%s')]");

    public OnlinerCatalogPage selectCatalogCategory (String catalogCategoryName) {
        catalogCategoryNameTemplate.setLocatorVariable(catalogCategoryName).waitForToBeDisplayed().click();
        log.info("Select Catalog Category: " + catalogCategoryName);
        return this;
    }

    public OnlinerCatalogPage selectCatalogSubCategory(String catalogSubCategoryName) {
        catalogSubCategoryNameTemplate.setLocatorVariable(catalogSubCategoryName).click();
        log.info("Select Catalog SubCategory: " + catalogSubCategoryName);
        return this;
    }

    public RadiocontrolAirPage selectProductType(String productType) {
        productTypeTemplate.setLocatorVariable(productType).click();
        log.info("Select Catalog SubCategory: " + productType);
        return new RadiocontrolAirPage();
    }

}