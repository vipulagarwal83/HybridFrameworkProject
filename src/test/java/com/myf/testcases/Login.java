package com.myf.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.myf.base.Base;
import com.myf.pages.LoginPage;
import com.myf.utils.Utilities;


public class Login extends Base {
	
	public Login()
	{
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = intializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
	}

	@Test(priority = 1,dataProvider="validCredentials")
	public void verifyLoginWithValidCredentials(String email,String password) {

		String actuallogoText = driver.findElement(By.tagName("img")).getAttribute("alt");

		System.out.println(actuallogoText);
		String expectedlogoText = dataProp.getProperty("actuallogo");

		Assert.assertTrue(actuallogoText.contains(expectedlogoText), "Expected : Logo Name is not displayed");
		
		// Calling Page Factory functions
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();

	//	driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(prop.getProperty("validEmail"));
	//	driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(prop.getProperty("validPassword"));

	//	driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));

	}
	
	@DataProvider(name = "validCredentials")
	public Object[][] supplyTestData()
	{
		//Object[][] data = {{"vishal.beniwal@mindyourfleet.com","Vipul@12"},
				        //   {"vishal.beniwal@mindyourfleet.com","Vipul@12"}};
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		
		// Calling Page Factory functions
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generatedEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();

	//	driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(Utilities.generatedEmailWithTimeStamp());
	//	driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(dataProp.getProperty("invalidPassword"));

	//	driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordWarningMsg();

	//	String actualWarningMessage = driver.findElement(By.xpath("//*[contains(text(), 'Either the username')]")).getText();
				

		System.out.println(actualWarningMessage);
		String expectedWarningMessage = dataProp.getProperty("expectedMessage");

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected : Warning message is not displayed");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		// Calling Page Factory functions
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generatedEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();

	//	driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(Utilities.generatedEmailWithTimeStamp());
	//	driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(prop.getProperty("validPassword"));

	//	driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();

		String actualWarningMessage = loginPage.retrieveEmailPasswordWarningMsg();
	//	String actualWarningMessage = driver.findElement(By.xpath("//*[contains(text(), 'Either the username')]")).getText();
				

		System.out.println(actualWarningMessage);
		String expectedWarningMessage = dataProp.getProperty("expectedMessage");

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected : Warning message is not displayed");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		// Calling Page Factory functions
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
				
	//	driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(prop.getProperty("validEmail"));
	//	driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(dataProp.getProperty("invalidPassword"));

	//	driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordWarningMsg();

	//	String actualWarningMessage = driver.findElement(By.xpath("//*[contains(text(), 'Either the username')]")).getText();
				

		System.out.println(actualWarningMessage);
		String expectedWarningMessage = dataProp.getProperty("expectedMessage");

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected : Warning message is not displayed");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnLoginButton();

		//driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys("");
		//driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys("");

		//driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();
		
		String actualWarningMessage = loginPage.retrievePasswordWarningMsg();

		//String actualWarningMessage = driver.findElement(By.xpath("//*[contains(text(), 'Please enter username')]")).getText();
				

		System.out.println(actualWarningMessage);
		String expectedWarningMessage = "Please enter username";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected : Warning message is not displayed");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));

	}


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
