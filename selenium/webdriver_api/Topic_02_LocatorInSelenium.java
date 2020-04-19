package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_LocatorInSelenium {
	
	WebDriver driver;
 
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
 
	@Test
	public void TC_01_Locator() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		// ID 
		driver.findElement(By.id("email")).sendKeys("test@locator.com"); // enter email
		driver.findElement(By.id("pass")).sendKeys("123@#"); // enter password
		//driver.findElement(By.id("email")).clear(); // clear email
		
		// NAME
		driver.findElement(By.name("send")). click(); // click button Login
		
		// CLASS 
		driver.findElement(By.className("validate-email")).clear(); // clear email
		driver.findElement(By.className("validate-email")).sendKeys("testClasName@locator.com"); // enter email
		
		// Tagname (count numbers of tagnames)
		List <WebElement> links = driver.findElements(By.tagName("a")); // Use List
		int linkNumber = links.size(); // Get size of link numbers
			System.out.println("Numbers of link = " + linkNumber);
		for(WebElement link:links) { // Print links detail
			System.out.println("Link =  " + link.getAttribute("href"));
		}	
		
		//LINK TEXT (notice TEXT in UI)
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
		
		// Partial link text
		driver.findElement(By.partialLinkText("RETURNS")).click();
	}
	 
	@Test
	public void TC_02_CssSelector() { // Syntax: tagname[attribute='value']
		driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("00001234"); // Enter mail in order_id field by id
		driver.findElement(By.cssSelector(".input-text.required-entry")).sendKeys("Ngoc"); // Enter name in billing last name field by class
		driver.findElement(By.cssSelector("input[name='oar_email']")).sendKeys("test@css.com"); // Enter email in email field by name
		
		// Print numbers of a Tag use CSS
		System.out.println("A tag by css = " + driver.findElements(By.cssSelector("a")).size());
		
		// Click Advanced search link by tag a with link value
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();
	}
	
	@Test
	public void TC_03_Xpath() {
		// Syntax: //tagname[@attribute='value']
		driver.findElement(By.xpath("//input[@id='oar_order_id']")).sendKeys("00005678"); // Enter mail in order_id field by id
		driver.findElement(By.xpath("//input[@class = 'input-text required-entry']")).sendKeys("Ngocc"); // Enter name in billing last name field by class
		driver.findElement(By.xpath("//input[@name='oar_email']")).sendKeys("test@css.com");// Enter email in email field by name

		// Print numbers of a Tag use xpath
		System.out.println("A tag by xpath = " + driver.findElements(By.xpath("//a")).size());
		
		// Click Advanced search link by tag a with link value
		driver.findElement(By.xpath("//a[text()='Site Map']")).click();
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
 }
