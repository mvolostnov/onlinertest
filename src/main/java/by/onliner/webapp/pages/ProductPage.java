package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import com.maxim.testframework.webdriver.htmlelements.Button;
import com.maxim.testframework.webdriver.htmlelements.Element;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j
public class ProductPage extends WebDriverInstance {

    private final Button addToCartButton = new Button("//aside[@class='product-aside js-product-aside']//a[@class='button button_orange product-aside__item-button']");
    private final Element cartIconHeader = new Element("//div[@class='b-top-actions']//a[@title='Корзина']");
    private final Element productDescription = new Element("//div[@class='product-specs__main-group product-specs__group--full js-specs-full is-visible']//td[contains(text(),'Тип')]");
//    private final Element productPrice = new Element("//span[@class='helpers_hide_tablet']");
    private final Element numberOfProductsInCartHeaderCounter = new Element("//div[@class='b-top-actions']//div[contains(@class,'auth-bar__counter')]");
    public Element numberOfProductsInCartHeaderTemplate = new Element("//div[@class='b-top-actions']//div[contains(text(),'%d') and contains(@class,'auth-bar__counter')]");
    public Element typeOfProductTemplate = new Element("//table[@class='product-specs__table']//span[contains(text(),'%s')]");
    private final Element productPrice = new Element("//span[@class='helpers_hide_tablet']");


    public ProductPage addProductToCart() {
        addToCartButton.waitForToBeClickable().click();
        log.info("Add Product To Cart");
        return this;
    }

    public CartPage openCartHeader() {
        cartIconHeader.waitForToBeDisplayed().click();
        log.info("Open Cart in Header");
        return new CartPage();
    }

    public ProductPage verifyNumberOfProductsInCartHeader(int numberOfProductsInCart) {

        log.info("Verify Number Of Products In Cart Header");
        numberOfProductsInCartHeaderCounter.scrollTo().waitForToBeDisplayed();
        assertThat(numberOfProductsInCartHeaderTemplate.setLocatorVariable(numberOfProductsInCart).isVisible()).as("Number Of Products In Cart Header is incorrect!").isTrue();
        log.info("Passed");
        return this;
    }

    public ProductPage verifyThatProductDescription(String typeOfProduct) {
        productDescription.waitForToBeDisplayed();
     //   SoftAssertions softly = new SoftAssertions();
        log.info("Verify That Product Description contains: " + typeOfProduct);
        assertThat(typeOfProductTemplate.setLocatorVariable(typeOfProduct).isVisible()).as("Product description is incorrect!").isTrue();
        log.info("Passed");
 //      softly.assertAll();
        return this;
    }

    public ProductPage verifyThatProductDescription(List<String> typeOfProduct) {

        productPrice.scrollTo();
        for (String element : typeOfProduct) {
            Element productMaterial;
            try {
                productMaterial = typeOfProductTemplate.setLocatorVariable(element);

            } catch (WebDriverException e) {
//                 log.error(e.getMessage(), e);
                continue;
            }
            if  (productMaterial.isVisible()) {
                log.info("Product material: " + element);
                return this;
            }
        }
        throw new ElementNotVisibleException("No elements found");
    }

}