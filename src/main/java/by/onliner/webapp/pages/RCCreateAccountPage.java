package by.onliner.webapp.pages;

import by.onliner.test.WebDriverInstance;
import by.onliner.test.data.entities.ErrorMessagesData;
import by.onliner.test.data.entities.SecurityQuestionData;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class RCCreateAccountPage extends WebDriverInstance {


    private final By firstNameField = By.xpath("//input[@placeholder='First name/Given name']");
    private final By lastNameField = By.xpath("//input[@placeholder='Last name/Surname']");
    private final By monthOfBirthField = By.id("mat-select-0");
    private final By dayOfBirthField = By.id("mat-select-1");
    private final By yearOfBirthField = By.id("mat-input-3");
    private final By countryOfResidenceField = By.id("mat-select-2");
    private final By emailField = By.id("mat-input-0");
    private final By newPasswordField = By.id("mat-input-4");
    private final By securityQuestionField = By.id("mat-select-3");
    private final By answerField = By.id("mat-input-5");
    private final By termsCheckbox = By.xpath("//mat-checkbox//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']");
    private final By clickDoneButton = By.xpath("//button[@class='mat-royal-button btn-create']");


    public WebElement monthOfBirth (String monthOfBirth) {
        return driver.findElement(By.xpath(String.format("//div[@id='cdk-overlay-0']//span[contains(text(), '%s')]", monthOfBirth)));
    }

    public WebElement dayOfBirth(String dayOfBirth) {
        return driver.findElement(By.xpath(String.format("//span[@class='mat-option-text' and contains(text(), '%s')]", dayOfBirth)));
    }

    public WebElement countryOfResidence(String countryOfResidence) {
        return driver.findElement(By.xpath(String.format("//span[@class='mat-option-text' and contains(text(), '%s')]", countryOfResidence)));
    }

    public WebElement securityQuestion(String securityQuestion) {
        return driver.findElement(By.xpath(String.format("//span[contains(text(),'%s')]", securityQuestion)));
    }


    public RCCreateAccountPage enterFirstName(String firstName) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(firstNameField));
        driver.findElement(firstNameField).sendKeys(firstName);
        log.info("Enter First Name: " + firstName);

        return this;
    }

    public RCCreateAccountPage enterLastName(String lastName) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(lastNameField));
        driver.findElement(lastNameField).sendKeys(lastName);
        log.info("Enter Last Name: " + lastName);
        return this;
    }

    public RCCreateAccountPage selectMonthOfBirth (String monthOfBirth) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(monthOfBirthField));

        driver.findElement(monthOfBirthField).click();
        (monthOfBirth(monthOfBirth)).click();
        log.info("Select Month of Birth: " + monthOfBirth);
        return this;
    }

    public RCCreateAccountPage selectDayOfBirth(String dayOfBirth) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(dayOfBirthField));

        driver.findElement(dayOfBirthField).click();
        (dayOfBirth(dayOfBirth)).click();
        log.info("Select Day of Birth: " + dayOfBirth);
        return this;
    }

    public RCCreateAccountPage selectYearOfBirth(String yearOfBirth) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(yearOfBirthField));

        driver.findElement(yearOfBirthField).sendKeys(yearOfBirth);
        log.info("Select Year of Birth: " + yearOfBirth);
        return this;
    }

    public RCCreateAccountPage selectCounrtyOfResidence(String countryOfResidence) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(countryOfResidenceField));

        driver.findElement(countryOfResidenceField).click();
        (countryOfResidence(countryOfResidence)).click();
        log.info("Select Country of Residence: " + countryOfResidence);
        return this;
    }

    public RCCreateAccountPage enterEmail(String email) throws InterruptedException {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(yearOfBirthField));

        WebElement emailInput = driver.findElement(emailField);
        Thread.sleep(200);
        for(int i = 0; i < email.length(); i++){
            emailInput.sendKeys(String.valueOf(email.charAt(i)));
            Thread.sleep(20);
            //driver.manage().timeouts().wait(20);
        }

        log.info("Enter Email: " + email);
        return this;
    }

    public RCCreateAccountPage createNewPassword(String newPassword) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(newPasswordField));

        driver.findElement(newPasswordField).sendKeys(newPassword);
        log.info("Create new Password: " + newPassword);
        return this;
    }

    public RCCreateAccountPage selectSecurityQuestion(String securityQuestion) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(securityQuestionField));

        driver.findElement(securityQuestionField).click();
        (securityQuestion(securityQuestion)).click();
        log.info("Select security Question: " + securityQuestion);
        return this;
    }

    public RCCreateAccountPage enterAnswer(String answer) {

        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(answerField));

        driver.findElement(answerField).sendKeys(answer);
        log.info("Enter Answer: " + answer);
        return this;
    }

    public RCCreateAccountPage selectTermsCheckbox () {

        driver.findElement(termsCheckbox).click();
        log.info("Click Terms Checkbox");
        return this;
    }

/*
    public MyAccountPage clickDoneButton() throws InterruptedException {
        driver.findElement(clickDoneButton).click();
        log.info("Click Done Button");
        return new MyAccountPage(driver);
    }*/



    public <T> T clickDoneButton( Class<T> returnType) {
        try {
            driver.findElement(clickDoneButton).click();
            log.info("Click Done Button");

            return returnType.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }



    public RCCreateAccountPage verifyThatErrorMessages() {
        String url = driver.getCurrentUrl();
        assertThat(url).isEqualTo("https://www.stage2.royalcaribbean.com/account/create").as("Account page URL is incorrect: " + url);
        log.info("Verify Account Page URL: " + url);

        String ErrorMsgName = driver.findElement(By.xpath(ErrorMessagesData.FIRST_NAME_IS_REQUIRED.getXPath())).getText();
        assertThat(driver.findElement(By.xpath(ErrorMessagesData.FIRST_NAME_IS_REQUIRED.getXPath())).isDisplayed()).as("Name Error message is not displayed!").isTrue();
        assertThat(ErrorMsgName).isEqualTo(ErrorMessagesData.FIRST_NAME_IS_REQUIRED.getErrorMessage()).as("Name Error message is incorrect!");
        log.info("Verify First Name Error message: " + ErrorMsgName);

        String ErrorMsgSurname = driver.findElement(By.xpath(ErrorMessagesData.LAST_NAME_IS_SPACES.getXPath())).getText();
        assertThat(driver.findElement(By.xpath(ErrorMessagesData.LAST_NAME_IS_SPACES.getXPath())).isDisplayed()).as("Surname Error message is not displayed!").isTrue();
        assertThat(ErrorMsgSurname).isEqualTo(ErrorMessagesData.LAST_NAME_IS_SPACES.getErrorMessage()).as("Surname Error message is incorrect!");
        log.info("Verify Last Name Error message: " + ErrorMsgSurname);

        System.out.println("name: " + SecurityQuestionData.WHAT_ELEMENTARY_SCHOOL_DID_YOU_GO_TO.name());
        System.out.println("values: " + Arrays.toString(SecurityQuestionData.values()));
//  ?      System.out.println("valueOf: " + SecurityQuestionData.valueOf(SecurityQuestionData, "What was the first concert you attended?"));
        System.out.println("ordinal: " + SecurityQuestionData.WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT.ordinal());
        System.out.println("equals: " + SecurityQuestionData.WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT.equals(SecurityQuestionData.WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT));

        return this;
    }

/*
    public RCCreateAccountPage toUppCase () {

        System.out.println( (" What was the first concert you attended? " +
                " What is the name of the street where you first lived? " +
                " What elementary school did you go to? " +
                " Where did you take your first vacation? " +
                " What was the name of your first pet? " +
                " What was the first album you bought? " +
                " What was your first car's make or model? " +
                " Who was your first kiss? " +
                " What is your travel agent’s last name? ").toUpperCase());
        return this;
    }

 */


}

