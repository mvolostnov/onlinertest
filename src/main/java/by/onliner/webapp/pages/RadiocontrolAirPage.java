package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import com.maxim.testframework.webdriver.htmlelements.Button;
import com.maxim.testframework.webdriver.htmlelements.CheckBox;
import com.maxim.testframework.webdriver.htmlelements.Element;
import com.maxim.testframework.webdriver.htmlelements.Input;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class RadiocontrolAirPage extends WebDriverInstance {

    /*
    //    private static final Logger log = Logger.getLogger(RadiocontrolAirPage.class);
//    private WebDriver driver;
//    JavascriptExecutor js = (JavascriptExecutor) driver;
    public RadiocontrolAirPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
     */


//    private  final By headerTitleWE = By.xpath("//h1[@class='schema-header__title']");
    private final By productCheckboxWE = By.xpath("//div[@id='schema-products']//span[@class='i-checkbox__faux']");

    private final Button compareButton = new Button("//div[@class='compare-button-container']//span[contains(text(),'товара')]");
    private final Element sortingMenu = new Element("//div[@class='js-schema-results schema-grid__center-column']//a[@class='schema-order__link']");
    private final Element headerTitle = new Element("//h1[@class='schema-header__title']");
    private  final Input minimalRange = new Input("//div[@id='schema-filter']//input[@placeholder=5]");
    private final Element additionalParameters = new Element("//div[@id='schema-filter']//a[@data-bind='click: $root.toggleAdditionalParameters.bind($root)']");
    private final CheckBox productCheckbox = new CheckBox("//div[@id='schema-products']//span[@class='i-checkbox__faux']");
    private final Element leftMenuItemTemplate = new Element("//div[@id='schema-filter']//span[text()='%s']");
    private final Element sortingMenuItemTemplate = new Element("//div[@class='schema-order__popover']//span[text()='%s']");
    private final Button compareButtonWithProductsTemplate = new Button("//div[@class='compare-button compare-button_visible']//span[contains(text(),'%d')]");
    private final Element numberOfSearchResultsTemplate = new Element("//div[@class='schema-filter-button__state schema-filter-button__state_initial schema-filter-button__state_disabled schema-filter-button__state_control']//span[contains(text(),'Найдено %d')]");

/*
    public WebElement sortingMenuItemWE(String sortingMenuItem) {
        return driver.findElement(By.xpath(String.format("//div[@class='schema-order__popover']//span[text()='%s']"
                , sortingMenuItem)));
    }
 */



    public RadiocontrolAirPage scrollToLeftMenuElement(String leftMenuElement) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format("//div[@id='schema-filter']//span[text()='%s']", leftMenuElement))));
        log.info("Scroll to Left Menu Element: " + leftMenuElement);
        return new RadiocontrolAirPage();
    }

    public RadiocontrolAirPage scrollToPageHeader()  {
        headerTitle.scrollTo();
 //       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
 //               driver.findElement(headerTitleWE));
        log.info("Scroll to Page Header" );
        return this;
    }

    public RadiocontrolAirPage selectLeftMenuItem(String leftMenuItem)  {
        leftMenuItemTemplate.setLocatorVariable(leftMenuItem).waitForToBeClickable().click();
        log.info("Select Left Menu Item: " + leftMenuItem);
        return new RadiocontrolAirPage();
    }

    public RadiocontrolAirPage selectLeftMenuItem(List<String> leftMenuItem)  {

        for (String element : leftMenuItem) {
            leftMenuItemTemplate.setLocatorVariable(element).click();
            log.info("Select Left Menu Item: " + element);
        }
        return new RadiocontrolAirPage();
    }

    public RadiocontrolAirPage selectSortingBy(String sortingMenuItem) {

        log.info("Open Sorting menu");
//        new WebDriverWait(driver, 10).ignoring(ElementClickInterceptedException.class)
//                .until(elementToBeClickable(sortingMenu));
        sortingMenu.waitForToBeClickable().click();
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click();", sortingMenuItemWE(sortingMenuItem));
        sortingMenuItemTemplate.setLocatorVariable(sortingMenuItem).clickMenuItem();

        log.info("Select Sorting by: " + sortingMenuItem);
        return new RadiocontrolAirPage();
    }

    public RadiocontrolAirPage setMinimalRange(String minimalRage)  {
        minimalRange.setValue(minimalRage);
        log.info("Set Minimal Range: " + minimalRage);
        return new RadiocontrolAirPage();
    }

    public RadiocontrolAirPage openAdditionalParameters()  {
        additionalParameters.click();
        log.info("Open Additional Parameters");
        return new RadiocontrolAirPage();
    }

    public RadiocontrolAirPage selectProductByIndex(int index) {
        productCheckbox.waitForToBeDisplayed();
        List<WebElement> selectedProducts = driver.findElements(productCheckboxWE);

        Actions actions = new Actions(driver);
        actions.moveToElement(selectedProducts.get(index)).click().build().perform();

        log.info("Select product № " + index);
        return this;
    }

    public ComparisonPage openComparison()  {
        compareButton.waitForToBeClickable().click();
        log.info("Open Comparison");
        return new ComparisonPage();
    }


    public RadiocontrolAirPage checkThatPageHeaderContains(String headerName) {
        headerTitle.waitForToBeDisplayed();
        String pageHeader = headerTitle.getText();
        log.info("Checking Page Header");
        if (pageHeader.contains(headerName)) {
            log.info("Page header name is correct: " + pageHeader);
        } else {
            log.info("Warning! Page header name is incorrect: " + pageHeader + " instead of " + headerName);
        }
//        assertThat(pageHeader).as("Incorrect Page header", pageHeader).contains(headerName);
        return this;
    }

    public RadiocontrolAirPage verifyThatNumberOfSearchResultsEqualsTo(int numberOfSearchResults) {
        log.info("Verification that Number of Search Results is correct");
        numberOfSearchResultsTemplate.setLocatorVariable(numberOfSearchResults).waitForToBeDisplayed();
        log.info("numberOfSearchResults: " + numberOfSearchResults);
        assertThat(numberOfSearchResultsTemplate.setLocatorVariable(numberOfSearchResults).isVisible()).as("Найдено: " + numberOfSearchResults).isTrue();
        return this;
    }


    public RadiocontrolAirPage verifyThatSortingIsCorrect() throws InterruptedException {
        log.info("Verify that Sorting is correct");

 //       driver.manage().timeouts().pageLoadTimeout(2000,TimeUnit.MILLISECONDS);
 //       driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
//        driver.manage().timeouts().wait(2000);

Thread.sleep(1000);

        List<Double> priceToDouble = new ArrayList<Double>();
        List<WebElement> price = driver.findElements(By.xpath("//div[@class='schema-product__part schema-product__part_2']//span[contains(text(),'р.')]"));
//        price.listOfElements();
        for (WebElement element : price) {
            priceToDouble.add(Double.parseDouble(element.getText().replaceAll("[^,0-9]+", "").replaceAll(",", ".")));
        }
/*
        boolean isSorted = Ordering.natural().isOrdered(priceToDouble);
        System.out.println(isSorted);

 */
/*
        boolean isSorted = Comparators.isInOrder(priceToDouble, Comparator.<Double> naturalOrder());
        System.out.println("Whyyyyy??????? " + isSorted);

 */

/*
        String verifySortingOrder = (priceToDouble.get(1) >= priceToDouble.get(0)) ? "Sorting is correct!" : "Sorting is incorrect!";
        System.out.println(verifySortingOrder);

 */
        assertThat(priceToDouble.get(1) >= priceToDouble.get(0)).as("Sorting is incorrect! "  + priceToDouble.get(1) + " is not >= " + priceToDouble.get(0)).isTrue();
        log.info("Sorting is Correct: " + priceToDouble.get(1) + " > " + priceToDouble.get(0));

        return this;
    }

        public RadiocontrolAirPage verifyThatCompareButtonContainsNumberOfSelectedProducts(int numberOfProducts) {
            log.info("Verify That Compare Button contains " + numberOfProducts + " products");
            boolean isDisplayed = false;
            try {
                compareButtonWithProductsTemplate.setLocatorVariable(numberOfProducts);
                if (compareButtonWithProductsTemplate.setLocatorVariable(numberOfProducts).isVisible()) {
                    isDisplayed = true;
                }
            } catch(Exception e) {

        }

        assertThat(isDisplayed).as("Cant find Compare Button with " + numberOfProducts + " products").isTrue();
            return this;
        }

}
