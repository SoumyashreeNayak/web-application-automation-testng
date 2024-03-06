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

public class LoginTest {
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
	@Test(priority = 1)
	public void testLoginFailure(String appURL, String fileLoc) throws IOException {
		Reporter.log("inside testLoginFailure");
		webDriver.get(appURL);
		Reporter.log("Went to home Page");
		WebElement webEle1 = webDriver.findElement(By.linkText("Login"));
		if (webEle1 != null) {
			takeScreenshot(webDriver, countOfScreenshots, fileLoc);
			countOfScreenshots++;
		}
		webEle1.click();
		assertEquals("Login", webDriver.getTitle());
		Reporter.log("Went to Login Page");
		WebElement webEle2 = webDriver.findElement(By.id("user-email"));
		WebElement webEle3 = webDriver.findElement(By.id("user-pwd"));
		WebElement webEle4 = webDriver.findElement(By.id("submit-btn"));
		// SENDING INCORRECT CREDENTIALS
		webEle2.sendKeys("a1@test.com");
		webEle3.sendKeys("aabbcc");
		takeScreenshot(webDriver, countOfScreenshots, fileLoc);
		countOfScreenshots++;
		webEle4.click();
		Reporter.log("Sending incorrect credentials");
		assertEquals("Login", webDriver.getTitle());
		takeScreenshot(webDriver, countOfScreenshots, fileLoc );
		countOfScreenshots++;
		Reporter.log("Remaining back on login page");
	}
	@Parameters({ "appURL", "fileLoc" })
	@Test(priority = 1)
	public void testLoginSuccess(String appURL, String fileLoc) throws IOException {
		Reporter.log("inside testLoginSuccess");
		webDriver.get(appURL);
		Reporter.log("Went to home Page");
		WebElement webEle1 = webDriver.findElement(By.linkText("Login"));
		if (webEle1 != null) {
			takeScreenshot(webDriver, countOfScreenshots, fileLoc);
			countOfScreenshots++;
		}
		webEle1.click();
		assertEquals("Login", webDriver.getTitle());
		Reporter.log("Went to Login Page");
		WebElement webEle2 = webDriver.findElement(By.id("user-email"));
		WebElement webEle3 = webDriver.findElement(By.id("user-pwd"));
		WebElement webEle4 = webDriver.findElement(By.id("submit-btn"));
		// SENDING CORRECT CREDENTIALS
		webEle2.sendKeys("a1@test.com");
		webEle3.sendKeys("123456");
		takeScreenshot(webDriver, countOfScreenshots, fileLoc);
		countOfScreenshots++;
		webEle4.click();
		Reporter.log("Sending correct credentials");
		assertEquals("Dashboard", webDriver.getTitle());
		takeScreenshot(webDriver, countOfScreenshots, fileLoc );
		countOfScreenshots++;
		Reporter.log("Went to Dashboard");
		WebElement webEle5 = webDriver.findElement(By.linkText("Logout"));
		webEle5.click();
		Reporter.log("Logging out");
		assertEquals("Welcome", webDriver.getTitle());
		takeScreenshot(webDriver, countOfScreenshots, fileLoc );
		countOfScreenshots++;
		Reporter.log("Went back to Home");
	}
	
	
	@AfterClass
	public void tearDown() {
		webDriver.close();
	}

	public static void takeScreenshot(WebDriver wd, int count, String fileLoc) throws IOException {
		File file = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(fileLoc + "login_test_" + count + ".png"));
	}
}
