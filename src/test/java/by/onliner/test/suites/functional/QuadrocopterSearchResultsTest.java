package by.onliner.test.suites.functional;

import by.onliner.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Log4j
public class QuadrocopterSearchResultsTest extends BaseTest {


    @Test (description = "Dummy test")

    public void dummyTest() throws InterruptedException, IOException {

        log.info(" !!! Startring test: Test #2 !!!");
//       Assert.assertEquals(2,4);
//        log.debug("Startring test: Test #2");
//        log.warn("Startring test: Test #2");
//        log.error("Startring test: Test #2");
//        log.fatal("Startring test: Test #2");
/*
        // log(Status, details)
        test.log(Status.INFO, "This step shows usage of log(status, details)");

        // info(details)
        test.info("This step shows usage of info(details)");

        // log with snapshot
        test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());

        // test with snapshot
        test.addScreenCaptureFromPath("screenshot.png");

*/
        System.out.println("Test #2 body");
        //      driver.manage().window().setSize(new Dimension(1936, 1056));
    }


}

