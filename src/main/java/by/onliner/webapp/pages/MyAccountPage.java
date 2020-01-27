package by.onliner.webapp.pages;

import by.onliner.test.WebDriverInstance;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Log4j
public class MyAccountPage extends WebDriverInstance {


    public MyAccountPage() throws InterruptedException {
        new WebDriverWait(driver, 10)
                .until(visibilityOfElementLocated(myAccountButton));
    }

    private final By userNameHeader = By.xpath("//h2[@class='hero-header__user-name']");
    private final By myAccountButton = By.xpath("//div[@class='menu-nav__profile']//span[@class='menu-nav__profile-label']");


    public MyAccountPage verifyThatAccountPage(String name) {
        String url = driver.getCurrentUrl();
        assertThat(url).isEqualTo("https://www.stage2.royalcaribbean.com/account/").as("Account page URL is incorrect: " + url);
        log.info("Verify Account Page URL: " + url);

        String userName = driver.findElement(userNameHeader).getText();
        assertThat(driver.findElement(userNameHeader).isDisplayed()).as("User name is not displayed!").isTrue();
        assertThat(userName).isEqualTo(name).as("User name is incorrect!");
        log.info("Verify User name: " + userName);

        return this;
    }


}
