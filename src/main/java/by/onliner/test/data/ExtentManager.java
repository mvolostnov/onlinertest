package by.onliner.test.data;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {


    private static ExtentReports extentReports;

    public static ExtentReports getInstance() {
        if (extentReports == null)
            createInstance("d:\\Automation\\GitHub\\onlinertest\\target\\test-output\\extent.html");

        return extentReports;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        return extentReports;
    }
}