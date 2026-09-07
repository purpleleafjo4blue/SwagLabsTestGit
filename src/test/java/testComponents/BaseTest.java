package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;
	public WebDriver InitializeDriver() throws IOException
	{
		//Initialize GlobalProperties.properties
		Properties prop = new Properties();
		String propFilePath = System.getProperty("user.dir") + "//src//main//java//Resources//GlobalData.properties";
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
}
