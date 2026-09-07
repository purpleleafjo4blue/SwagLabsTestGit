package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends AbstractComponent{
	public CheckoutCompletePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "back-to-products")
	WebElement backHomeButton;
	
	@FindBy(id = "generate-pdf-order")
	WebElement generatePDFbutton;
	
	public void backHome()
	{
		backHomeButton.click();
	}
	
	public void generatePDFbutton()
	{
		generatePDFbutton.click();
	}
}
