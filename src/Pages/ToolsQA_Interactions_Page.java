package Pages;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class ToolsQA_Interactions_Page {
	
	
	@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),\"Interactions\")]")
	public static WebElement InteractionsTitle;
	
	@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),\"Widgets\")]")
	public static WebElement WidgetsTitle;
	
	@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),\"Tooltip and\")]")
	public static WebElement ToolTipTitle;

}
