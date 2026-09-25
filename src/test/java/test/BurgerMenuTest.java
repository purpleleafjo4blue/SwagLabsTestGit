package test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.DataReader;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testComponents.BaseTest;

public class BurgerMenuTest extends BaseTest{		
	//<--- Tests --->
	@Test
	public void CheckLink_AllItemsList()
	{
		//Step 1: Login
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		
		//Step 2: Click Add to Cart button
		productsPage.click_CartButton();
		
		//Step 3: Click Burger menu button > Click "All Items" link
		//Expected: Clicking the "All Items" link should direct the user back to home page.
		productsPage.burgerButton_AllItems();
		
		//Step 4: In the home page, wait for the span title text "Products" and confirm if its displayed in the page.
		WebElement title = productsPage.spanTitle;
		productsPage.WaitForElementToBeVisible(title, 10);
		Assert.assertTrue(title.isDisplayed());
	}
	
	@Test
	public void CheckLink_About()
	{
		//Step 1: Login
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		
		//Step 2: Click Burger menu button > Click "About" link
		//Clicking the "About" link should direct player to another website.
		productsPage.burgerButton_About();
		
		//Step 3: Wait for a web element in the newly opened website to be displayed first.
		productsPage.WaitForElementToBeVisible(driver.findElement(By.className("hero-heading")), 10);
		
		//Step 4: Confirm if the current URL is the newly opened website.
		Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
	}
	
	@Test
	public void CheckLink_Logout()
	{
		//Step 1: Login
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		
		//Step 2: Click Burger menu button > Click "Logout" link
		//Expected: Clicking the "Logout" link should log out the user and direct them to the Login page.
		productsPage.burgerButton_LogOut();
		
		//Step 3: Confirm if user is logged out by confirming that they are in the Login page. 
		//Confirm that they are in login page by checking if a Login page web element is there.
		WebElement usernameList = loginPage.usernameListElement;
		loginPage.WaitForElementToBeVisible(usernameList, 10);
		Assert.assertTrue(usernameList.isDisplayed());
	}
	
	@Test
	public void CheckResetAppState()
	{
		//Step 1: Login
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		
		//Step 2: Click Burger menu button > Click "Reset App State" link
		productsPage.burgerButton_resetAppState();
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
