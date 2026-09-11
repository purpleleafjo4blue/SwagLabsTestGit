package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.DataReader;
import pageObjects.CartPage;
import pageObjects.CheckoutInformationPage;
import pageObjects.CheckoutOverviewPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import testComponents.BaseTest;

public class EndToEndTest extends BaseTest{
	//<--- Tests --->
	//@Test
	public void sampleEndToEnd_OriginalCode()
	{
		String a_ShopList[] = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt"};
		List<String> l_ShopList = new ArrayList<String>(Arrays.asList(a_ShopList)); 
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.Login("standard_user", "secret_sauce");
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.addMultipleItemsToCart(l_ShopList);
		productsPage.click_CartButton();
		CartPage cartPage = new CartPage(driver);
		cartPage.confirmItemNamesAndPrices();
		cartPage.Checkout();
		CheckoutInformationPage checkInfoPage = new CheckoutInformationPage(driver);
		//checkInfoPage.EnterInformation("Alfredo Marquis", "Villegas", "1108");
		checkInfoPage.EnterInformationThenProceed("Alfredo Marquis", "Villegas", "1108");
		CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
		checkoutOverviewPage.Finish();
	}
	
	@Test
	public void sampleEndToEnd()
	{
		String a_ShopList[] = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt"};
		List<String> l_ShopList = new ArrayList<String>(Arrays.asList(a_ShopList)); 
		
		LoginPage loginPage = new LoginPage(driver);
		ProductsPage productsPage = loginPage.Login("standard_user", "secret_sauce");
		productsPage.addMultipleItemsToCart(l_ShopList);
		CartPage cartPage = productsPage.click_CartButton();
		cartPage.confirmItemNamesAndPrices();
		CheckoutInformationPage checkInfoPage = cartPage.Checkout();
		CheckoutOverviewPage checkoutOverviewPage = checkInfoPage.EnterInformationThenProceed("Alfredo Marquis", "Villegas", "1108");
		checkoutOverviewPage.Finish();
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
