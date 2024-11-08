package com.nikolaiefimov.tests.vendorportal;

import com.nikolaiefimov.pages.vendorportal.DashboardPage;
import com.nikolaiefimov.pages.vendorportal.LoginPage;
import com.nikolaiefimov.tests.AbstractTest;
import com.nikolaiefimov.util.Config;
import com.nikolaiefimov.util.Constants;
import com.nikolaiefimov.util.JsonUtil;
import com.nikolaiefimov.tests.vendorportal.model.VendorPortalTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest  extends AbstractTest {


    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }


    @Test
    private void loginTest() {
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    private void DashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //finance metrics validation
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "DashboardTest")
    public void LogoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
