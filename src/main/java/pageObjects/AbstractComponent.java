package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "primary_header")
	WebElement header;
	
	@FindBy(id = "react-burger-menu-btn")
	WebElement burgerButton;
	
	@FindBy(className = "shopping_cart_link")
	WebElement cartButton;
	
	@FindBy(id = "cancel")
	WebElement cancelButton;
	
	@FindBy(className = "app_logo")
	public WebElement SwagLabsLogoText;
	
	@FindBy(id = "react-burger-menu-btn")
	WebElement burgerMenuButton;
	
	@FindBy(className = "bm-menu")
	WebElement burgerMenu;
	
	@FindBy(xpath = "//nav[@class = 'bm-item-list']/a")
	List<WebElement> burgerMenuLinks;
	
	public void WaitForElementToBeVisible(WebElement element, long seconds)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void WaitForElementToBeInvisible(WebElement element, long seconds)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void click_BurgerButton()
	{
		//Click Burger menu button
		burgerButton.click();
	}
	
	public void burgerButton_AllItems()
	{
		//Click Burger menu button
		burgerButton.click();
		
		//After clicking the burger menu button, wait for the burger menu to display first.
		WaitForElementToBeVisible(burgerMenu, 10);
		
		//Among the burger menu links, look for "All Items" link then click on it.
		for (int i = 0; i<burgerMenuLinks.size(); i++)
		{
			WebElement burgerMenuLink = burgerMenuLinks.get(i);
			if (burgerMenuLink.getText().equals("All Items"))
			{
				burgerMenuLink.click();
			}
		}
	}
	
	public void burgerButton_About()
	{
		burgerButton.click();
		WaitForElementToBeVisible(burgerMenu, 10);
		for (int i = 0; i<burgerMenuLinks.size(); i++)
		{
			WebElement burgerMenuLink = burgerMenuLinks.get(i);
			if (burgerMenuLink.getText().equals("About"))
			{
				burgerMenuLink.click();
			}
		}
	}
	
	public void burgerButton_clickTestAbout()
	{
		burgerButton.click();
		WaitForElementToBeVisible(burgerMenu, 10);
		for (int i = 0; i<burgerMenuLinks.size(); i++)
		{
			WebElement burgerMenuLink = burgerMenuLinks.get(i);
			if (burgerMenuLink.getText().equals("About"))
			{
				burgerMenuLink.click();
			}
		}
	}
	
	public void burgerButton_LogOut()
	{
		burgerButton.click();
		WaitForElementToBeVisible(burgerMenu, 10);
		for (int i = 0; i<burgerMenuLinks.size(); i++)
		{
			WebElement burgerMenuLink = burgerMenuLinks.get(i);
			if (burgerMenuLink.getText().equals("Logout"))
			{
				burgerMenuLink.click();
			}
		}
	}
	
	public void burgerButton_resetAppState()
	{
		burgerButton.click();
		WaitForElementToBeVisible(burgerMenu, 10);
		for (int i = 0; i<burgerMenuLinks.size(); i++)
		{
			WebElement burgerMenuLink = burgerMenuLinks.get(i);
			if (burgerMenuLink.getText().equals("Reset App State"))
			{
				burgerMenuLink.click();
			}
		}
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
