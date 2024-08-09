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

	// ----------------------------------------------------
	// Light/Dark Mode button
	@FindBy(xpath = "//div[contains(@class,'nav-header')]//button[@type='button']")
	private WebElement lightOrDarkModeButton;

	public WebElement getlightOrDarkModeButton() {
		return lightOrDarkModeButton;
	}

	// ----------------------------------------------------
	// Availability Menu
	@FindBy(xpath = "//p[text()=\"Availability\"]")
	private WebElement availabilityMenu;

	public WebElement getAvailabilityMenu() {
		return availabilityMenu;
	}

	// ----------------------------------------------------
	// Utilization Menu
	@FindBy(xpath = "//a[@href=\"/utilization/\"]")
	private WebElement utilizationMenu;

	public WebElement getUtilizationMenu() {
		return utilizationMenu;
	}

	// ----------------------------------------------------

	// My Projects Dropdown
	@FindBy(xpath = "//p[text()=\"My Projects\"]")
	private WebElement myProjectsMenu;

	public WebElement getMyProjectsMenu() {
		return myProjectsMenu;
	}

	// -----------------------------------------------------
	// View All Projects Menu
	@FindBy(xpath = "//a[@href=\"/projects/\"]")
	private WebElement viewAllProjectsMenu;

	public WebElement getViewAllProjectsMenu() {
		return viewAllProjectsMenu;
	}

	// -----------------------------------------------------
	// View All Peers Menu
	@FindBy(xpath = "//a[@href=\"/peers/\"]")
	private WebElement viewAllPeersMenu;

	public WebElement getViewAllPeersMenu() {
		return viewAllPeersMenu;
	}

	// -----------------------------------------------------
	// Work Status Report Menu
	@FindBy(xpath = "//a[@href='workStatus']")
	private WebElement workStatusReportMenu;

	public WebElement getWorkStatusReportMenu() {
		return workStatusReportMenu;
	}

	// -----------------------------------------------------
	// Announcement Menu
	@FindBy(xpath = "//a[@href=\"/announcement/\"]")
	private WebElement announcementMenu;

	public WebElement getAnnouncementMenu() {
		return announcementMenu;
	}

	// -----------------------------------------------------
	// Logout Menu
	@FindBy(xpath = "//p[text()='Logout']")
	private WebElement logoutMenu;

	public WebElement getLogoutMenu() {
		return logoutMenu;
	}

}
