package glue.gluewebapp.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import glue.gluewebapp.library.BaseClass;
import glue.gluewebapp.library.GenericLibrary;
import glue.gluewebapp.library.Log;
import glue.gluewebapp.pages.HomePage;
import glue.gluewebapp.pages.LoginPage;

public class LoginTest extends BaseClass {
	LoginPage logEle;
	HomePage homEle;
	Actions actions;

	@BeforeClass
	public void intitialize() {
		logEle = new LoginPage(driver);
		homEle = new HomePage(driver);
		actions = new Actions(driver);
	}

	@BeforeMethod
	public void clearFields() {
		String os = System.getProperty("os.name").toLowerCase();// Check if the system is macOS
	    Keys commandOrControl = os.contains("mac") ? Keys.COMMAND : Keys.CONTROL;
	    
		actions.click(logEle.getEmailField()).keyDown(commandOrControl).sendKeys("a").keyUp(commandOrControl)
				.keyDown(Keys.DELETE).build().perform();
		actions.click(logEle.getPasswordField()).keyDown(commandOrControl).sendKeys("a").keyUp(commandOrControl)
				.keyDown(Keys.DELETE).build().perform();
	}

	// Login with Empty email
	@Test(priority = 0)
	public void emptyEmailLoginTest() {
		try {
			// actions.click(logEle.getPasswordField()).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(GenericLibrary.getConfigValue(sConfigPath,"inValidPassword")).build().perform();
			logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
			logEle.getLoginButton().click();
			WebElement emailErrorMessage = driver
					.findElement(By.xpath("//p[text()=\"Invalid email domain. Please enter a valid company email.\"]"));
			Assert.assertTrue(emailErrorMessage.isDisplayed());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Login with Empty password
	@Test(priority = 1)
	public void emptyPasswordLogin() {
		try {
			// actions.click(logEle.getEmailField()).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(GenericLibrary.getConfigValue(sConfigPath, "validEmail")).build().perform();
			logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "inValidEmail"));
			logEle.getLoginButton().click();
			WebElement passwordErrorMessage = driver.findElement(By.xpath("//p[text()=\"Password is required.\"]"));
			GenericLibrary.elementVisibility(passwordErrorMessage);
			Assert.assertTrue(passwordErrorMessage.isDisplayed());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Login with Invalid Email
	@Test(priority = 2)
	public void invalidEmailLoginTest() {
		try {
			logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "inValidEmail"));
			logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "validPassword"));
			logEle.getLoginButton().click();
			WebElement loginErrorMessage = driver.findElement(By.xpath("//p[text()=\"Invalid email or password\"]"));
			Assert.assertTrue(loginErrorMessage.isDisplayed());	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	// Login with Invalid Password
	@Test(priority = 3)
	public void invalidPasswordLoginTest() {
		try {
			logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "validEmail"));
			logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
			logEle.getLoginButton().click();
			WebElement loginErrorMessage = driver.findElement(By.xpath("//p[text()=\"Invalid email or password\"]"));
			Assert.assertTrue(loginErrorMessage.isDisplayed());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Password is visible on click of Eye icon
	@Test(priority = 4)
	public void eyeIconDisabledModeTest() {
		try {
			logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
			boolean passwordVisibility = logEle.getHiddenPassword().isDisplayed();
			Assert.assertTrue(passwordVisibility);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Password is visible on click of Eye icon
	@Test(priority = 5)
	public void eyeIconEnableModeTest() {
		try {
			logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
			logEle.getEyeIcon().click();
			boolean passwordVisibility = logEle.getVisiblePassword().isDisplayed();
			Assert.assertTrue(passwordVisibility);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	//Valid Email and Password Test
	@Test(priority=6)
	public void validLoginScenario() {
		try {
			logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "validEmail"));
			logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "validPassword"));
			logEle.getLoginButton().click();
			Boolean availablityVisibility = homEle.getAvailabilityMenu().isDisplayed();
			Assert.assertTrue(availablityVisibility);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
