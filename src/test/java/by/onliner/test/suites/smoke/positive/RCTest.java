package by.onliner.test.suites.smoke.positive;

import by.onliner.test.BaseTest;
import by.onliner.test.data.entities.RCCreateAccountData;
import by.onliner.test.data.entities.SecurityQuestionData;
import com.maxim.testframework.utils.RandomString;
import by.onliner.webapp.pages.MyAccountPage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Getter
@Setter
@Log4j
public class RCTest extends BaseTest {


    @DataProvider
    public Object[][] accountData() {
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


    @Test (dataProvider = "accountData")
    public void testRCsignUp(RCCreateAccountData accountData) throws InterruptedException {

        extentTest.info("Start scenario with the following data: " + accountData.toString());
        app.openRCCreateAccountPage()
                .enterFirstName(accountData.getFirstName())
                .enterLastName(accountData.getLastName())
                .selectMonthOfBirth(accountData.getMonthOfBirth())
                .selectDayOfBirth(accountData.getDayOfBirth())
                .selectYearOfBirth(accountData.getYearOfBirth())
                .selectCounrtyOfResidence(accountData.getCountryOfResidence())
                .enterEmail(accountData.getEmail())
                .createNewPassword(accountData.getNewPassword())
                .selectSecurityQuestion(accountData.getSecurityQuestion())
                .enterAnswer(accountData.getAnswer())
                .selectTermsCheckbox()
                .clickDoneButton(MyAccountPage.class)
                .verifyThatAccountPage(accountData.getFirstName())
                ;

        testContext.setAccountData(accountData);
    }
/*
    @Test ()
    public void testRCsignUp2() {

        RCCreateAccountData accountData = testContext.getAccountData();
        accountData.setNewPassword("");

        //app.profilaPage().updateCredentials(accountData);
        app.openRCCreateAccountPage();
        testContext.setAccountData(accountData);

    }

 */

}



