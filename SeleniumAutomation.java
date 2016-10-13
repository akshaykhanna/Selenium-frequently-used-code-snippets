// ********* imports ****************

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


// obj creation for chrome
System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
WebDriver driver = new ChromeDriver();


//open url
driver.get("https://url.com");

//handle dropdown's
Select dropdown1 = new Select(driver.findElement(By.id("form_flexcabNumberList")));
dropdown1.selectByValue("Other...");

//login to website
private static void websiteLogin(WebDriver driver)throws InterruptedException 
	{
		// TODO Auto-generated method stub
		  driver.get("https://website/login.htm");
		  Thread.sleep(intraDelay);
		  driver.findElement(By.name("name_of_username_text_box")).sendKeys(user);
		  Thread.sleep(intraDelay);
		  driver.findElement(By.name("name_of_pass_text_box")).sendKeys(pass);
		  Thread.sleep(intraDelay);
		  driver.findElement(By.name("name_of_submit_btn")).click();
		
	}

//open multiple page in multiple instance of chrome & webdriver obj
private static void openOrdersInBrowser() throws InterruptedException 
	{
		// TODO Auto-generated method stub
		List<String> orders = Arrays.asList
				(
						"ZX7MGX3Y"
						);
		String url;
		
		for(String orderNo:orders)
		{
			url="https://website.com?orderRefNumber="+orderNo;
			WebDriver driver = new ChromeDriver();
			digibizLogin(driver);
			driver.get(url);
		}
	}

//open mutiple pages in same instance of browser i.e. in its multiple tabs
private static void openPagesInBrowserInMultipleTabs() throws InterruptedException 
	{
		// TODO Auto-generated method stub
		List<String> orders = Arrays.asList
				(
						"WLJGDABD", "JRKG2AEH", "SHY4HR97", "NDB46W82", "BTFPNFMQ", "L7XH8HED"
						);
		String url;
		WebDriver driver = new ChromeDriver();
		digibizLogin(driver);
		int ct=0;
		for(String orderNo:orders)
		{
			
			url="https://web.htm?orderRefNumber="+orderNo;
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");

			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			 
			driver.switchTo().window(tabs.get(ct++));
			
			driver.get(url);
		}
	}
	
	
//take multiple screenshots
private static void openMultiplePageToTakeScreenShot() throws InterruptedException 
	{
		// TODO Auto-generated method stub
		List<String> orders = Arrays.asList
				(
						"L7XH8HED", "PZSN3RB5", "FWG78XNR", "BHEE7CPZ", "ED6DEXEH", "E3DP7C79", "SFL89DX8"
						);
		String url;
		WebDriver driver = new ChromeDriver();
		digibizLogin(driver);
		for(String orderNo:orders)
		{
			url="https://order-summary.htm?orderRefNumber="+orderNo;
			driver.get(url);
			
			try {
				
				//*** upper screenshot of page ***
				//creating output image file of screenshot
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//creating dynamic name for it
				FileUtils.copyFile(scrFile, new File(screesnhotLocation+orderNo+"_A"+".png")); 
				Thread.sleep(500);
				
				
				//*** lower screenshot of page ***
			    int size=driver.findElements(By.name("updateStatusForm_updateStatusButtonn")).size();
				if(size>0)
				{
					//if button is there on the bottom of page then click it to move bottom of page
					driver.findElement(By.name("updateStatusForm_updateStatusButton")).click();
				}
				else
				{
					//or use js to move to bottom of page
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("window.scrollBy(0,250)", "");
				}
				// creating & saving screenshot of lower part of page
				scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(screesnhotLocation+orderNo+"_B"+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(1000);
		}
	}
	
	
