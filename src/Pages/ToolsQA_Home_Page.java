package Pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


public class ToolsQA_Home_Page {
	
	
	@FindBy(xpath="//ul[@id='menu-top']/li[2]")
	public static WebElement InteractionsMenu;
	
	@FindBy(xpath="//ul[@id='menu-top']/li[3]")
	public static WebElement WidgetsMenu;
	
	@FindBy(xpath="//ul[@id='menu-top']/li[4]")
	public static WebElement TooltipsMenu;
	
	
	

}
