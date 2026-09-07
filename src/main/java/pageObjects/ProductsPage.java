package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends AbstractComponent{

	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "inventory_item")
	List<WebElement> items;
	
	public void addItemToCart(String selectedItem)
	{
		for (int i = 0; i<items.size(); i++)
		{
			WebElement item = items.get(i);
			if (item.findElement(By.className("inventory_item_name")).getText().equals(selectedItem))
			{
				item.findElement(By.className("btn_inventory")).click();
			}
		}
	}
	
	public void addMultipleItemsToCart(List<String> selectedItems)
	{
		for (int i = 0; i<items.size(); i++)
		{
			for (int j = 0; j<selectedItems.size(); j++)
			{
				WebElement item = items.get(i);
				if (item.findElement(By.className("inventory_item_name")).getText().equals(selectedItems.get(j)))
				{
					item.findElement(By.className("btn_inventory")).click();
				}
			}
		}
	}
}
