package glue.gluewebapp.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static String sDirPath = System.getProperty("user.dir");
	public DesiredCapabilities capabilities = null;
	public static String configPropertyFilePath = sDirPath + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources";
	public static String sConfigPath = configPropertyFilePath + File.separator + "webconfig.properties";
	public static Properties configProps;
	protected static WebDriver driver;

	@BeforeSuite
	public synchronized void initialize() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		// Read configuration properties
		configProps = new Properties();
		System.out.println(sConfigPath);
		FileInputStream inputStream = new FileInputStream(sConfigPath);
		configProps.load(inputStream);
		inputStream.close();
		
		// Read launch browser method name from config Properties
		String launchBrowserMethodName = configProps.getProperty("LaunchBrowserName");
		System.out.println("BrowserName : "+configProps.getProperty("LaunchBrowserName"));

		// Call launch browser method using reflection (assuming methods are private)
		try
		{
			String webAppUrl = GenericLibrary.getConfigValue(sConfigPath, "webAppUrl");
			switch(launchBrowserMethodName)
			{
			
			case "chrome":
				ChromeOptions c = new ChromeOptions();
				c.addArguments("--remote-allow-origins=*");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(c);
				break;
				
			case "firefox":
				FirefoxOptions f = new FirefoxOptions();
				f.setCapability("moz:firefoxOptions", new FirefoxOptions().addArguments("--remote-allow-origins=*"));
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions ed= new EdgeOptions();
				ed.setCapability("acceptInsecureCerts", true);
				driver = new EdgeDriver();
				break;

			default:
				break;
			}
			
			driver.manage().window().maximize();
			driver.get(webAppUrl.startsWith("http") ? webAppUrl : "https://" + webAppUrl);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
			
	catch(SecurityException|IllegalArgumentException e)
	{
		throw new RuntimeException("Failed to call launch browser method: " + launchBrowserMethodName, e);
	}

}

	//@AfterSuite
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
