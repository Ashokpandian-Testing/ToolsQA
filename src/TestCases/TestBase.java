package TestCases;

import java.io.File;
import java.io.File.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
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
import org.apache.commons.io.FileUtils;
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
	
	
	
	@BeforeTest(alwaysRun=true)
	public static WebDriver initWebDriver(ITestContext context) {
//		extentlog = extentreport.createTest(context.getName());
	if(getConfigPropValue("browser").equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", getConfigPropValue("chromedriverpath"));	
//		extentlog.log(Status.PASS, MarkupHelper.createLabel("Chrome Browser Launched Successfully !", ExtentColor.GREEN));
		driver = new ChromeDriver();
	}			
	softassert = new SoftAssert();
	PropertyConfigurator.configure("log4j.properties");
	String Date = new SimpleDateFormat("ddmmyyyyhhmmss").format(new Date());
	htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/extentreport_"+context.getName()+Date+".html");
	extentreport.attachReporter(htmlreporter);
	htmlreporter.config().setReportName(context.getName());
	extentreport.setSystemInfo("Test Browser",getConfigPropValue("browser"));
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
	
	@AfterTest(alwaysRun=true)
	public static void killDriver() {
//		extentlog = extentreport.createTest("Closing Browser");
		driver.quit();
		logger.info("Browser Closed");
//		extentlog.log(Status.PASS, MarkupHelper.createLabel("Browser Closed Successfully !", ExtentColor.GREEN));
		extentreport.flush();
		
	}
	
	@BeforeMethod(alwaysRun=true)
	@Parameters("Env")
	public static void launchURL(@Optional("Testappurl") String url, Method method) {
		extentlog = extentreport.createTest(method.getName());
		driver.get(getConfigPropValue(url));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Application Successfully Launched ! - '"+driver.getCurrentUrl()+"'", ExtentColor.GREEN));
		//extentreport.setSystemInfo("Test Browser",url);
	}
	
	
// ITestListener Implementation starts
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentlog.log(Status.FAIL, MarkupHelper.createLabel("Test : '"+result.getMethod().getMethodName()+"' is failed",ExtentColor.RED));
		extentlog.log(Status.FAIL, MarkupHelper.createLabel("ERROR MESSAGE : '"+result.getThrowable()+"'",ExtentColor.TEAL));
		try {
			extentlog.addScreenCaptureFromPath(getScreenshot(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Test : '"+result.getMethod().getMethodName()+"' is Passed",ExtentColor.BLUE));
	}
	
	
	public static String getScreenshot(ITestResult result) throws IOException {		
		String date = new SimpleDateFormat("ddmmyyyhhmmss").format(new Date());		
		TakesScreenshot ss = (TakesScreenshot) driver;	
		File source = ss.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("User.dir")+"/Screenshots/"+result.getMethod().getMethodName()+"_"+date+".png";
		File finalDestination = new File(dest);
		FileUtils.copyFile(source, finalDestination);
		return finalDestination.toString();
	}
	
	

}
