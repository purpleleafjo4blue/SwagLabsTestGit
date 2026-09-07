package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponent{
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(className = "cart_item")
	List<WebElement> cartItems;
	
	@FindBy(id = "continue-shopping")
	WebElement continueShoppingButton;
	
	@FindBy(id = "checkout")
	WebElement checkoutButton;
	
	
	public void confirmItemNames()
	{
		for (int i = 0; i<cartItems.size(); i++)
		{
			System.out.println(cartItems.get(i).findElement(By.className("inventory_item_name")).getText());
		}
	}
	
	public void confirmItemNamesAndPrices()
	{
		for (int i = 0; i<cartItems.size(); i++)
		{
			WebElement item = cartItems.get(i);
			String itemName = item.findElement(By.className("inventory_item_name")).getText();
			String priceName = item.findElement(By.className("inventory_item_price")).getText();
			String iCounter;
			if (i == 0)
			{
				iCounter = "1st item is ";
			}
			else if (i == 1)
			{
				iCounter = "2nd item is ";
			}
			else if (i == 2)
			{
				iCounter = "3rd item is ";
			}
			else
			{
				iCounter = (i+1) + "th item is ";
			}
			System.out.println(iCounter + itemName + ", Price is " + priceName);
		}
	}

	public void ContinueShopping()
	{
		continueShoppingButton.click();
	}
	
	public CheckoutInformationPage Checkout()
	{
		checkoutButton.click(); //Proceeds to the CheckoutInformationPage
		return new CheckoutInformationPage(driver);
	}
}
