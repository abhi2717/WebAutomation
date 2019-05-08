package baselab;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetUp {
	
	WebDriver driver;

	@Test
	public void browserHandling() throws InterruptedException
	{
		/*
		 * System.setProperty("webdriver.gecko.driver", reader.getDriverPath()); driver
		 * = new ChromeDriver(); driver.get(reader.getApplicationUrl());
		 * Thread.sleep(5000);
		 */
		
		 try {

	            System.setProperty("webdriver.chrome.driver","C:\\SeleniumDriver\\chromedriver.exe");
	            driver=new ChromeDriver(); 
	            driver.get("https://www.hdfcbank.com");
	            driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
	    		driver.manage().window().maximize();
	    		driver.manage().deleteAllCookies();
	            WebDriverWait wait = new WebDriverWait(driver, 5000);
	            Thread.sleep(3000);
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("popupCloseButton"))).click();
	            //driver.findElement(By.className("popupCloseButton")).click();
	            
			/*
			 * // scroll down till end of webpage ((JavascriptExecutor)driver).
			 * executeScript("window.scrollTo(0, document.body.scrollHeight)"); // scroll
			 * down by coordinates
			 * ((JavascriptExecutor)driver).executeScript("scroll(0,900)");
			 * System.out.println("Scrolling Done");
			 */
	            System.out.println(System.getProperty("user.dir"));

	            Actions action = new Actions(driver);
	            action.moveToElement(driver.findElement(By.linkText("Making payments"))).perform();
	            Thread.sleep(3000);
	            wait.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//*[@class='mainlink' and text()='Pay Credit Card Bills']"))).click();
	            //driver.findElement(By.xpath("//*[@class='mainlink' and text()='Pay Credit Card Bills']")).click();
	            action.moveToElement(driver.findElement(By.linkText("HDFC Bank Account"))).click().build().perform();
	            
	            String actualUrl= "https://www.hdfcbank.com/personal/making-payments/pay-credit-card-bills/hdfc-bank-account";
	            Assert.assertEquals(actualUrl, driver.getCurrentUrl());	            
	            driver.navigate().back();
	            System.out.println("Return back to Home");
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("popupCloseButton"))).click();
	            ((JavascriptExecutor)driver).executeScript("scroll(0,400)");
	            

	            WebElement element =driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[12]/a[1]"));
	            Coordinates points=((Locatable)element).getCoordinates();
	            points.inViewPort();
	            element.click();
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Buy Gift Voucher now!']"))).click();
	            
			/*
			 * Alert alert = driver.switchTo().alert(); System.out.println(alert.getText());
			 * alert.accept();
			 */
	            //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Proceed']"))).click();
	            driver.switchTo().frame("ceeboxiframe");
	            System.out.println("Find frame");	                    	            
	            driver.findElement(By.linkText("Proceed")).click();
	            System.out.println("Gift Voucher Page");	
	            
	            driver.switchTo().window(actualUrl);
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Cart']")));
	            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Get In Touch"))).click();
	            
	            try {                       
		            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		            String fileName = new SimpleDateFormat("dd_MMM_yyyy").format(new Date());
		            FileUtils.copyFile(src, new File(System.getProperty("user.dir")+File.separator+"/FrameworkReports/"+fileName +".png"));
		            }
		            catch(WebDriverException ex)
		            {
		            	System.out.println(ex.getMessage());
		            }
			/*
			 * File screenshotFile = ((TakesScreenshot)
			 * driver).getScreenshotAs(OutputType.FILE); String screenshotBase64 =
			 * ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			 */            
		 } 
		 catch (Exception e) 
		 	{
	            e.printStackTrace();
		 	}
	        
	}



}
