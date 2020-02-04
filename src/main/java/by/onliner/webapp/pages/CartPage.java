package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import com.maxim.testframework.webdriver.htmlelements.Button;
import com.maxim.testframework.webdriver.htmlelements.Element;
import lombok.extern.log4j.Log4j;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j
public class CartPage extends WebDriverInstance {

//    private final By numberOfProducts = By.xpath("//div[@class='cart-form__offers-item cart-form__offers-item_secondary']//input[@class='input-style input-style_primary input-style_small input-style_text_center cart-form__input cart-form__input_max-width_xxxxsssss cart-form__input_nonadaptive']");
//    private final By plusButton = By.xpath("//div[@class='cart-form__offers-item cart-form__offers-item_secondary']//a[@class='button-style button-style_auxiliary button-style_small cart-form__button cart-form__button_increment helpers_hide_tablet']");
//    private final By checkoutButton = By.xpath("//div[@class='cart-form__offers-part cart-form__offers-part_total']//a[@class='button-style button-style_primary button-style_small cart-form__button']");
    private final Element numberOfProducts = new Element("//div[@class='cart-form__offers-item cart-form__offers-item_secondary']//input[@class='input-style input-style_primary input-style_small input-style_text_center cart-form__input cart-form__input_max-width_xxxxsssss cart-form__input_nonadaptive']");
    private final Button plusButton = new Button("//div[@class='cart-form__offers-item cart-form__offers-item_secondary']//a[@class='button-style button-style_auxiliary button-style_small cart-form__button cart-form__button_increment helpers_hide_tablet']");
    private final Element singlePrice = new Element("//div[@class='cart-form__offers-part cart-form__offers-part_price helpers_hide_tablet']//div[@class='cart-form__description cart-form__description_primary cart-form__description_base-alter cart-form__description_font-weight_semibold cart-form__description_ellipsis cart-form__description_condensed-another']/span");
    private final Element totalPrice = new Element("//div[@class='cart-form__offers-part cart-form__offers-part_sum']//div[@class='cart-form__description cart-form__description_primary cart-form__description_base-alter cart-form__description_font-weight_semibold cart-form__description_ellipsis cart-form__description_condensed-other']/span");
    private final Button checkoutButton = new Button("//div[@class='cart-form__offers-part cart-form__offers-part_total']//a[@class='button-style button-style_primary button-style_small cart-form__button']");
    private final Element loginPopUp = new Element("//div[@id='auth-container']//button[contains(text(), 'Войти')]");


    public CartPage clickPlusButton() {
//        new WebDriverWait(driver, 10).until(visibilityOfElementLocated(numberOfProducts));
//        new WebDriverWait(driver, 10).until(elementToBeClickable(numberOfProducts));
        numberOfProducts.waitForToBeDisplayed().click();
      //  numberOfProducts.click();
        plusButton.waitForToBeClickable().click();
//        driver.findElement(plusButton).click();
        log.info("Click Plus Button");
        return this;
    }

    public CartPage verifyThatTotalPrice(){
        log.info("Verify That Total Price is correct");
        Double singlePriceToDouble;
        Double totalPriceToDouble;

        singlePriceToDouble = Double.parseDouble(singlePrice.getText().replaceAll("[^,0-9]+", "").replaceAll(",", "."));
        log.info("singlePriceToDouble = " + singlePriceToDouble);

        totalPriceToDouble = Double.parseDouble(totalPrice.getText().replaceAll("[^,0-9]+", "").replaceAll(",", "."));
        log.info("singlePriceToDouble = " + totalPriceToDouble);

        assertThat(totalPriceToDouble).as("Total price is incorrect!").isEqualTo(singlePriceToDouble);
        log.info("Passed");
        return this;
    }

    public CartPage doCheckout() {
        checkoutButton.click();
        log.info("Do Checkout");
        return this;
    }

    public CartPage verifyThatLoginPopup() {
        log.info("Verify That Login Popup appears");
        loginPopUp.waitForToBeDisplayed();

        assertThat(loginPopUp.isVisible()).as("No Login Pop Up!").isTrue();
        log.info("Passed");
        return this;
    }

}

