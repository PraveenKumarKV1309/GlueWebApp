package glue.gluewebapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import glue.gluewebapp.library.BaseClass;

public class HomePage extends BaseClass {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ----------------------------------------------------
	// Availability Menu
	@FindBy(xpath = "//p[text()=\"Availability\"]")
	private WebElement availabilityMenu;

	public WebElement getAvailabilityMenu() {
		return availabilityMenu;
	}

	// -----------------------------------------------------
	// Email field
	@FindBy(xpath = "//input[@id=\"email\"]")
	private WebElement emailField;

	public WebElement getEmailField() {
		return emailField;
	}

	public void setEmailField(String emailValue) {
		emailField.sendKeys(emailValue);
	}

	// -----------------------------------------------------
	// Password field
	@FindBy(xpath = "//input[@id=\"auth-login-password\"]")
	private WebElement passwordField;

	public WebElement getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(String passwordValue) {
		passwordField.sendKeys(passwordValue);
	}

	// -----------------------------------------------------
	// Eye icon
	@FindBy(xpath = "//button[@aria-label=\"toggle password visibility\"]")
	private WebElement eyeIcon;

	public WebElement getEyeIcon() {
		return eyeIcon;
	}

	// -----------------------------------------------------
	// Password visible text
	@FindBy(xpath = "//input[@id=\"auth-login-password\" and @type=\"text\"]")
	private WebElement visiblePassword;

	public WebElement getVisiblePassword() {
		return visiblePassword;
	}

	// -----------------------------------------------------
	// Password hidden text
	@FindBy(xpath = "//input[@id=\"auth-login-password\" and @type=\"password\"]")
	private WebElement hiddenPassword;

	public WebElement getHiddenPassword() {
		return hiddenPassword;
	}

	// -----------------------------------------------------
	// Login Button
	@FindBy(xpath = "//button[@type=\"submit\" and text()=\"Login\"]")
	private WebElement loginButton;

	public WebElement getLoginButton() {
		return loginButton;
	}

}
