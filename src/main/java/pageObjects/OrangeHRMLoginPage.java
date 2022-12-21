package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRMLoginPage {

	//private static WebDriver driver;
	
	public OrangeHRMLoginPage(WebDriver driver) {
		//this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[contains(text(),'Username : ')]")
	public WebElement userName;
	
	@FindBy(xpath="//*[@class='orangehrm-login-branding']")
	public WebElement loginPageLogo;
	
	@FindBy(xpath="//*[contains(text(),'Password : ')]")
	public WebElement password;
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement userNameField;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement passwordField;
	
	public String getUsername() {
		String username = userName.getText().substring(12);
		return username;
	}
	
	public String getPassword() {
		String pword = password.getText().substring(12);
		return pword;
	}
}
