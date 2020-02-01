package by.onliner.webapp.pages;

import com.maxim.testframework.webdriver.WebDriverInstance;
import com.maxim.testframework.webdriver.htmlelements.Button;
import com.maxim.testframework.webdriver.htmlelements.Element;
import lombok.extern.log4j.Log4j;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j
public class MyAccountPage extends WebDriverInstance {

    private final Button myAccountButton = new Button("//div[@class='menu-nav__profile']//span[@class='menu-nav__profile-label']");
    private final Element userNameHeader = new Element("//h2[@class='hero-header__user-name']");

    public MyAccountPage() {
        myAccountButton.waitForToBeDisplayed();
    }

    public MyAccountPage verifyThatAccountPage(String name) {
        String url = driver.getCurrentUrl();
        assertThat(url).isEqualTo("https://www.stage2.royalcaribbean.com/account/").as("Account page URL is incorrect: " + url);
        log.info("Verify Account Page URL: " + url);

        String userName = userNameHeader.getText();
        assertThat(userNameHeader.isVisible()).as("User name is not displayed!").isTrue();
        assertThat(userName).isEqualTo(name).as("User name is incorrect!");
        log.info("Verify User name: " + userName);

        return this;
    }

}
