package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_00_Template {
	
	WebDriver driver;
 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_TextBox() {
		driver.get("");
	}
	 
	@Test
	public void TC_02_TextArea() {
		driver.get("");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 }
