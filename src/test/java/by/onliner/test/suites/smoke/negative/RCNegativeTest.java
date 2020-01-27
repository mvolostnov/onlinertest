package by.onliner.test.suites.smoke.negative;

import by.onliner.test.BaseTest;
import by.onliner.test.data.entities.RCCreateAccountData;
import by.onliner.test.data.entities.SecurityQuestionData;
import by.onliner.utils.RandomString;
import by.onliner.webapp.pages.MyAccountPage;
import by.onliner.webapp.pages.RCCreateAccountPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RCNegativeTest extends BaseTest {
    @DataProvider
    public Object[][] contactData() {
        return new Object[][]{
                new Object[]{RCCreateAccountData.builder()
                        .firstName("")
                        .lastName("  " )
                        .monthOfBirth("November")
                        .dayOfBirth("27")
                        .yearOfBirth("1990")
                        .countryOfResidence("Chile")
                        .email(RandomString.randomString(6).toLowerCase())
                        .newPassword("1Qwertyui")
                        .securityQuestion(SecurityQuestionData.WHAT_WAS_THE_FIRST_CONCERT_YOU_ATTENDED.getQuestionText())
                        .answer("You")
                        .build()},
        };
    }

    @Test(dataProvider = "contactData")
    public void testRCsignUpNegative(RCCreateAccountData contactData) throws InterruptedException {

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
                .clickDoneButton(RCCreateAccountPage.class)
                .verifyThatErrorMessages()
        ;
    }

}
