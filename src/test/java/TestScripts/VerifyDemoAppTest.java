package TestScripts;

import Pages.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import static Pages.HomePage.getHomePage;


public class VerifyDemoAppTest extends TestBase {
    static Logger log = Logger.getLogger(VerifyDemoAppTest.class);


    //Validate that the user is able to navigate to the login page.
    @Test
    public void validateUserIsAbleToAccessUrlAndLandsOnTheLoginPage() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertEquals(loginPage.getUrlData(), "https://sakshingp.github.io/assignment/login.html", "actual and expected url not matched");
        Assert.assertEquals(loginPage.getLoginPageHeading(), "Login Form", "User is not on Login page");
    }


    // verify username placeholder label
    @Test
    public void verifyUsernameLabel() {
        LoginPage loginPage = LoginPage.getLoginPage();
        String actualUsernameLabel = loginPage.getUserNameLabel();
        Assert.assertEquals(actualUsernameLabel, "Username", "labels do not match");
    }


    // verify password placeholder label
    @Test
    public void verifyPasswordLabel() {
        LoginPage loginPage = LoginPage.getLoginPage();
        String actualUsernameLabel = loginPage.getPassWordLabel();
        Assert.assertEquals(actualUsernameLabel, "Password", "labels do not match");

    }

    //Validate loginPage logo is visible
    @Test
    public void validateLoginPageLogoVisibility() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertTrue(loginPage.isLogoPresent(), "Logo is not visible");
    }

    //Validate socialMedia login icons are visible
    @Test
    public void validateSocialMediaLoginIcons() {
        LoginPage loginPage = LoginPage.getLoginPage();
        log.info(loginPage.isSocialMediaLoginIconsVisible());
    }


    //Validate username and password pre-logo are visible
    @Test
    public void validateUsernamePasswordPreLogosVisible() {
        LoginPage loginPage = LoginPage.getLoginPage();
        log.info(loginPage.isUsernamePasswordPreLogosVisible());
    }

    //verify login button text
    @Test
    public void verifyTextOnLoginBtn() {
        LoginPage loginPage = LoginPage.getLoginPage();
        String actualLoginBtnText = loginPage.getLoginBtnText();
        Assert.assertEquals(actualLoginBtnText, "Log In", "actual and expected text do not match for login btn");

    }

    //verify checkbox remember me text
    @Test
    public void verifyTextForRememberMeCheckBox() {
        LoginPage loginPage = LoginPage.getLoginPage();
        String actualCheckBoxText = loginPage.getRememberMeCheckBoxText();
        Assert.assertEquals(actualCheckBoxText, "Remember Me", "actual and expected text do not match for login btn");

    }

    //validate whether remember me checkBox is enabled
    @Test
    public void rememberMeCheckBoxIsEnabled() {
        LoginPage loginPage = getLoginPageObj();
        boolean flag = loginPage.isRememberMeCheckBoxEnabled();
        Assert.assertTrue(flag, "checkbox is not enabled in the login page");
    }

    //Validate username & Password field is enabled
    @Test
    public void validateUsernameAndPasswordFieldEnabled() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertTrue(loginPage.isUsernameFieldEnabled(), "Username field is disabled");
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field is disabled");
    }

    // Validate Placeholder is visible
    @Test
    public void validatePlaceholderIsVisible() {
        LoginPage loginPage = LoginPage.getLoginPage();
        Assert.assertEquals(loginPage.getUsernamePlaceholder(), "Enter your username", "Username placeholder doesn't match");
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), "Enter your password", "Password placeholder doesn't match");
    }

    //Validate login without username and password
    @Test
    public void validateLoginWithoutUsernameAndPassword() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentialsForLogin("", "");
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsg(), "Both Username and Password must be present", "Inappropriate msg is displayed");
    }


    //Validate login without password
    @Test
    public void validateLoginWithoutPassword() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentialsForLogin("sk", "");
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsg(), "Password must be present", "Inappropriate msg is displayed");
    }

    //Validate login without username
    @Test
    public void validateLoginWithoutUsername() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentialsForLogin("", "abc");
        loginPage.clickOnLoginBtn();
        Assert.assertEquals(loginPage.getErrorMsg(), "Username must be present", "Inappropriate msg is displayed");
    }

    //validate that using valid credentials user gets access to home page.
    @Test
    public void loginWithValidCredentialsAndVerifyUserDirectsToHomePage() {
        LoginPage loginPage = getLoginPageObj();
        loginPage.enterCredentialsForLogin("Sk", "123");
        loginPage.clickOnLoginBtn();
        HomePage homePage = getHomePage();
        String actualHeader = homePage.returnHeaderText();
        Assert.assertEquals(actualHeader, "Compare Expenses", "Header text not equal");
        String homePageUrl = homePage.getUrlData();
        Assert.assertEquals(homePageUrl, "https://sakshingp.github.io/assignment/home.html", "Actual and expected url not matched");
    }

    //Validate login with username & password
    @Test
    public void validateLoginWithWhiteSpaces() {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentialsForLogin("    ", "    ");
        loginPage.clickOnLoginBtn();
        Assert.assertNotEquals(loginPage.getUrlData(), "https://sakshingp.github.io/assignment/login.html", "User has logged in homepage");
    }

    //Validate login with Remember Me Functionality
    @Test
    public void validateRememberMeFunctionality() throws InterruptedException {
        LoginPage loginPage = LoginPage.getLoginPage();
        loginPage.enterCredentialsForLogin("sk", "abc");
        loginPage.clickOnRememberMe();
        Assert.assertTrue(loginPage.rememberMeCheckBoxIsSelected(), "Remember me checkbox is not selected");
        loginPage.clickOnLoginBtn();
        Thread.sleep(5000);
        HomePage homePage = getHomePage();
        homePage.navigateToPreviousPage();
        Assert.assertEquals(loginPage.getUsername(), "sk", "Remember me function is not working correctly");
        loginPage.clickOnLoginBtn();
    }

    //Validate amount values given are sorted.
    @Test
    public void validateAmountListSorted() {
        loginWithValidCredentialsAndVerifyUserDirectsToHomePage();
        HomePage homePage = HomePage.getHomePage();
        log.info("Amount List Before click on Table head -> " + homePage.getAmountList());
        homePage.clickOnAmountHead();
        log.info("Amount List After click on Table head -> " + homePage.getAmountList());
        Assert.assertTrue(homePage.isAmountListSorted(), "List is not sorted");
    }
}


