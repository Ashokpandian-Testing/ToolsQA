package Pages;


import org.openqa.selenium.support.FindBy;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ToolsQA_Interactions_Page {
	
	
	@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),\"Interactions\")]")
	public static WebElement InteractionsTitle;
	
	@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),\"Widgets\")]")
	public static WebElement WidgetsTitle;
	
	@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),\"Tooltip and\")]")
	public static WebElement ToolTipTitle;
	
	
	@FindBy(linkText="Sortable")
	public static WebElement Sortable;
	
	
	@FindBy(linkText="Selectable")
	public static WebElement Selectable;
	
	
	@FindBy(xpath="//*[@id=\"selectable\"]/li")
	public static List<WebElement> SelectableList;
	
	
	@FindBy(linkText="Droppable")
	public static WebElement Droppable;
	
	
	@FindBy(id="draggable")
	public static WebElement DraggableSource;
	
	@FindBy(id="droppable")
	public static WebElement DraggableDest;
	
	
	@FindBy(linkText="Resizable")
	public static WebElement Resizable;
	
	@FindBy(className="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se")
	public static WebElement ResizeableHandle;
	
	
	
	
	@FindBy(linkText="Draggable")
	public static WebElement Draggable;
	
	
	

}
