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

public class HomeTest extends BaseClass {
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
		actions.click(logEle.getEmailField()).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.keyDown(Keys.DELETE).build().perform();
		actions.click(logEle.getPasswordField()).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.keyDown(Keys.DELETE).build().perform();
	}

	// Login with Empty email
	@Test(priority = 0)
	public void emptyEmailLoginTest() {
		try {
			System.out.println("1");
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
			System.out.println("2");
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
		System.out.println("3");
		logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "inValidEmail"));
		logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "validPassword"));
		logEle.getLoginButton().click();
		WebElement loginErrorMessage = driver.findElement(By.xpath("//p[text()=\"Invalid email or password\"]"));
		Assert.assertTrue(loginErrorMessage.isDisplayed());
		Log.info("Successfully logged into the application");
	}

	// Login with Invalid Password
	@Test(priority = 3)
	public void invalidPasswordLoginTest() {
		System.out.println("4");
		logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "validEmail"));
		logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
		logEle.getLoginButton().click();
		WebElement loginErrorMessage = driver.findElement(By.xpath("//p[text()=\"Invalid email or password\"]"));
		Assert.assertTrue(loginErrorMessage.isDisplayed());
		Log.info("Successfully logged into the application");
	}

	// Password is visible on click of Eye icon
	@Test(priority = 4)
	public void eyeIconDisabledModeTest() {
		System.out.println("5");
		logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
		boolean passwordVisibility = logEle.getHiddenPassword().isDisplayed();
		Assert.assertTrue(passwordVisibility);
		Log.info("Successfully logged into the application");
	}

	// Password is visible on click of Eye icon
	@Test(priority = 5)
	public void eyeIconEnableModeTest() {
		System.out.println("6");
		logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "inValidPassword"));
		logEle.getEyeIcon().click();
		boolean passwordVisibility = logEle.getVisiblePassword().isDisplayed();
		Assert.assertTrue(passwordVisibility);
		Log.info("Successfully logged into the application");
	}

	//Valid Email and Password Test
	@Test(priority=6)
	public void validLoginScenario() {
		System.out.println("7");
		logEle.setEmailField(GenericLibrary.getConfigValue(sConfigPath, "validEmail"));
		logEle.setPasswordField(GenericLibrary.getConfigValue(sConfigPath, "validPassword"));
		logEle.getLoginButton().click();
		Boolean availablityVisibility = homEle.getAvailabilityMenu().isDisplayed();
		Assert.assertTrue(availablityVisibility);
	}
}
