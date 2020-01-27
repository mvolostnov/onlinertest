package by.onliner.webapp.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RadiocontrolAirPage {
    private static final Logger log = Logger.getLogger(RadiocontrolAirPage.class);

    private WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public RadiocontrolAirPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final By compareButton = By.xpath("//div[@class='compare-button-container']//span[contains(text(),'товара')]");


    public WebElement leftMenuItem(String leftMenuItem) {
        return driver.findElement(By.xpath(String.format("//div[@id='schema-filter']//span[text()='%s']", leftMenuItem)));
    }

    private  final By sortingMenu = By.xpath("//div[@class='js-schema-results schema-grid__center-column']//a[@class='schema-order__link']");

    public WebElement sortingMenuItem(String sortingMenuItem) {
        return driver.findElement(By.xpath(String.format("//div[@class='schema-order__popover']//span[text()='%s']"
                , sortingMenuItem)));
    }


    public WebElement compareButtonWithProducts(int numberOfProducts) {
        return driver.findElement(By.xpath(String.format("//div[@class='compare-button compare-button_visible']//span[contains(text(),'%d')]"
                ,numberOfProducts)));
    }

    public WebElement numberOfSearchResults(int numberOfSearchResults) {
        return driver.findElement(By.xpath(String.format("//div[@class='schema-filter-button__state schema-filter-button__state_initial schema-filter-button__state_disabled schema-filter-button__state_control']//span[contains(text(),'Найдено %d')]"
                ,numberOfSearchResults )));

    }

    private  final By headerTitle = By.xpath("//h1[@class='schema-header__title']");

    private  final By minimalRange = By.xpath("//div[@id='schema-filter']//input[@placeholder=5]");

    private final By additionalParameters = By.xpath("//div[@id='schema-filter']//a[@data-bind='click: $root.toggleAdditionalParameters.bind($root)']");

    private final By productCheckbox = By.xpath("//div[@id='schema-products']//span[@class='i-checkbox__faux']");


    public RadiocontrolAirPage scrollToLeftMenuElement(String leftMenuElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format("//div[@id='schema-filter']//span[text()='%s']", leftMenuElement))));
        log.info("Scroll to Left Menu Element: " + leftMenuElement);
        return new RadiocontrolAirPage(driver);
    }

    public RadiocontrolAirPage scrollToPageHeader()  {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(headerTitle));
        log.info("Scroll to Page Header" );
        return this;
    }

    public RadiocontrolAirPage selectLeftMenuItem(String leftMenuItem)  {
        new WebDriverWait(driver, 10)
                .until(elementToBeClickable(leftMenuItem(leftMenuItem)));
        leftMenuItem(leftMenuItem).click();
        log.info("Select Left Menu Item: " + leftMenuItem);
        return new RadiocontrolAirPage(driver);
    }

    public RadiocontrolAirPage selectLeftMenuItem(List<String> leftMenuItem)  {

        for (String element : leftMenuItem) {
            leftMenuItem(element).click();
            log.info("Select Left Menu Item: " + element);
        }

        return new RadiocontrolAirPage(driver);
    }

    public RadiocontrolAirPage selectSortingBy(String sortingMenuItem) {

        new WebDriverWait(driver, 10).until(elementToBeClickable(sortingMenu));
        log.info("Open Sorting menu");
        new WebDriverWait(driver, 10).ignoring(ElementClickInterceptedException.class)
                .until(elementToBeClickable(sortingMenu));
        driver.findElement(sortingMenu).click();

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", sortingMenuItem(sortingMenuItem));

        log.info("Select Sorting by: " + sortingMenuItem);

        return new RadiocontrolAirPage(driver);
    }

    public RadiocontrolAirPage setMinimalRange(String minimalRage)  {
        driver.findElement(minimalRange).sendKeys(minimalRage);
        log.info("Set Minimal Range: " + minimalRage);
        return new RadiocontrolAirPage(driver);
    }

    public RadiocontrolAirPage openAdditionalParameters()  {
        driver.findElement(additionalParameters).click();
        log.info("Open Additional Parameters");
        return new RadiocontrolAirPage(driver);
    }

    public RadiocontrolAirPage selectProductByIndex(int index) {
        new WebDriverWait(driver, 10).ignoring(ElementClickInterceptedException.class)
                .until(visibilityOfElementLocated(productCheckbox));
        List<WebElement> selectedProducts = driver.findElements(productCheckbox);

        Actions actions = new Actions(driver);
        actions.moveToElement(selectedProducts.get(index)).click().build().perform();

        log.info("Select product № " + index);
        return this;
    }

    public ComparisonPage openComparison()  {
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(compareButton));
        driver.findElement(compareButton).click();
        log.info("Open Comparison");
        return new ComparisonPage();
    }


    public RadiocontrolAirPage checkThatPageHeaderContains(String headerName) {
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(headerTitle));

        String pageHeader = driver.findElement(headerTitle).getText();
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

       new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(By.xpath(String.format("//div[@class='schema-filter-button__state schema-filter-button__state_initial schema-filter-button__state_disabled schema-filter-button__state_control']//span[contains(text(),'Найдено %d')]"
                        , numberOfSearchResults ))));


        log.info("numberOfSearchResults: " + numberOfSearchResults);
        assertThat(numberOfSearchResults(numberOfSearchResults).isDisplayed()).as("Найдено: " + numberOfSearchResults).isTrue();
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

        public RadiocontrolAirPage verifyThatCompareButtonContainsNumberOfSelectedProducts(int numberOfProducts) throws InterruptedException {
            log.info("Verify That Compare Button contains " + numberOfProducts + " products");

            boolean isDisplayed = false;
            try {
                new WebDriverWait(driver, 10)
                        .until(elementToBeClickable(By.xpath(String.format("//div[@class='compare-button compare-button_visible']//span[contains(text(),'%d')]"
                                ,numberOfProducts))));
                if (compareButtonWithProducts(numberOfProducts).isDisplayed()) {
                    isDisplayed = true;
                }
            } catch(Exception e) {

        }

        assertThat(isDisplayed).as("Cant find Compare Button with " + numberOfProducts + " products").isTrue();
            log.info("Passed");
            return this;
        }

}
