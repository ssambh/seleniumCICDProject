package testCase;

import java.io.IOException;

import org.testng.annotations.Test;

import frameworkBase.BaseMain;
import pageObjects.OrangeHRMDashboard;
import pageObjects.OrangeHRMLoginPage;

public class OrangeCRMTest extends BaseMain{
	OrangeHRMLoginPage hrm;
	OrangeHRMDashboard dashboardHRM;
	
	@Test(testName = "Login to Orange CRM")
	public void loginToOrangeCRM() throws InterruptedException, IOException {
		hrm = new OrangeHRMLoginPage(driver);
		dashboardHRM = new OrangeHRMDashboard(driver);
		hrm.userNameField.sendKeys("admin");
		hrm.passwordField.sendKeys("admin123");
		hrm.passwordField.submit();
	}
	
	@Test(testName = "Logout of the Orange CRM application")
	public void logout() throws InterruptedException, IOException {
		hrm = new OrangeHRMLoginPage(driver);
		dashboardHRM = new OrangeHRMDashboard(driver);
		loginToOrangeCRM();
		dashboardHRM.profileDropdown.click();
		dashboardHRM.logoutDropDownOption.click();
	}
	

}
