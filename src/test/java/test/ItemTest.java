package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.DataReader;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testComponents.BaseTest;

public class ItemTest extends BaseTest{
	//<--- Tests --->
	
	//Test 1: Check if user can add an item to cart.
	@Test
	public void addItemToCart()
	{
		//Step 1: Login
		//Expected result: User should be able to login using valid credentials.
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		
		//Step 2: Add item to cart
		productsPage.addItemToCart("Sauce Labs Onesie");
	}
	
	//Test 2: Check if user can add multiple items to cart.
	@Test
	public void addMultipleItemsToCart()
	{
		String[] a_shopList = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};
		List<String> l_shopList = new ArrayList<>(Arrays.asList(a_shopList));
		
		//Step 1: Login
		//Expected result: User should be able to login using valid credentials.
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		
		//Step 2: Add multiple items to cart
		productsPage.addMultipleItemsToCart(l_shopList);
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
