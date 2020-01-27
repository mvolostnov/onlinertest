package by.onliner.test.suites.functional;

import by.onliner.test.BaseTest;
import by.onliner.test.data.entities.ExpectedData;
import by.onliner.test.data.entities.MainMenuData;
import by.onliner.test.data.entities.ProductMenuData;
import by.onliner.test.data.entities.QuadrocopterData;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Arrays;

@Log4j
public  class RadiocontrolAirTest extends BaseTest {

    @DataProvider
    public Object[][] testData() {
        return new Object[][] {
                new Object[] {QuadrocopterData.builder()
                        .materials(Arrays.asList("бальза", "металл", "пластик"))
                        .minimalRange("100")
                        .engineType("бесколлекторный")
                        .build(),
                        ExpectedData.builder()
                        .numberOfSearchResults(102)
                        .numberOfSelectedProducts(4)
                        .build()},
     /*
                new Object[] {QuadrocopterData.builder()
                        .materials(Arrays.asList("пластик", "бальза"))
                        .minimalRange("200")
                        .engineType("бесколлекторный")
                        .build(),
                        ExpectedData.builder()
                        .numberOfSearchResults(87)
                        .numberOfSelectedProducts(4)
                        .build()}

      */
        };
    }


    @Test(dataProvider = "testData")
    public void radiocontrolAirTest(QuadrocopterData qcData, ExpectedData expData) throws InterruptedException {


        MainMenuData mainMenu = MainMenuData.builder()
                                .topMenuSection("Каталог")
                                .catalogCategory("Красота и спорт")
                                .catalogSubCategory("Хобби")
                                .productType("Радиоуправляемые авиамодели")
                                .menuHeaderItem("Радиоуправляемые авиамодели")
                                .build();

        ProductMenuData  productMenu = ProductMenuData.builder()
                                .leftMenuElement1("Тип")
                                .leftMenuItem("квадрокоптер")
                                .leftMenuElement2("Дальность действия, м")
                                .sortingItem("Дешевые")
                                .build();

        extentTest.info("Start scenario with the following data: " + qcData.toString());
        app.openHomePage()
                .openTopMenuSection(mainMenu.getTopMenuSection())
                .selectCatalogCategory(mainMenu.getCatalogCategory())
                .selectCatalogSubCategory(mainMenu.getCatalogSubCategory())
                .selectProductType(mainMenu.getProductType())
                .checkThatPageHeaderContains(mainMenu.getMenuHeaderItem())
                .scrollToLeftMenuElement(productMenu.getLeftMenuElement1())
                .selectLeftMenuItem(productMenu.getLeftMenuItem())
                .selectLeftMenuItem(qcData.getMaterials())
                .setMinimalRange(qcData.getMinimalRange())
                .scrollToLeftMenuElement(productMenu.getLeftMenuElement2())
                .openAdditionalParameters()
                .selectLeftMenuItem(qcData.getEngineType())
                .verifyThatNumberOfSearchResultsEqualsTo(expData.getNumberOfSearchResults())
                .scrollToPageHeader()
                .selectSortingBy(productMenu.getSortingItem())
                .verifyThatSortingIsCorrect()
                .selectProductByIndex(0)
                .selectProductByIndex(2)
                .selectProductByIndex(4)
                .selectProductByIndex(5)
                .verifyThatCompareButtonContainsNumberOfSelectedProducts(expData.getNumberOfSelectedProducts())
                .openComparison()
                .selectComparedProductWithIndex(1)
                .verifyThatProductDescription(productMenu.getLeftMenuItem())
                .verifyThatProductDescription(qcData.getMaterials())
                .verifyThatProductDescription(qcData.getEngineType())
                .addProductToCart()
                .verifyNumberOfProductsInCartHeader(1)
                .openCartHeader()
                .clickPlusButton()
                .verifyThatTotalPrice()
                .doCheckout()
                .verifyThatLoginPopup();

    }

    @Test (description = "Dummy test 2")

    public void dummyTest2() throws InterruptedException, IOException {

        log.info(" !!! Startring test: Test #2.2 !!!");
 //       Assert.assertEquals(2,4);

        System.out.println("Test #2.2 body");

    }


}
