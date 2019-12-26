package by.onliner.test.suites.functional;

import by.onliner.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Log4j
public  class RadiocontrolAirTest extends BaseTest {

    @Test(description = "Header Радиоуправляемые авиамодели test")
    public void radiocontrolAirTest() throws InterruptedException {

        app.openHomePage()
                .openTopMenuSection("Каталог")
                .selectCatalogCategory("Красота и спорт")
                .selectCatalogSubCategory("Хобби")
                .selectProductType("Радиоуправляемые авиамодели")
                .checkThatPageHeaderContains("Радиоуправляемые авиамодели")
                .scrollToLeftMenuElement("Тип")
                .selectLeftMenuItem("квадрокоптер")
                .selectLeftMenuItem("пластик")
                .selectLeftMenuItem("металл")
                .setMinimalRange("100")
                .scrollToLeftMenuElement("Дальность действия, м")
                .openAdditionalParameters()
                .selectLeftMenuItem("бесколлекторный")
                .verifyThatNumberOfSearchResultsEqualsTo(102)
                .scrollToPageHeader()
                .selectSortingBy("Дешевые")
                .verifyThatSortingIsCorrect()
                .selectProductByIndex(0)
                .selectProductByIndex(2)
                .selectProductByIndex(4)
                .selectProductByIndex(5)
                .verifyThatCompareButtonContainsNumberOfSelectedProducts(4)
                .openComparison()
                .selectComparedProductWithIndex(2)
                .verifyThatProductDescription("квадрокоптер")
                .verifyThatProductDescription("пластик")
//                .verifyThatProductDescription("металл")
                .verifyThatProductDescription("бесколлекторный")
                .addProductToCart()
                .verifyNumberOfProductsInCartHeader(1)
                .openCartHeader()
                .clickPlusButton()
                .verifyThatTotalPrice()
                .doCheckout()
                .verifyThatLoginPopup()

        ;

    }

    @Test (description = "Dummy test 2")

    public void dummyTest2() throws InterruptedException, IOException {

        log.info(" !!! Startring test: Test #2.2 !!!");
 //       Assert.assertEquals(2,4);

        System.out.println("Test #2.2 body");

    }


}
