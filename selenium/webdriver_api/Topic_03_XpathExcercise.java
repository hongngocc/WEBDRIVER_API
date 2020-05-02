package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_XpathExcercise {

	WebDriver driver;
	String firstName = "Ngoc";
	String lastName = "NH";
	String validEmail = firstName + lastName + randomNum() + "@gmail.com";
	String validPassword = "123123";

	@BeforeClass(description = "run at the first time for all Test below")
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod(description = "run before Test for each test below")
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	@Test
	public void TC_01_LoginWithEmailPasswordEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String emailErrMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrMsg, "This is a required field.");

		String passErrMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passErrMsg, "This is a required field.");

	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@123.123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String emailErrMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrMsg, "Please enter a valid email address. For example johndoe@domain.com.");

		String passErrMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passErrMsg, "This is a required field.");
	}

	@Test
	public void TC_03_LoginWithPassLessThan6Characters() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ngocnh@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("abc");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String passErrMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passErrMsg, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ngocnh@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("abcde123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String ErrMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(ErrMsg, "Invalid login or password.");
	}

	@Test
	public void TC_05_CreateANewAccount() {
		driver.findElement(By.xpath("//a[@class='button']")).click();
		
		System.out.println("Random Email = " + validEmail);
		
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + validEmail + "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}
	
	
	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + validEmail + "')]")).isDisplayed());
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNum() {
		Random r = new Random();
		int n = r.nextInt(20);		
	    return n;
	}
}

