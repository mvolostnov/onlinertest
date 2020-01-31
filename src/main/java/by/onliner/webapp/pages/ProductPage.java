package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class ProductPage extends WebDriverInstance {

    private final By addToCartButton = By.xpath("//aside[@class='product-aside js-product-aside']//a[@class='button button_orange product-aside__item-button']");
    private final By cartIconHeader = By.xpath("//div[@class='b-top-actions']//a[@title='Корзина']");
    private final By productDescription = By.xpath("//div[@class='product-specs__main-group product-specs__group--full js-specs-full is-visible']//td[contains(text(),'Тип')]");
    private final By productPrice = By.xpath("//span[@class='helpers_hide_tablet']");
    private final By numberOfProductsInCartHeaderCounter= By.xpath("//div[@class='b-top-actions']//div[contains(@class,'auth-bar__counter')]");

    public WebElement numberOfProductsInCartHeader(int numberOfProductsInCart) {
        return driver.findElement(By.xpath(String.format("//div[@class='b-top-actions']//div[contains(text(),'%d') and contains(@class,'auth-bar__counter')]", numberOfProductsInCart)));
    }

    public WebElement typeOfProduct(String typeOfProduct) {
        return driver.findElement(By.xpath(String.format("//table[@class='product-specs__table']//span[contains(text(),'%s')]", typeOfProduct)));
    }

    public ProductPage addProductToCart() {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();
        log.info("Add Product To Cart");

        return this;
    }

    public CartPage openCartHeader() {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(cartIconHeader));
        driver.findElement(cartIconHeader).click();
        log.info("Open Cart in Header");

        return new CartPage();
    }

    public ProductPage verifyNumberOfProductsInCartHeader(int numberOfProductsInCart) {

        log.info("Verify Number Of Products In Cart Header");
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(numberOfProductsInCartHeaderCounter));
        assertThat(numberOfProductsInCartHeader(numberOfProductsInCart).isDisplayed()).as("Number Of Products In Cart Header is incorrect!").isTrue();
        log.info("Passed");

        return this;
    }

    public ProductPage verifyThatProductDescription(String typeOfProduct) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(productDescription));

     //   SoftAssertions softly = new SoftAssertions();
        log.info("Verify That Product Description contains: " + typeOfProduct);
        assertThat(typeOfProduct(typeOfProduct).isDisplayed()).as("Product description is incorrect!").isTrue();
        log.info("Passed");

 //      softly.assertAll();
        return this;
    }

    public ProductPage verifyThatProductDescription(List<String> typeOfProduct) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(productPrice));

        for (String element : typeOfProduct) {
            WebElement productMaterial;
            try {
                productMaterial = typeOfProduct(element);

            } catch (WebDriverException e) {
   //             log.error(e.getMessage(), e);
                continue;
            }
            if  (productMaterial.isDisplayed()) {
                log.info("Select Left Menu Item: " + element);
                return this;
            }
        }
        throw new ElementNotVisibleException("No elements found");
    }

}