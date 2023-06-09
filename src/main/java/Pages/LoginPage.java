package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends PredefinedActions {
    private static LoginPage loginPage;
    private final PropertyReading loginPageProp;
    static Logger log = Logger.getLogger(LoginPage.class);

    private LoginPage() {
        //Private Constructor for Singleton Design Pattern
        loginPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "LoginPageProp.properties");
    }

    public static LoginPage getLoginPage() {
        if (loginPage == null)
            loginPage = new LoginPage();
        return loginPage;
    }


    //logosVisible
    public String getLoginPageHeading() {
        return getElementText(getElement(loginPageProp.getValue("loginFormHeading"), true));
    }

    public String getUserNameLabel() {
        return getElementText(getElement(loginPageProp.getValue("userNameLabel"), true));
    }

    public String getPassWordLabel() {
        return getElementText(getElement(loginPageProp.getValue("passwordLabel"), true));
    }

    public String getLoginBtnText() {
        return getElementText(getElement(loginPageProp.getValue("loginBtn"), true));
    }

    public String getRememberMeCheckBoxText() {
        return getElementText(getElement(loginPageProp.getValue("rememberMeCheckBoxText"), true));
    }

    public void enterCredentialsForLogin(String username, String password) {
        enterText(getElement(loginPageProp.getValue("usernameField"), true), username);
        enterText(getElement(loginPageProp.getValue("passwordField"), true), password);
    }

    public void clickOnLoginBtn() {
        getElement(loginPageProp.getValue("loginBtn"), true).click();
    }

    public void clickOnRememberMe() {
        getElement(loginPageProp.getValue("rememberMeCheckBox"), true).click();
    }

    public boolean rememberMeCheckBoxIsSelected() {
        return getElement(loginPageProp.getValue("rememberMeCheckBox"), true).isSelected();
    }

    public String getUrlData() {
        return returnUrl();
    }

    public boolean isLogoPresent() {
        return isElementDisplayed(loginPageProp.getValue("logo"), true);
    }

    public List<Boolean> isSocialMediaLoginIconsVisible() {
        List<WebElement> iconList = getWebElementList(loginPageProp.getValue("socialMediaIcons"), true);
        List<Boolean> boolList = new ArrayList<>();
        for (WebElement element : iconList) {
            boolList.add(element.isDisplayed());
        }
        return boolList;
    }

    public List<Boolean> isUsernamePasswordPreLogosVisible() {
        List<WebElement> iconList = getWebElementList(loginPageProp.getValue("usernamePasswordPreLogo"), true);
        List<Boolean> boolList = new ArrayList<>();
        for (WebElement element : iconList) {
            boolList.add(element.isDisplayed());
        }
        return boolList;
    }

    public boolean isRememberMeCheckBoxEnabled() {
        return getElement(loginPageProp.getValue("rememberMeCheckBox"), true).isEnabled();
    }

    public boolean isUsernameFieldEnabled() {
        return getElement(loginPageProp.getValue("usernameField"), true).isEnabled();
    }

    public boolean isPasswordFieldEnabled() {
        return getElement(loginPageProp.getValue("passwordField"), true).isEnabled();
    }

    public String getUsernamePlaceholder() {
        return getAttribute(getElement(loginPageProp.getValue("usernameField"), true), "placeholder");
    }

    public String getPasswordPlaceholder() {
        return getAttribute(getElement(loginPageProp.getValue("passwordField"), true), "placeholder");
    }

    public String getErrorMsg() {
        return getElementText(getElement(loginPageProp.getValue("errorMsg"), true));
    }

    public String getUsername() {
        return getAttribute(getElement(loginPageProp.getValue("usernameField"), true), "value");
    }


}