package test;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.DataReader;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testComponents.BaseTest;

public class LoginValidationTest extends BaseTest{
	//<--- Tests --->
	//Test 1: Check if user can enter credentials in the username and password text fields.
	@Test(dataProvider = "DataFromExcel", groups = {"NoLogin"})
	public void ValidateLoginCredentials_NoClickLogin(String username, String password)
	{
		//Step 1: Enter credentials in the username and password fields.
		//Expected result: Entered credentials are displayed in the input fields.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.EnterCredentials(username, password); 
	}
	
	//Test 2: Check if user can login using valid credentials.
	@Test(dataProvider = "BasicLoginData", groups= {"login"})
	public void ValidateLoginCredentials(String username, String password)
	{
		//Step 1: Login
		//Expected result: User should be able to login using valid credentials.
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productPage = loginPage.Login(username, password);
		
		//Step 2: Check if user is logged in by confirming if a web element in the Home page is visible.
		WebElement logo = productPage.SwagLabsLogoText;
		productPage.WaitForElementToBeVisible(logo, 10);
		Assert.assertTrue(logo.isDisplayed());
	}
	
	
	//<--- Data Providers --->
	@DataProvider(name = "BasicLoginData")
	public Object[][] basicLoginData()
	{
		String password = "secret_sauce";
		return new Object[][] {
			{"standard_user", password},
			{"locked_out_user", password},
			{"problem_user", password}
		};
	}
	
	@DataProvider(name = "DataFromJson")
	public Object[][] dataFromJson() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "//src//test//java//Data//Data.json";
		DataReader dataReader = new DataReader();
		Object[][] result = dataReader.getJsonData(filePath);
		// create an Object[][] of size [data.size()][1] and populate via a loop
		return result;
		
	}
	
	@DataProvider(name = "DataFromExcel")
	public Object[][] dataFromExcel() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "//src//test//java//Data//Data.xlsx";
		DataReader dataReader = new DataReader();
		Object[][] result = dataReader.getExcelData(filePath, 0);
		return result;
	}
}
