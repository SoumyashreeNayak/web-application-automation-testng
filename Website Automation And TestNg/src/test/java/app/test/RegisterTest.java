package app.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegisterTest {
	private int countOfScreenshots = 0;
	private WebDriver webDriver;

	@Parameters({ "chromeDriverPath" })
	@BeforeClass
	public void setUp(String chromeDriverPath) {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

	}

	@Parameters({ "appURL", "fileLoc" })
	@Test(priority =1)
	public void testRegisterFailure(String appURL, String fileLoc) throws IOException, InterruptedException {
		Reporter.log("inside testRegisterFailure");
		webDriver.get(appURL);
		Reporter.log("Went to home Page");
		WebElement webEle1 = webDriver.findElement(By.linkText("Register"));
		if (webEle1 != null) {
			takeScreenshot(webDriver, countOfScreenshots, fileLoc);
			countOfScreenshots++;
		}
		webEle1.click();
		assertEquals("Register", webDriver.getTitle());
		Reporter.log("Went to Register Page");
		WebElement webEle2 = webDriver.findElement(By.id("user-first-name"));
		WebElement webEle3 = webDriver.findElement(By.id("user-last-name"));
		WebElement webEle4 = webDriver.findElement(By.id("user-email"));
		WebElement webEle5 = webDriver.findElement(By.id("user-pwd"));
		WebElement webEle6 = webDriver.findElement(By.id("submit-btn"));
		Thread.sleep(1000);
		webEle2.sendKeys("John");
		webEle3.sendKeys("Watson");
		webEle4.sendKeys("a1@test.com");
		webEle5.sendKeys("123456");
		takeScreenshot(webDriver, countOfScreenshots, fileLoc);
		countOfScreenshots++;
		webEle6.click();
		Reporter.log("Providing existing email");
		takeScreenshot(webDriver, countOfScreenshots, fileLoc);
		countOfScreenshots++;
		Reporter.log("Registration not done");
		assertEquals("Register", webDriver.getTitle());
		Reporter.log("Remainining on Register Page");
	}

	@Parameters({ "appURL", "fileLoc" })
	@Test(priority = 2)
	public void testRegisterSuccess(String appURL, String fileLoc) throws IOException, InterruptedException {
		Reporter.log("inside testRegisterSuccess");
		webDriver.get(appURL);
		Reporter.log("Went to home Page");
		WebElement webEle1 = webDriver.findElement(By.linkText("Register"));
		if (webEle1 != null) {
			takeScreenshot(webDriver, countOfScreenshots, fileLoc);
			countOfScreenshots++;
		}
		webEle1.click();
		assertEquals("Register", webDriver.getTitle());
		WebElement webEle2 = webDriver.findElement(By.id("user-first-name"));
		WebElement webEle3 = webDriver.findElement(By.id("user-last-name"));
		WebElement webEle4 = webDriver.findElement(By.id("user-email"));
		WebElement webEle5 = webDriver.findElement(By.id("user-pwd"));
		WebElement webEle6 = webDriver.findElement(By.id("submit-btn"));
		Thread.sleep(1000);
		webEle2.sendKeys("John");
		webEle3.sendKeys("Watson");
		webEle4.sendKeys("a4@test.com");
		webEle5.sendKeys("123456");
		takeScreenshot(webDriver, countOfScreenshots, fileLoc);
		countOfScreenshots++;
		webEle6.click();
		Reporter.log("Providing new email");
		takeScreenshot(webDriver, countOfScreenshots, fileLoc);
		countOfScreenshots++;
		Reporter.log("Went to home page");
		assertEquals("Welcome", webDriver.getTitle());
		Reporter.log("New User Registration - DONE");
	}
	@AfterClass
	public void tearDown() {
		webDriver.close();
	}
	public static void takeScreenshot(WebDriver wd, int count, String fileLoc) throws IOException {
		File file = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(fileLoc + "reg_test_" + count + ".png"));
	}
	
	
}
