package by.onliner.webapp.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class OnlinerCatalogPage {

    private WebDriver driver;

    public OnlinerCatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    @FindBy (xpath = "//span[text()='Красота и спорт']")
//    private WebElement usernameField = driver.findElement(By.name("user"));
    public WebElement catalogCategoryName (String catalogCategoryName) {
        return driver.findElement(By.xpath(String.format("//ul[contains(@class,'catalog-navigation-classifier')]//span[text()='%s']", catalogCategoryName)));
    }

    public WebElement catalogSubCategoryName(String catalogSubCategoryName) {
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(By.xpath(String.format("//div[@class='catalog-navigation-list  catalog-navigation-list_active catalog-navigation-list_opened']//div[contains(text(),'%s')]"
                        , catalogSubCategoryName))));
    return driver.findElement(By.xpath(String.format("//div[@class='catalog-navigation-list  catalog-navigation-list_active catalog-navigation-list_opened']//div[contains(text(),'%s')]"
            , catalogSubCategoryName)));
    }

    public WebElement productType(String productType) {
        return driver.findElement(By.xpath(String.format("//div[@class='catalog-navigation-list  catalog-navigation-list_active catalog-navigation-list_opened']//span[contains(text(),'%s')]"
                , productType)));
    }

    public OnlinerCatalogPage selectCatalogCategory (String catalogCategoryName) {
        catalogCategoryName(catalogCategoryName).click();
        log.info("Select Catalog Category: " + catalogCategoryName);

        return this;
    }

    public OnlinerCatalogPage selectCatalogSubCategory(String catalogSubCategoryName) {
        catalogSubCategoryName(catalogSubCategoryName).click();
        log.info("Select Catalog SubCategory: " + catalogSubCategoryName);

        return this;
    }

    public RadiocontrolAirPage selectProductType(String productType) {
        productType(productType).click();
        log.info("Select Catalog SubCategory: " + productType);

        return new RadiocontrolAirPage(driver);
    }

}