package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "primary_header")
	WebElement header;
	
	@FindBy(className = "bm-burger-button")
	WebElement burgerButton;
	
	@FindBy(className = "shopping_cart_link")
	WebElement cartButton;
	
	@FindBy(id = "cancel")
	WebElement cancelButton;
	
	public void click_BurgerButton()
	{
		burgerButton.click();
	}
	
	public CartPage click_CartButton()
	{
		cartButton.click(); //proceeds to the CartPage
		return new CartPage(driver);
	}
	
	public void Cancel()
	{
		cancelButton.click();
	}
	
}
