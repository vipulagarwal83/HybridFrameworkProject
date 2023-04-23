package com.myf.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.myf.utils.ExtentReporter;
import com.myf.utils.Utilities;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;

    public void onStart(ITestContext context) {
    	
    	extentReport = ExtentReporter.generateExtentReport();
    	
    	//System.out.println("Execution of Project Tests Started");
	}
	public void onTestStart(ITestResult result) {
		//String testName = result.getName();
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing");
		System.out.println(result.getName()+" started executing");
	}
	
	public void onTestSuccess(ITestResult result) {
		//String testName = result.getName();
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
		System.out.println(result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {
		//String testName = result.getName();
		//System.out.println(testName+" got failed");
		//System.out.println(result.getThrowable());
		//System.out.println("ScreenShots Taken");
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
		/*
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destinationScreenshotPath = System.getProperty("user.dir")+"/Screenshot"+result.getName()+".png";
		
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/
		
		String destinationScreenshotPath =Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");
	}
	
	public void onTestSkipped(ITestResult result) {
		//String testName = result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,result.getName()+" got skipped");
		//System.out.println(testName+" got skipped");
		//System.out.println(result.getThrowable());
	}
	
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		//System.out.println("Finished Executing project test");
		
		String pathOfExtentReport = System.getProperty("user.dir")+"/test-output/ExtentReports/extentreport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
