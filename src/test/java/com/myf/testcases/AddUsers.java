package com.myf.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.myf.base.Base;
import com.myf.pages.AddUsersPage;
import com.myf.utils.Utilities;

public class AddUsers extends Base{
	
	public AddUsers()
	{
		super();
	}
	
	public WebDriver driver;
//	WebElement userOption;
//	WebElement addUser;
//	WebElement genderOption;
//	WebElement userSelect;
//	WebElement saveButton;
	JavascriptExecutor executor;
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		driver = intializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		loginWithValidCredentials();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		
		AddUsersPage addUserPage = new AddUsersPage(driver);
		addUserPage.onClickSetupOption();
		addUserPage.onClickUsersOption();
		
		//driver.findElement(By.xpath("//*[text()='Setup']")).click();
		//driver.findElement(By.xpath("//*[text()='Users']")).click();
		
		executor = (JavascriptExecutor)driver;
		executor.executeScript("document.body.style.zoom = '0.75'");
		
			
		
	}
	
	@Test
	public void verifyAddUserWithMandatoryFields()
	{
		AddUsersPage addUserPage = new AddUsersPage(driver);
		addUserPage.onclickAddUser();
		
		//addUser = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div/div/a"));
	//	executor = (JavascriptExecutor)driver;
	//	executor.executeScript("arguments[0].click();", addUser);
		
		addUserPage.enterFirstName(dataProp.getProperty("firstName"));
		addUserPage.enterEmailID(Utilities.generatedEmailWithTimeStamp());
		addUserPage.enterPrimaryPhone(dataProp.getProperty("mobileNo"));
		
	//	driver.findElement(By.xpath("//input[@name='name']")).sendKeys(dataProp.getProperty("firstName"));
	//	driver.findElement(By.xpath("//input[@name='primary_email']")).sendKeys(Utilities.generatedEmailWithTimeStamp());
	//	driver.findElement(By.xpath("//input[@name='primary_phone']")).sendKeys(dataProp.getProperty("mobileNo"));
		
		addUserPage.onSelectGenderField();
		
		//genderOption = driver.findElement(By.xpath("(//span[@class='filter-option pull-left'])[3]"));
		//executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].click();", genderOption);
		
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		addUserPage.onGenderSelect();
		//WebElement gender = driver.findElement(By.xpath("//span[normalize-space()='Female']"));
		//executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].click()", gender);
		
		
		addUserPage.onClickUserOption();
		
		//userOption = driver.findElement(By.xpath("(//span[@class='filter-option pull-left'])[4]"));
	//	executor = (JavascriptExecutor)driver;
	//	executor.executeScript("arguments[0].click();", userOption);
		
		
		addUserPage.onClickUserSelect();
	//	userSelect = driver.findElement(By.xpath("//span[normalize-space()='Booking Operator']"));
	//	executor = (JavascriptExecutor)driver;
	//	executor.executeScript("arguments[0].click()", userSelect);
		
		
		addUserPage.onClickSaveButton();
		//saveButton = driver.findElement(By.xpath("//*[@id=\"add_staff_form\"]//*[text()='Save']"));
	//	executor = (JavascriptExecutor)driver;
	//	executor.executeScript("arguments[0].click()", saveButton);
		
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	


}
