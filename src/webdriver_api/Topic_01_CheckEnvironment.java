package webdriver_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironment {
	// Khai bao bien driver dai dien selenium webdriver
	WebDriver driver;
 
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		// Khoi tao trinh duyet firefox
		driver = new FirefoxDriver();
		
		// Chờ cho Element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		// Mở 1 trang web/AUT: Application under testing
		driver.get("http://demo.guru99.com/v4/");
	}
 
	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Lấy ra Url của page hiện tại và gán nó vào cái biến loginPageUrl
		String loginPageUrl = driver.getCurrentUrl();
		
		// In ra console cho ng dùng (coder) thấy được kết quả chạy test
		System.out.println(loginPageUrl);
		
		// Các hàm để verify dữ liệu cảu TestNG (true/false/eaquals)
		Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
	}
	 
	@Test
	public void TC_02_ValidatePageTitle() {
		// Login Page title
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}
	 
	@Test
	public void TC_03_LoginFormDisplayed() {
		// Login form displayed
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}
	 
	// Post-condition
	@AfterClass
		public void afterClass() {
		driver.quit();
	}
 }
