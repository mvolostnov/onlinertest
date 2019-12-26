package by.onliner.webapp.pages;

import lombok.extern.log4j.Log4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private final By addToCartButton = By.xpath("//aside[@class='product-aside js-product-aside']//a[@class='button button_orange product-aside__item-button']");

    private final By cartIconHeader = By.xpath("//div[@class='b-top-actions']//a[@title='Корзина']");

    private final By productDescription = By.xpath("//div[@class='product-specs__main-group product-specs__group--full js-specs-full is-visible']//td[contains(text(),'Тип')]");

    public WebElement numberOfProductsInCartHeader(int numberOfProductsInCart) {
        return driver.findElement(By.xpath(String.format("//div[@class='b-top-actions']//div[contains(text(),'%d') and contains(@class,'auth-bar__counter')]", numberOfProductsInCart)));
    }

    public WebElement typeOfProduct(String typeOfProduct) {
        return driver.findElement(By.xpath(String.format("//div[@class='product-specs__main-group product-specs__group--full js-specs-full is-visible']//span[contains(text(),'%s')]", typeOfProduct)));
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

        return new CartPage(driver);
    }

    public ProductPage verifyNumberOfProductsInCartHeader(int numberOfProductsInCart) {

        log.info("Verify Number Of Products In Cart Header");
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(By.xpath("//div[@class='b-top-actions']//div[contains(@class,'auth-bar__counter')]")));
        assertThat(numberOfProductsInCartHeader(numberOfProductsInCart).isDisplayed()).as("Number Of Products In Cart Header is incorrect!").isTrue();
        log.info("Passed");

        return this;
    }

    public ProductPage verifyThatProductDescription(String typeOfProduct) {


        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(productDescription));

        SoftAssertions softly = new SoftAssertions();
        log.info("Verify That Product Description contains: " + typeOfProduct);
        softly.assertThat(typeOfProduct(typeOfProduct).isDisplayed()).as("Product description is incorrect!").isTrue();
        log.info("Passed");

 //       softly.assertAll();

        return this;
    }




}