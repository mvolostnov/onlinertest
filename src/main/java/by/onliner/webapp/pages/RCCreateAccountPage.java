package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.Page;
import by.onliner.test.data.entities.ErrorMessagesData;
import com.maxim.testframework.webdriver.htmlelements.*;
import lombok.extern.log4j.Log4j;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j
public class RCCreateAccountPage extends Page {

    private final Button doneButton = new Button("//button[@class='mat-royal-button btn-create']");
    private final Input firstNameField  = new Input("//input[@placeholder='First name/Given name']");
    private final Input lastNameField = new Input("//input[@placeholder='Last name/Surname']");
    private final Element monthOfBirthField = new Element("id=mat-select-0");
    private final Element monthOfBirthTemplate = new Element("//div[@id='cdk-overlay-0']//span[contains(text(), '%s')]");
    private final Element dayOfBirthField = new Element("id=mat-select-1");
    private final Element dayOfBirthFieldTemplate = new Element("//span[@class='mat-option-text' and contains(text(), '%s')]");
    private final Input yearOfBirthField = new Input("id=mat-input-3");
    private final Element countryOfResidenceField = new Element("id=mat-select-2");
    private final Element countryOfResidenceFieldTemplate = new Element("//span[@class='mat-option-text' and contains(text(), '%s')]");
    private final EmailInput emailField = new EmailInput("id=mat-input-0");
    private final Input newPasswordField = new Input("id=mat-input-4");
    private final Element securityQuestionField = new Element("id=mat-select-3");
    private final Element securityQuestionFieldTemplate = new Element("//span[contains(text(),'%s')]");
    private final Input answerField = new Input("id=mat-input-5");
    private final CheckBox termsCheckbox = new CheckBox("//mat-checkbox//div[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin']");
    private final Element errorMsgName = new Element(ErrorMessagesData.FIRST_NAME_IS_REQUIRED.getXPath());
    private final Element errorMsgSurname = new Element(ErrorMessagesData.LAST_NAME_IS_SPACES.getXPath());


    public RCCreateAccountPage enterFirstName(String firstName) {

        firstNameField.waitForToBeDisplayed().setValue(firstName);
        log.info("Enter First Name: " + firstName);
        return this;
    }

    public RCCreateAccountPage enterLastName(String lastName) {

        lastNameField.waitForToBeDisplayed().setValue(lastName);
        log.info("Enter Last Name: " + lastName);
        return this;
    }

    public RCCreateAccountPage selectMonthOfBirth (String monthOfBirth) {

        monthOfBirthField.waitForToBeDisplayed().click();
        monthOfBirthTemplate.setLocatorVariable(monthOfBirth).click();
        log.info("Select Month of Birth: " + monthOfBirth);
        return this;
    }

    public RCCreateAccountPage selectDayOfBirth(String dayOfBirth) {

        dayOfBirthField.waitForToBeDisplayed().click();
        dayOfBirthFieldTemplate.setLocatorVariable(dayOfBirth).click();
        log.info("Select Day of Birth: " + dayOfBirth);
        return this;
    }

    public RCCreateAccountPage selectYearOfBirth(String yearOfBirth) {

        yearOfBirthField.waitForToBeDisplayed();
        yearOfBirthField.setValue(yearOfBirth);
        log.info("Select Year of Birth: " + yearOfBirth);
        return this;
    }

    public RCCreateAccountPage selectCounrtyOfResidence(String countryOfResidence) {

        countryOfResidenceField.waitForToBeDisplayed().click();
        countryOfResidenceFieldTemplate.setLocatorVariable(countryOfResidence).click();
        log.info("Select Country of Residence: " + countryOfResidence);
        return this;
    }

    public RCCreateAccountPage enterEmail(String email) throws InterruptedException {

        emailField.setEmailValue(email);
        log.info("Enter Email: " + email);
        return this;
    }

    public RCCreateAccountPage createNewPassword(String newPassword) {

        newPasswordField.waitForToBeDisplayed().setValue(newPassword);
        log.info("Create new Password: " + newPassword);
        return this;
    }

    public RCCreateAccountPage selectSecurityQuestion(String securityQuestion) {

        securityQuestionField.waitForToBeDisplayed().click();
        securityQuestionFieldTemplate.setLocatorVariable(securityQuestion).click();
        log.info("Select security Question: " + securityQuestion);
        return this;
    }

    public RCCreateAccountPage enterAnswer(String answer) {

        answerField.waitForToBeDisplayed();
        answerField.setValue(answer);
        log.info("Enter Answer: " + answer);
        return this;
    }

    public RCCreateAccountPage selectTermsCheckbox () throws InterruptedException {

        termsCheckbox.set(true);
        log.info("Click Terms Checkbox");
        return this;
    }

/*
    public MyAccountPage clickDoneButton() throws InterruptedException {
        driver.findElement(clickDoneButton).click();
        log.info("Click Done Button");
        return new MyAccountPage(driver);
    }*/

    public <T> T clickDoneButton(Class<T> returnType) {
        try {
            doneButton.waitForToBeDisplayed().click();
            log.info("Click Done Button");
            return returnType.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    public RCCreateAccountPage verifyThatErrorMessages() {
        String url = getUrl();
        assertThat(url).isEqualTo("https://www.stage2.royalcaribbean.com/account/create").as("Account page URL is incorrect: " + url);
        log.info("Verify Account Page URL: " + url);

        String ErrorMsgName = errorMsgName.getText();
        assertThat(errorMsgName.isVisible()).as("Name Error message is not displayed!").isTrue();
        assertThat(ErrorMsgName).isEqualTo(ErrorMessagesData.FIRST_NAME_IS_REQUIRED.getErrorMessage()).as("Name Error message is incorrect!");
        log.info("Verify First Name Error message: " + ErrorMsgName);

        String ErrorMsgSurname = errorMsgSurname.getText();
        assertThat(errorMsgSurname.isVisible()).as("Surname Error message is not displayed!").isTrue();
        assertThat(ErrorMsgSurname).isEqualTo(ErrorMessagesData.LAST_NAME_IS_SPACES.getErrorMessage()).as("Surname Error message is incorrect!");
        log.info("Verify Last Name Error message: " + ErrorMsgSurname);
/*
        System.out.println("name: " + SecurityQuestionData.WHAT_ELEMENTARY_SCHOOL_DID_YOU_GO_TO.name());
        System.out.println("values: " + Arrays.toString(SecurityQuestionData.values()));
//  ?      System.out.println("valueOf: " + SecurityQuestionData.valueOf(SecurityQuestionData, "What was the first concert you attended?"));
        System.out.println("ordinal: " + SecurityQuestionData.WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT.ordinal());
        System.out.println("equals: " + SecurityQuestionData.WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT.equals(SecurityQuestionData.WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT));

 */
        return this;
    }

}

