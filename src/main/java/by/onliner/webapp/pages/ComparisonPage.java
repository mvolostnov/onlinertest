package by.onliner.webapp.pages;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Log4j
public class ComparisonPage {
    private WebDriver driver;

    public ComparisonPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ProductPage selectComparedProductWithIndex(int index) {
        List<WebElement> selectComparedProductN = driver.findElements(By.xpath("//tr[@class='product-table__row product-table__row_header product-table__row_top']//a[@class='product-summary__figure']"));
        selectComparedProductN.get(index).click();

        log.info("Select Compared Product Index " + index);
        return new ProductPage(driver);

    }

}
