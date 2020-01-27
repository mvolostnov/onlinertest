package by.onliner.test.suites.smoke.positive;

import by.onliner.test.BaseTest;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;


@Log4j
public class SmokeTest_1 extends BaseTest {
    @Test(description = "SmokeTest_1")

    public void smokeTest_1() {
        log.info(" !!! Startring test: SmokeTest_1 !!!");
//       Assert.assertEquals(2,4);
//        log.debug("Startring test: Test #2");
//        log.warn("Startring test: Test #2");
//        log.error("Startring test: Test #2");
//        log.fatal("Startring test: Test #2");
        System.out.println("SmokeTest_1 body");
    }
}
