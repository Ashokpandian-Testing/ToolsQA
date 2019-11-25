package TestCases;

import java.io.File;
import java.io.File.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Colors;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.apache.log4j.*;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.PropertyConfigurator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.FileUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestBase implements ITestListener {
	
	public static WebDriver driver;
	public static SoftAssert softassert;
	public static Logger logger = Logger.getLogger(TestBase.class);
	public static ExtentReports extentreport = new ExtentReports();
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentTest extentlog;
	
	
	
	@BeforeTest
	public static WebDriver initWebDriver(ITestContext context) {
		extentlog = extentreport.createTest("Launching Browser");
	if(getConfigPropValue("browser").equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", getConfigPropValue("chromedriverpath"));	
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Chrome Browser Launched Successfully !", ExtentColor.GREEN));
		driver = new ChromeDriver();
	}			
	softassert = new SoftAssert();
	PropertyConfigurator.configure("log4j.properties");
	htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/extentreport_"+context.getName()+".html");
	extentreport.attachReporter(htmlreporter);
	return driver;
	}	
	
	public static String getConfigPropValue(String key) {
		String value = null;
		
		try{
			FileInputStream fis = new FileInputStream("config.properties");
			Properties prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
			fis.close();
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
		return value;
	}
	
	@AfterTest
	public static void killDriver() {
		extentlog = extentreport.createTest("Closing Browser");
		driver.quit();
		logger.info("Browser Closed");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Browser Closed Successfully !", ExtentColor.GREEN));
		extentreport.flush();
		
	}
	
	@BeforeMethod
	@Parameters("Env")
	public static void launchURL(@Optional("Testappurl") String url) {
		extentlog = extentreport.createTest("Launching Application");
		driver.get(getConfigPropValue(url));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Application Successfully Launched !", ExtentColor.GREEN));
	}
	
	
// ITestListener Implementation starts
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentlog.log(Status.FAIL, MarkupHelper.createLabel("Test : '"+result.getMethod().getMethodName()+"' is failed",ExtentColor.RED));
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Test : '"+result.getMethod().getMethodName()+"' is Passed",ExtentColor.BLUE));
	}
	
	
	public static void getScreenshot(ITestResult result) {
		
		String date = new SimpleDateFormat("ddmmyyyhhmmss").format(new Date());
		
		TakesScreenshot ss = (TakesScreenshot) driver;	
		File source = ss.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("User.dir")+"/Screenshots/"+result.getMethod().getMethodName()+"_"+date+".png";
		File finalDestination = new File(dest);
//		FileUtils.copyFile(screenshot, newFileName);
	
		
	}
	
	

}
