package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends PredefinedActions {
    private static HomePage homePage;
    private final PropertyReading homePageProp;
    static Logger log = Logger.getLogger(HomePage.class);

    private HomePage() {
        //Private Constructor for Singleton Design Pattern
        homePageProp = new PropertyReading(ConstantPaths.PROP_PATH + "HomePageProp.properties");
    }

    public static HomePage getHomePage() {
        if (homePage == null)
            homePage = new HomePage();
        return homePage;
    }


    public String returnHeaderText() {
        return getElementText(getElement(homePageProp.getValue("compareExpensesHeader"), true));
    }

    public String getUrlData() {
        return returnUrl();
    }

    public void navigateToPreviousPage() {
        navigateBack();
    }

    public void clickOnAmountHead() {
        clickOnElement(homePageProp.getValue("amountTable"), true);
    }

    public List<Double> getAmountList() {
        return getWebElementListInDouble(homePageProp.getValue("amountList"), true);
    }

    public boolean isAmountListSorted() {
        List<Double> amountList = getAmountList();
        List<Double> tempList = new ArrayList<>(amountList);
        Collections.sort(tempList);
        return amountList.equals(tempList);
    }


}