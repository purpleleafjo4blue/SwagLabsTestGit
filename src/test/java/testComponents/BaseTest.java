package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;
	public WebDriver InitializeDriver() throws IOException
	{
		//Initialize GlobalProperties.properties
		Properties prop = new Properties();
		String propFilePath = System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties";
		FileInputStream fis = new FileInputStream(propFilePath);
		prop.load(fis);
		String browserName;
		if (System.getProperty("browser") != null)
		{
			browserName = System.getProperty("browser");
		}
		else
		{
			browserName = prop.getProperty("browser");
		}
		
		//Initializing the Driver using if else conditions based on GlobalProperties.properties file.
		if (browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("brave"))
		{
			ChromeOptions options = new ChromeOptions();
			String braveBrowserFilePath = "C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";
			options.setBinary(braveBrowserFilePath);
			driver = new ChromeDriver(options);
		}
		else if (browserName.equalsIgnoreCase("opera"))
		{
			ChromeOptions options = new ChromeOptions();
			String operaBrowserFilePath = "C:\\Users\\jo\\AppData\\Local\\Programs\\Opera\\opera.exe";
			options.setBinary(operaBrowserFilePath);
			driver = new ChromeDriver(options);
		}
		
		
		//Setting window size and implicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public void goTo() throws IOException
	{
		driver = InitializeDriver();
		driver.get("https://www.saucedemo.com/");
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File file = new File(filePath);
		FileUtils.copyFile(source, file); //throws IOException
		return filePath;
	}
}
