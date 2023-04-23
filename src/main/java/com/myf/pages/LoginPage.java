package com.myf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	//objects
	@FindBy(xpath="//*[@id=\"loginEmail\"]")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//*[@id=\"loginPassword\"]")
	private WebElement passwordField;
	
	@FindBy(xpath="//*[@id=\"btnLogin\"]")
	private WebElement loginButton;
	
	@FindBy(xpath="//*[contains(text(), 'Either the username')]")
	private WebElement eitherTheUsernameWarning;
	
	@FindBy(xpath="//*[contains(text(), 'Please enter username')]")
	private WebElement pleaseEnterUsername;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
    public void enterEmailAddress(String emailText)
    {
    	emailAddressField.sendKeys(emailText);
    }
    
    public void enterPassword(String passwordText)
    {
    	passwordField.sendKeys(passwordText);
    }
    
    public void clickOnLoginButton()
    {
    	loginButton.click();
    }
    
    public String retrieveEmailPasswordWarningMsg()
    {
    	String emailwarningText = eitherTheUsernameWarning.getText();
    	return emailwarningText;
    }
    
    public String retrievePasswordWarningMsg()
    {
    	String passwordWarningText = pleaseEnterUsername.getText();
    	return passwordWarningText;
    }
}
