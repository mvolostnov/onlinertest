package by.onliner.test.suites.smoke.positive;

import by.onliner.test.BaseTest;
import by.onliner.test.data.entities.RCCreateAccountData;
import by.onliner.test.data.entities.SecurityQuestionData;
import by.onliner.utils.RandomString;
import by.onliner.webapp.pages.MyAccountPage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j
public class RCTest extends BaseTest {


    @DataProvider
    public Object[][] contactData() {
        return new Object[][]{
                new Object[]{RCCreateAccountData.builder()
                        .firstName("Firstname" + RandomString.randomString(6).toLowerCase())
                        .lastName("Lastname" + RandomString.randomString(6).toLowerCase())
                        .monthOfBirth("November")
                        .dayOfBirth("27")
                        .yearOfBirth("1990")
                        .countryOfResidence("Chile")
                        .email(RandomString.randomString(6).toLowerCase()+"@mail.com")
                        .newPassword("1Qwertyui")
                        .securityQuestion(SecurityQuestionData.WHAT_ELEMENTARY_SCHOOL_DID_YOU_GO_TO.getQuestionText())
                        .answer("You")
                        .build()},
        };
    }

    @Test (dataProvider = "contactData")
    public void testRCsignUp(RCCreateAccountData contactData) throws InterruptedException {

        extentTest.info("Start scenario with the following data: " + contactData.toString());
        app.openRCCreateAccountPage()
                .enterFirstName(contactData.getFirstName())
                .enterLastName(contactData.getLastName())
                .selectMonthOfBirth(contactData.getMonthOfBirth())
                .selectDayOfBirth(contactData.getDayOfBirth())
                .selectYearOfBirth(contactData.getYearOfBirth())
                .selectCounrtyOfResidence(contactData.getCountryOfResidence())
                .enterEmail(contactData.getEmail())
                .createNewPassword(contactData.getNewPassword())
                .selectSecurityQuestion(contactData.getSecurityQuestion())
                .enterAnswer(contactData.getAnswer())
                .selectTermsCheckbox()
                .clickDoneButton(MyAccountPage.class)
                .verifyThatAccountPage(contactData.getFirstName())
                ;
    }
}



