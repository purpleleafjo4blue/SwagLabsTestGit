package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent{

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "user-name")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement loginButton;
	
	@FindBy(id = "login_credentials")
	public WebElement usernameListElement;
	
	@FindBy(className = "login_password")
	public WebElement passwordListElement;
	
	public void testClick_LoginButton()
	{
		loginButton.click();
	}
	
	public void EnterCredentials (String user, String pass)
	{
		username.sendKeys(user);
		password.sendKeys(pass);
	}
	
	public ProductsPage Login (String user, String pass)
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click(); //Proceeds from LoginPage to ProductsPage.
		return new ProductsPage(driver);
	}
}
