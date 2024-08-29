package glue.gluewebapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import glue.gluewebapp.library.BaseClass;

public class ViewAllPeersPage extends BaseClass {
	WebDriver driver;

	public ViewAllPeersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ----------------------------------------------------
	// User Profile Element
	@FindBy(xpath = "//a[contains(@href,'professional-details')]")
	private WebElement userProfile;

	public WebElement getUserProfileButton() {
		return userProfile;
	}

	// ----------------------------------------------------
	// Glue Logo
	@FindBy(xpath = "//div[contains(@class,'nav-header')]//img[contains(@src,'glue')]")
	private WebElement glueLogo;

	public WebElement getGlueLogo() {
		return glueLogo;
	}

	

}
