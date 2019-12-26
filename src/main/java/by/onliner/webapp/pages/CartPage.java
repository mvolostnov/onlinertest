package by.onliner.webapp.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    private final By numberOfProducts = By.xpath("//div[@class='cart-product']//input[@class='input-style input-style_primary input-style_small cart-product-add-box__input']");

    private final By plusButton = By.xpath("//div[@class='cart-product']//span[@class='button-style button-style_auxiliary button-style_small cart-product-add-box__button cart-product-add-box__button_add']");

    private final By checkoutButton = By.xpath("//div[@class='cart-navigation']//a[contains(text(),'Оформить весь заказ')]");

    private final By loginPopUp = By.xpath("//div[@class='popup-style__content']//div[contains(text(),'Войдите на')]");

    private final By singlePrice = By.xpath("//div[@class='cart-product__part cart-product__part_4']//span[contains(text(),'р.')]");

    private final By totalPrice = By.xpath("//div[@class='cart-navigation']//span[@data-bind='html: $root.format.positionPrice($root.positionsList.total().composite_price)']");


    public CartPage clickPlusButton() {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(numberOfProducts));
        new WebDriverWait(driver, 10)
                .until(elementToBeClickable(numberOfProducts));

        driver.findElement(numberOfProducts).click();
        new WebDriverWait(driver, 10)
                .until(elementToBeClickable(plusButton));
        driver.findElement(plusButton).click();
        log.info("Click Plus Button");
        return this;
    }

    public CartPage verifyThatTotalPrice(){
        log.info("Verify That Total Price is correct");

        Double singlePriceToDouble;
        Double totalPriceToDouble;
  //    WebElement singlePrice = driver.findElement(By.xpath("//div[@class='cart-product__part cart-product__part_4']//span[contains(text(),'р.')]"));;
  //      WebElement totalPrice = driver.findElement(By.xpath("//div[@class='cart-navigation']//span[@data-bind='html: $root.format.positionPrice($root.positionsList.total().composite_price)']"));;

        singlePriceToDouble = Double.parseDouble(driver.findElement(singlePrice).getText().replaceAll("[^,0-9]+", "").replaceAll(",", "."));
        log.info("singlePriceToDouble = " + singlePriceToDouble);

        totalPriceToDouble = Double.parseDouble(driver.findElement(totalPrice).getText().replaceAll("[^,0-9]+", "").replaceAll(",", "."));
        log.info("singlePriceToDouble = " + totalPriceToDouble);

        assertThat(totalPriceToDouble).as("Total price is incorrect!").isEqualTo(singlePriceToDouble * 2);
        log.info("Passed");

        return this;
    }

    public CartPage doCheckout() {
        driver.findElement(checkoutButton).click();
        log.info("Do Checkout");
        return this;
    }

    public CartPage verifyThatLoginPopup() {
        log.info("Verify That Login Popup appears");
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(loginPopUp));

        assertThat(driver.findElement(loginPopUp).isDisplayed()).as("No Login Pop Up!").isTrue();
        log.info("Passed");

        return this;
    }

}

