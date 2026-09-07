package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage extends AbstractComponent{
	public CheckoutInformationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "first-name")
	WebElement firstNameTextbox;
	
	@FindBy(id = "last-name")
	WebElement lastNameTextbox;
	
	@FindBy(id = "postal-code")
	WebElement postalCodeTextbox;
	
	@FindBy(id = "continue")
	WebElement continueButton;
	
	
	
	public void EnterInformation(String firstName, String lastName, String postalCode)
	{
		firstNameTextbox.sendKeys(firstName);
		lastNameTextbox.sendKeys(lastName);
		postalCodeTextbox.sendKeys(postalCode);
	}
	
	public CheckoutOverviewPage EnterInformationThenProceed(String firstName, String lastName, String postalCode)
	{
		firstNameTextbox.sendKeys(firstName);
		lastNameTextbox.sendKeys(lastName);
		postalCodeTextbox.sendKeys(postalCode);
		continueButton.click(); //Proceeds to the CheckoutOverviewPage
		return new CheckoutOverviewPage(driver);
	}
	
	
}
