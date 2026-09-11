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

public class ItemTesting extends BaseTest{
	//<--- Tests --->
	@Test
	public void addItemToCart()
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.Login("standard_user", "secret_sauce");
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addItemToCart("Sauce Labs Onesie");
	}
	
	@Test
	public void addMultipleItemsToCart()
	{
		String[] a_shopList = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};
		List<String> l_shopList = new ArrayList<>(Arrays.asList(a_shopList));
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.Login("standard_user", "secret_sauce");
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addMultipleItemsToCart(l_shopList);
	}
	
	
	//<--- Data Providers --->
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
