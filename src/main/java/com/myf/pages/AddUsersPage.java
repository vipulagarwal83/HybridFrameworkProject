package com.myf.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddUsersPage {
	
	WebDriver driver ;
	JavascriptExecutor executor;
	
   //Objects
	
	@FindBy(xpath="//*[text()='Setup']")
	private WebElement clickSetupOption;
	
	@FindBy(xpath="//*[text()='Users']")
	private WebElement clickUsersOption;
	
	@FindBy(xpath="//*[@id=\"main\"]/div[1]/div/div/div/a")
	private WebElement clickAddUserButton;
	
	@FindBy(xpath="//input[@name='name']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@name='primary_email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@name='primary_phone']")
	private WebElement primaryPhoneField;
	
	@FindBy(xpath="(//span[@class='filter-option pull-left'])[3]")
	private WebElement genderFieldOption;
	
	@FindBy(xpath="//span[normalize-space()='Female']")
	private WebElement genderSelect;
	
	@FindBy(xpath="(//span[@class='filter-option pull-left'])[4]")
	private WebElement userOptionField;
	
	@FindBy(xpath="//span[normalize-space()='Booking Operator']")
	private WebElement userSelectField;
	
	@FindBy(xpath="//*[@id=\"add_staff_form\"]//*[text()='Save']")
	private WebElement clickSaveButton;
	
	public AddUsersPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	public void onClickSetupOption()
	{
		clickSetupOption.click();
	}
	
	
	public void onClickUsersOption() {
		
		clickUsersOption.click();
	}
	
	public void onclickAddUser()
	{
       
		WebElement addUser = clickAddUserButton;
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", addUser);
		
	}
	
	public void enterFirstName(String firstName)
	{
		firstNameField.sendKeys(firstName);
		
	}
	
	public void enterEmailID(String primaryEmail)
	{
		emailField.sendKeys(primaryEmail);
	}
	
	public void enterPrimaryPhone(String primaryPhone)
	{
		primaryPhoneField.sendKeys(primaryPhone);
	}
	
	public void onSelectGenderField()
	{
        WebElement genderOption = genderFieldOption;
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", genderOption);
		
		
	}
	
	public void onGenderSelect()
	{
		WebElement gender = genderSelect;
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", gender);
		
	}
	
	public void onClickUserOption()
	{
        WebElement userOption = userOptionField;
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", userOption);
		
	}
	
	public void onClickUserSelect()
	{
		WebElement userSelect = userSelectField;
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", userSelect);
	
	}
	
	public void onClickSaveButton()
	{
		WebElement saveButton = clickSaveButton;
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", saveButton);
		
	}
}
