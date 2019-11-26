package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sun.corba.se.impl.orbutil.GetPropertyAction;
import com.sun.glass.events.KeyEvent;

import Pages.ToolsQA_Home_Page;
import Pages.ToolsQA_Interactions_Page;

//@Listeners(TestCases.TestBase.class)
public class Test_ToolsQA_HomePage extends TestBase {
	
	@Test(priority=0, groups= {"Navigation","HomePage","Menu"}, alwaysRun=true)
	public static void Test01_TopMenu_InteractionsMenu() {
//		extentlog = extentreport.createTest("Interaction Menu - Navigation Check");
		PageFactory.initElements(TestBase.driver, ToolsQA_Home_Page.class);
		ToolsQA_Home_Page.InteractionsMenu.click();
		PageFactory.initElements(TestBase.driver, ToolsQA_Interactions_Page.class);		
		Assert.assertTrue(ToolsQA_Interactions_Page.InteractionsTitle.isDisplayed());
		logger.info("Interaction Menu - Navigation Check - Interaction Menu Navigation is successful !");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Interaction Menu - Navigation Check - Interaction Menu Navigation is successful !", ExtentColor.BLUE));
	}
	
	@Test(priority=2, groups= {"Navigation","HomePage","Menu"})
	public static void Test02_TopMenu_WidgetMenu() {
//		extentlog = extentreport.createTest("Widget Menu - Navigation Check");
		PageFactory.initElements(TestBase.driver, ToolsQA_Home_Page.class);
		ToolsQA_Home_Page.WidgetsMenu.click();
		PageFactory.initElements(TestBase.driver, ToolsQA_Interactions_Page.class);		
		softassert.assertTrue(ToolsQA_Interactions_Page.WidgetsTitle.isDisplayed());
		logger.info("Widget Menu - Navigation Check Test Passed - Widget Menu Navigation is successful !");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Widget Menu Navigation is successful !", ExtentColor.BLUE));
	}
	
	@Test(priority=1, groups= {"Navigation","HomePage","Menu"})
	public static void Test03_TopMenu_ToolTipsMenu() {
//		extentlog = extentreport.createTest("Tooltips Menu - Navigation Check");
		PageFactory.initElements(TestBase.driver, ToolsQA_Home_Page.class);
		ToolsQA_Home_Page.TooltipsMenu.click();
		PageFactory.initElements(TestBase.driver, ToolsQA_Interactions_Page.class);		
		softassert.assertTrue(ToolsQA_Interactions_Page.ToolTipTitle.isDisplayed());
		logger.info("Tooltips Menu - Navigation Check - Tooltip Menu Navigation is successful !");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Tooltip Menu Navigation is successful !", ExtentColor.BLUE));
	}
	

	
	

}
