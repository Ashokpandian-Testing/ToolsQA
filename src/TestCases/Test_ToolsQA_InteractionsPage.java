package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
public class Test_ToolsQA_InteractionsPage extends TestBase {
	
	@Test(priority=1, groups= {"Selectable","Interactions"})
	public static void Test01_Interactions_Selectable() throws InterruptedException {
//		extentlog = extentreport.createTest("Interaction Menu - Navigation Check");
		PageFactory.initElements(TestBase.driver, ToolsQA_Home_Page.class);
		ToolsQA_Home_Page.InteractionsMenu.click();
		PageFactory.initElements(TestBase.driver, ToolsQA_Interactions_Page.class);	
		ToolsQA_Interactions_Page.Selectable.click();
		Actions actions = new Actions(driver);
		
		actions.keyDown(Keys.CONTROL).click(ToolsQA_Interactions_Page.SelectableList.get(0))
				.click(ToolsQA_Interactions_Page.SelectableList.get(4))
						.click(ToolsQA_Interactions_Page.SelectableList.get(2)).build().perform();
		Thread.sleep(3000);
		logger.info("Test01_Interactions_Selectable - is successful !");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Test01_Interactions_Selectable - is successful !", ExtentColor.BLUE));
	}	
	
	
	@Test(priority=0, groups= {"Droppable","Interactions"})
	public static void Test02_Interactions_Droppable() throws InterruptedException {
//		extentlog = extentreport.createTest("Interaction Menu - Navigation Check");
		PageFactory.initElements(TestBase.driver, ToolsQA_Home_Page.class);
		ToolsQA_Home_Page.InteractionsMenu.click();
		PageFactory.initElements(TestBase.driver, ToolsQA_Interactions_Page.class);	
		ToolsQA_Interactions_Page.Droppable.click();		
		Actions actions = new Actions(driver);
		actions.clickAndHold(ToolsQA_Interactions_Page.DraggableSource);
		Thread.sleep(5000);
		actions.moveToElement(ToolsQA_Interactions_Page.DraggableDest);
		Thread.sleep(5000);
		actions.release(ToolsQA_Interactions_Page.DraggableDest).build().perform();
//		actions.keyDown(Keys.CONTROL).click(ToolsQA_Interactions_Page.SelectableList.get(0))
//				.click(ToolsQA_Interactions_Page.SelectableList.get(4))
//						.click(ToolsQA_Interactions_Page.SelectableList.get(2)).build().perform();
//		Thread.sleep(3000);
		logger.info("Test02_Interactions_Droppable - is successful !");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Test02_Interactions_Droppable - is successful !", ExtentColor.BLUE));
	}	
	
	
	
	@Test(priority=0, groups= {"Resizeable","Interactions"})
	public static void Test02_Interactions_Resizeable() throws InterruptedException {
//		extentlog = extentreport.createTest("Interaction Menu - Navigation Check");
		PageFactory.initElements(TestBase.driver, ToolsQA_Home_Page.class);
		ToolsQA_Home_Page.InteractionsMenu.click();
		PageFactory.initElements(TestBase.driver, ToolsQA_Interactions_Page.class);	
		ToolsQA_Interactions_Page.Resizable.click();		
		Actions actions = new Actions(driver);
		actions.clickAndHold(ToolsQA_Interactions_Page.ResizeableHandle).moveByOffset(250, 600).release().build().perform();
		Thread.sleep(5000);
		logger.info("Test02_Interactions_Resizeable - is successful !");
		extentlog.log(Status.PASS, MarkupHelper.createLabel("Test02_Interactions_Resizeable - is successful !", ExtentColor.BLUE));
	}	
	

}
