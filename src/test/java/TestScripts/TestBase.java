package TestScripts;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Pages.*;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBase {

    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
        System.setProperty("current.date.time", sdf.format(new Date()));
        PropertyConfigurator.configure(ConstantPaths.LOG4J_PATH);

    }

    @BeforeMethod
    public void openBrowser() {
        PredefinedActions.initializeBrowser("https://sakshingp.github.io/assignment/login.html", "chrome");
    }

    LoginPage getLoginPageObj() {
        if (loginPage == null)
            loginPage = LoginPage.getLoginPage();
        return loginPage;
    }


    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
            PredefinedActions.takeScreenshot(result.getName());
        PredefinedActions.closeBrowser();
    }
}
