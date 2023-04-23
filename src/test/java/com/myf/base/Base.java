package com.myf.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public Base()
	{
	   prop = new Properties();
	   File propFile = new File(System.getProperty("user.dir")+"/src/main/java/com/myf/config/config.properties");
	   
	   dataProp = new Properties();
	   File dataPropFile = new File(System.getProperty("user.dir")+"/src/main/java/com/myf/testdata/testdata.properties");
	   try {
		FileInputStream dataFis = new FileInputStream(dataPropFile);
		dataProp.load(dataFis);
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
	   
	   try {
	   FileInputStream fis = new FileInputStream(propFile);
	   prop.load(fis);
	   }catch (Exception e) {
		e.printStackTrace();
	}
	   
	   
	}
	public WebDriver intializeBrowserAndOpenApplicationUrl(String browserName) {
		
		
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));
		driver.get(prop.getProperty("url"));
		
		return driver;

	}
	
	public void loginWithValidCredentials()
	{
		driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys("vishal.beniwal@mindyourfleet.com");
		driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys("Vipul@12");

		driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		

	}

}
