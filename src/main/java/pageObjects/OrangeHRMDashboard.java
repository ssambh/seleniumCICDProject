package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRMDashboard {
	private static WebDriver driver;
	
	public OrangeHRMDashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h6[text()='Dashboard']")
	public WebElement dashBoardHeading;
	
	@FindBy(xpath="//li[@class='oxd-userdropdown']//p")
	public WebElement profileDropdown;
	
	@FindBy(xpath="//a[text()='Logout']")
	public WebElement logoutDropDownOption;
}
