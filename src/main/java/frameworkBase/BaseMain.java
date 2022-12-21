package frameworkBase;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;


public class BaseMain {
	
	public static WebDriver driver;
	public ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	@BeforeTest
	public void beforeTestMethod() {
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Report");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Sarang Sambharia");
		
	}
	
	@BeforeMethod
	@Parameters({"browser"})
	public void beforeMethodMethod(String browser, Method methodName) {
		logger = extent.createTest(methodName.getAnnotation(Test.class).testName());		
		setupDriver(browser);		
		System.out.println(driver);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));		
	}
	
	@AfterMethod
	public void afterMethodMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Markup m = MarkupHelper.createLabel("Test case: " + result.getMethod().getMethodName() + " Failed", ExtentColor.RED);
			logger.fail(m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			Markup m = MarkupHelper.createLabel("Test case: " + result.getMethod().getMethodName() + " Skipped", ExtentColor.YELLOW);
			logger.skip(m);
		}else if (result.getStatus() == ITestResult.SUCCESS) {
			Markup m = MarkupHelper.createLabel("Test case: " + result.getMethod().getMethodName() + " Passed", ExtentColor.GREEN);
			logger.pass(m);
		}
		driver.quit();
	}
	
	@AfterTest
	public void afterTestMethod() {
		extent.flush();
	}
	
	public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
}
