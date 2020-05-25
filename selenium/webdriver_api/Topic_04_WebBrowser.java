package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_WebBrowser {
	
	WebDriver driver;
 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Step 1: Open URL");
		driver.get("http://live.demoguru99.com/");
		
		System.out.println("Step 2: CLick My Account at Footer");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}
	
	
	
	@Test
	public void TC_01_VerifyUrl() {
		System.out.println("Step 3: Verify URL of My account page");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 4: Click button Create an account");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 5: Verify URL of Create an Account page");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");		
		
		System.out.println("======================");
	}
	 
	@Test
	public void TC_02_VerifyTitle() {
		System.out.println("Step 3: Verify Title of My account page");
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		System.out.println("Step 4: Click button Create an account");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 5: Verify Title of Create an Account page");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		System.out.println("======================");
	}
	
	@Test
	public void TC_03_NavigationFunction() {
		System.out.println("Step 3: Click button Create an account");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 4: Verify URL of Create account page");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		
		System.out.println("Step 5: Back to My account page");
		driver.navigate().back();
		
		System.out.println("Step 6: Verify URL of My account page");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 7: Forward to Create account page");
		driver.navigate().forward();
		
		System.out.println("Step 8: Verify Title of Create account page");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");		
		
		System.out.println("======================");
	}
	
	@Test
	public void TC_04_GetPageSource() {
		System.out.println("Step 3: Verify Login page contains 'Login or Create an Account'");
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		System.out.println("Step 4: Click button Create an account");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 5: Verify Create an account page contains 'Create an Account'");
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 }
