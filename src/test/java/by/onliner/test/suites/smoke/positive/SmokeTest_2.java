package by.onliner.test.suites.smoke.positive;

import by.onliner.test.BaseTest;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;


@Log4j
public class SmokeTest_2 extends BaseTest {
    @Test(description = "SmokeTest_2")

    public void smokeTest_2() {
        log.info(" !!! Startring test: SmokeTest_2 !!!");
//       Assert.assertEquals(2,4);
//        log.debug("Startring test: Test #2");
//        log.warn("Startring test: Test #2");
//        log.error("Startring test: Test #2");
//        log.fatal("Startring test: Test #2");
        System.out.println("SmokeTest_2 body");
    }
}
