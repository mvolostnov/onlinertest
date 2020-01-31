package by.onliner.test;

import com.maxim.testframework.utils.ExtentManager;
import by.onliner.test.data.TestContext;
import com.maxim.testframework.utils.PropertyLoader;
import by.onliner.webapp.WebApplication;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.maxim.testframework.webdriver.WebDriverInstance;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.ITestResult;
import org.testng.annotations.*;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@Log4j
public class BaseTest {

    //public static WebDriver driver;
    private static final String HOMEPAGE_URL = "https://onliner.by/";

    protected static WebApplication app = new WebApplication();
    private static final String SCREENSHOTS_PATH = "D:\\Automation\\GitHub\\onlinertest\\screenshots\\";

    private static ExtentReports extentReports;
    protected static ExtentTest extentTest;

    //for maven profiles
    protected static String baseUrl;

    protected static TestContext testContext = new TestContext();


    @BeforeSuite(alwaysRun = true)
    public void browserSetup() throws IOException {

        String envName = System.getProperty("environment", "stage").toLowerCase();
        PropertyLoader properties = new PropertyLoader();
        baseUrl = properties.getProperty(String.format("env/%s.properties", envName), "app.url");


        WebDriver driver = initDriver(BrowserType.CHROME);
        WebDriverInstance.setDriver(driver);
        BaseTest.log.info("Open browser " + driver);

        driver.manage().window().maximize();
        BaseTest.log.info("Maximize browser's window");

        driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);

        extentReports = ExtentManager.createInstance("extent.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extentReports.attachReporter(htmlReporter);

    }


    @BeforeMethod
    public void beforeEachTest(Method method) throws IOException, InterruptedException {

        extentTest = extentReports.createTest(getClass().getName());
        WebDriverInstance.getDriver().get(baseUrl);
        log.info("Open :" + baseUrl);
        Thread.sleep(5000);
        }


    @AfterSuite(alwaysRun = true)
    public void browserTearDown() {
        log.info("Close browser" + WebDriverInstance.getDriver());
        WebDriverInstance.getDriver().quit();
        WebDriverInstance.setDriver(null);

    }


    @SneakyThrows
    @AfterMethod (alwaysRun = true)//AfterMethod annotation - This method executes after every test execution
    public void afterMethod(ITestResult result){
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
            if (result.getStatus() == ITestResult.FAILURE) {
                try{
                // To create reference of TakesScreenshot
                TakesScreenshot screenshot=(TakesScreenshot)WebDriverInstance.getDriver();
                // Call method to capture screenshot
                File src=screenshot.getScreenshotAs(OutputType.FILE);
                // Copy files to specific location
                // result.getName() will return name of test case so that screenshot name will be same as test case name
                String screenShotPath = SCREENSHOTS_PATH + LocalDateTime.now().toString().replaceAll(":", "-") + result.getName() + ".png";
                FileUtils.copyFile(src, new File(screenShotPath));
                System.out.println("Successfully captured a screenshot");
                extentTest.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());

                } catch (Exception e){
                    System.out.println("Exception while taking screenshot "+e.getMessage());
                }

            }
            else if (result.getStatus() == ITestResult.SKIP)
                extentTest.skip(result.getThrowable());
            else
                extentTest.pass("Test passed");

            extentReports.flush();
        WebDriverInstance.getDriver().manage().deleteAllCookies();    }


    private WebDriver initDriver(String browserType) {
        if (browserType.equals(BrowserType.CHROME)) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        if (browserType.equals(BrowserType.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        if (browserType.equals(BrowserType.EDGE)) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
        return null;
    }

}
