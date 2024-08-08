package glue.gluewebapp.library;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericLibrary {

	public static WebDriver driver;
	public static String sTestDataFile = BaseClass.sDirPath + "\\GlueWebAppExcel.xlsx";

//explicit wait
	/**
	 * @param element
	 * @see explicit Wait 30 sec
	 */
	public static void elementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
//////////////////////////////////////////////////////////	
	
// get project path
	/**
	 * 
	 * @return String
	 */
	public static String getProjectPath() {
		String property = System.getProperty("user.dir");
		return property;
	}
//////////////////////////////////////////////////////////	
	
// implicit wait for 30sec
	/**
	 * @see implicit wait for 30
	 */
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}
/////////////////////////////////////////////////////////

// implicit wait for any timeframe
	public static void implicitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));
}
	
///////////////////////////////////////////////////////////
	
//These Methods are used for finding the WebElement using the locators.
//  find elementid
	/**
	 * 
	 * @param id
	 * @return WebElement
	 */
	public static WebElement findElementID(String id) {

		WebElement element = driver.findElement(By.id(id));
		return element;
	}

//  find elementname
	/**
	 * 
	 * @param name
	 * @return WebElement
	 */
	public static WebElement findElementName(String name) {
		WebElement element = driver.findElement(By.name(name));
		return element;
	}

	//  find elementclassname
	/**
	 * 
	 * @param data
	 * @return WebElement
	 */
	public static WebElement findElementClassName(String data) {
		WebElement element = driver.findElement(By.id(data));
		return element;
	}

	//  find elementxpath
	/**
	 * 
	 * @param xpath
	 * @return WebElement
	 */
	public static WebElement findElementXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
		return element;
	}

///////////////////////////////////////////////////////////////

//  sendkeys
	/**
	 * 
	 * @param element
	 * @param data
	 */
	public static void sendKeys(WebElement element, String data) {
		elementVisibility(element);
		element.sendKeys(data);
	}
	
///////////////////////////////////////////////////////////////////
/* These methods are used to Get attribute value and get Text values */
//  getattribute
	/**
	 * 
	 * @param element
	 * @return String
	 * @see get atrribute value
	 */
	public String getAttribute(WebElement element) {
		//elementVisibility(element);
		String string = element.getAttribute("value");
		return string;
	}

	//  gettext
	/**
	 * 
	 * @param element
	 * @return String
	 */
	public String getText(WebElement element) {
		//elementVisibility(element);
		String string = element.getText();
		return string;
	}
	
	//  get title
	/**
	 * @see get tittle
	 * @return
	 */
	public String getTitle() {

		String title = driver.getTitle();
		return title;
	}

	//  get the entered url

	public String getCurrentUrl() {

		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

/////////////////////////////////////////////////////////////////////
	
//  actions doubleclick
	/**
	 * 
	 * @param element
	 */
	public void doubleClick(WebElement element) {
		elementVisibility(element);
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	//  actions rightclick
	public void rightClick() {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}

	//  actions mouseover
	public void mouseOver(WebElement element) {
		elementVisibility(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	//  alert accept
	/**
	 * @see alert accept
	 */
	public void alertHandlingAccept() {
		driver.switchTo().alert().accept();
	}

	//  alert dismiss
	/**
	 * @see alert dismiss
	 */
	public void alertHandlingDismiss() {
		driver.switchTo().alert().dismiss();
	}

	//  select getoptions
	/**
	 * 
	 * @param element
	 * @return List of WebElement
	 */
	public List<WebElement> getOptions(WebElement element) {
		elementVisibility(element);
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		return options;
	}
	//  select using index
/**
 * 
 * @param element
 * @param index
 */
	public void selectIndex(WebElement element, int index) {
		elementVisibility(element);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	//  select using value
	/**
	 * 
	 * @param element
	 * @param value
	 */
	public void selectValue(WebElement element, String value) {
		elementVisibility(element);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	//  select using visible text
	/**
	 * 
	 * @param element
	 * @param text
	 * 
	 */
	public void selectVisibleText(WebElement element, String text) {
		elementVisibility(element);
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	//  deselct all

	public void deselectAll(WebElement element) {
		elementVisibility(element);
		Select select = new Select(element);
		select.deselectAll();
	}

	//  get first selected options
	public WebElement getFirstSelectedOptions(WebElement element) {
		elementVisibility(element);
		Select select = new Select(element);
		WebElement selectedOption = select.getFirstSelectedOption();
		return selectedOption;
	}

	//  get all selected options

	public List<WebElement> getAllSelectedOptions(WebElement element) {
		elementVisibility(element);
		Select select = new Select(element);
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		return selectedOptions;

	}

	//  navigate

	public void navigate(String url) {

		driver.navigate().to(url);
	}

	//  click
	public void click(WebElement element) {
		//elementVisibility(element);
		element.click();
	}

	//  close all windows
/**
 * @see close all window
 */
	public static void quit() {

		driver.quit();
	}

	//  close current window

	public void close() {

		driver.close();
	}

	//  sendkeysusing js

	public void javaScriptSendKeys(WebElement element, String value) {
		elementVisibility(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value','"+value+ "')", element);
	}

	//  click button by javascript

	public void javaScriptClick(WebElement element) {
		elementVisibility(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}

	//  get window handle

	public String getWindowHandle() {

		String windowHandle = driver.getWindowHandle();

		return windowHandle;
	}

	//  get window handles

	public Set<String> getWindowHandles() {

		Set<String> windowHandles = driver.getWindowHandles();

		return windowHandles;

	}

	//  switch to child window

	public void switchWindow(int index) {

		Set<String> windowHandles = driver.getWindowHandles();

		List<String> list = new ArrayList<String>();

		list.addAll(windowHandles);

		driver.switchTo().window(list.get(index));
	}

	//  switch to frame by index

	public void switchToFrame(int index) {

		driver.switchTo().frame(index);
	}

	//  switch to frame by id

	public void switchToFrame(String name) {

		driver.switchTo().frame(name);
	}

	//  get first selected option

	public WebElement getFirstSelectedOption(WebElement element) {
		elementVisibility(element);
		Select select = new Select(element);
		WebElement element2 = select.getFirstSelectedOption();
		return element2;
	}
	
	//  drop down is multiple or not

	public boolean isMultiple(WebElement element) {
		elementVisibility(element);
		Select select = new Select(element);
		boolean multiple = select.isMultiple();
		return multiple;
	}

	//  deselect by index

	public void deselectByIndex(WebElement element, int index) {
		elementVisibility(element);
		Select select = new Select(element);
		select.deselectByIndex(index);
	}

	//  deselect by text
	public void deselectByText(WebElement element, String text) {
		elementVisibility(element);
		Select select = new Select(element);
		select.deselectByVisibleText(text);

	}

	//  drag and drop

	public void dragAndDrop(WebElement source, WebElement target) {
		elementVisibility(source);
		elementVisibility(target);
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target);

	}

	//  clear textbox
/**
 * @see clear
 * @param element
 */
	public void clear(WebElement element) {
		elementVisibility(element);
		element.clear();

	}

	// 40 navigate to

	public void navigateTo(String url) {
		driver.navigate().to(url);

	}

	//  navigate back
	public void navigateBack() {

		driver.navigate().back();
	}

	//  navigate forward

	public void navigateForward() {

		driver.navigate().forward();
	}

	//  navigate refresh

	public void navigateRefresh() {

		driver.navigate().refresh();
	}

	//  element screenshot

	public static void screenshotElement(WebElement element, String pathname) throws IOException {
		elementVisibility(element);
		File s = element.getScreenshotAs(OutputType.FILE);
		File d = new File(pathname);
		FileUtils.copyFile(s, d);

	}

	//  switch to frame by name

	public void framByName(String nameOrId) {

		driver.switchTo().frame(nameOrId);
	}
	//  keysdown using action class

	public void keysDown(String key) {

		Actions actions = new Actions(driver);
		Actions keyDown = actions.keyDown(key);
		keyDown.perform();
	}

	//  keyupusing action class

	public void keyUp(String key) {

		Actions actions = new Actions(driver);
		actions.keyUp(key).perform();
	}

	//  scrolldown using js

	public void javaScriptScrollDown(WebElement element) {
		elementVisibility(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].ScrollIntoView(true)", element);
	}

	//  scrollup using js

	public void javaScriptScrollUp(WebElement element) {
		elementVisibility(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].ScrollIntoView(false)", element);
	}

	//  frame switch to immediate parent frame
/**
 * @see default frame
 */
	public void switchImmediateFrameParent() {
		driver.switchTo().parentFrame();

	}

	//  takescreenshot
	/**
	 * 
	 * @param pathname
	 * @throws IOException
	 */
	public static void takeScreenshot(String pathname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File s = screenshot.getScreenshotAs(OutputType.FILE);
		File d = new File(pathname);
		FileUtils.copyFile(s, d);

	}

	//  getdatafromexcel
	/**
	 * 
	 * @param pathname
	 * @param sheetname
	 * @param rowno
	 * @param cellno
	 * @return
	 * @throws IOException
	 */

	public static String getDataFromExcel(String pathname, String sheetname, int rowno, int cellno) throws IOException {

		String data = null;

		File file = new File(pathname);

		FileInputStream fileInputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(fileInputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		Row row = sheet.getRow(rowno);

		Cell cell = row.getCell(cellno);

		CellType cellType = cell.getCellType();

		if (cellType.toString().equals("STRING")) {

			data = cell.getStringCellValue();

		}

		if (cellType.toString().equals("NUMERIC")) {

			if (DateUtil.isCellDateFormatted(cell)) {

				Date dateCellValue = cell.getDateCellValue();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

				data = dateFormat.format(dateCellValue);
			}

			else {
				double numericCellValue = cell.getNumericCellValue();

				long l = (long) numericCellValue;
				data = String.valueOf(l);
			}

		}
		return data;

	}

	// 53 write data in excel
	/**
	 * 
	 * @param excelpathname
	 * @param sheetname
	 * @param rowno
	 * @param cellno
	 * @param valuetowrite
	 * @throws Exception
	 */
	public static void createExcel(String excelpathname, String sheetname, int rowno, int cellno, String valuetowrite)
			throws Exception {

		File file = new File(excelpathname);

		Workbook workbook = new XSSFWorkbook();
		Sheet createSheet = workbook.createSheet(sheetname);
		Row createRow = createSheet.createRow(rowno);
		Cell createCell = createRow.createCell(cellno);
		createCell.setCellValue(valuetowrite);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);

	}

	//  enter
	public static void robotEnter() throws AWTException {
		Robot  robot= new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	//  tab
	/**
	 * @see uses keyboard 
	 * @throws AWTException
	 */
	public static void robotTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}


 /**
    * This method helps to switch from one window to another
    * @param driver
    * @parampartialWinTitle
    */
   public void switchToWindow(WebDriver driver, String partialWinTitle)
   {
	   Set<String> window = driver.getWindowHandles();
	   Iterator<String> it = window.iterator();
	   while(it.hasNext())
	   {
		   String winId=it.next();
		   String title=driver.switchTo().window(winId).getTitle();
           if(title.contains(partialWinTitle))
           {
	   break;
           }
		
	   }
	
   }

	/**
	 * this method wait for 20 sec for page loading
	 * @param driver
	 */
	
   public void waitUntilPageLoad(WebDriver driver)
   {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
   }
	
	/**
	 * @see uses take screenShot
	 * @return
	 */
	public byte[] byteScreenshot() {
		  TakesScreenshot screenshot = (TakesScreenshot)driver;
		  byte[] bs = screenshot.getScreenshotAs(OutputType.BYTES); // returning 
		  return bs;
			}

	/**
	 * its used to get the current System date & time
	 * @return
	 */
	public String getSystemDate() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		return systemDateAndTime;
	}


	public static String capturescreenshot(String filename) throws Throwable

	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
		String screenshotPath = "./Screenshot/" + "/" + timeStamp + "_screenshot.png";

		File desfile = new File(screenshotPath);
		FileUtils.copyFile(src, desfile);
		return desfile.getAbsolutePath();
	}

	public static String base64() throws Throwable

	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64 = ts.getScreenshotAs(OutputType.BASE64);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
		String screenshotPath = "./Screenshot/" + "/" + timeStamp + "_screenshot.png";
		return base64;
	}

	public void closeserver() {
		try {
			String processName = "chrome.exe";
			Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);
			process.waitFor();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private void openExtentReport() throws IOException {
	        String separator = File.separator;
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	        String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
	        String path = System.getProperty("user.dir") + separator + "HTMLReports" + separator + "ExtentReport" + separator + "report-" + timeStamp + ".html";

	        File reportFile = new File(path);

	        if (Desktop.isDesktopSupported()) {
	            Desktop.getDesktop().browse(reportFile.toURI());
	        } else {
	            System.out.println("Opening the report is not supported on this platform.");
	        }
	    }


	public int randomNumber(int min, int max) {
	    Random ran = new Random();
	    int randomNum = ran.nextInt(max - min + 1) + min;
	    return randomNum;
	}
	

	public static String getVisibleAreaScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "//VisibleViewScreenshots//" + screenshotName + ".png";
		File destination = new File(dest);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}

	public static String getConfigValue(String sFile, String sKey) {
		Properties prop = new Properties();
		String sValue = null;

		try {
			InputStream input = new FileInputStream(sFile);
			try {
				prop.load(input);
				sValue = prop.getProperty(sKey);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sValue;
	}

	public static String[] toReadExcelData(String sSheet, String sTestCaseID)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		String[] sData = null;
		FileInputStream fis = new FileInputStream(sTestDataFile);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sht = wb.getSheet(sSheet);
		int rowNum = sht.getLastRowNum();

		for (int i = 1; i <= rowNum; i++) {

			if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {

				int cellNum = sht.getRow(i).getLastCellNum();
				sData = new String[cellNum];

				for (int j = 0; j < cellNum; j++) {

					sData[j] = sht.getRow(i).getCell(j).getStringCellValue();

				}
				break;
			}

		}
		return sData;

	}

	public static void sleep(long milliSecs) {
		try {
			Thread.sleep(milliSecs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Timeouts() {
		BaseClass.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

}
