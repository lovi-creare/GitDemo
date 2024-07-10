

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

import Playwrightbasic.AbCommonFiles;



public class AbSeleniumFunctions {
	
	
	
	//Hitesh's function Start

public static void LoginUpoint(WebDriver driver, String browserName, String url, 
			String userid, String pwd, String udpSchema, String transid1, String transid2, 
			String transid3, String transid4, String RACFflag, String testSheetName, int testStepRowIndex){
		

		if(url.isEmpty() || url.toLowerCase().equals("na")){

		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} else{		
			LoginURL(browserName, url, driver, testSheetName, testStepRowIndex);
			}
		
		if (driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).isDisplayed()) {
			
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).click();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).clear();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).sendKeys(userid);
			

			if(RACFflag.toLowerCase().equals("no") || RACFflag.toLowerCase().equals("n")) {
				WebElement racf_enableNPVCheckbox = driver
						.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_enableNPVCheckbox"));
				if (racf_enableNPVCheckbox.isSelected()) {
					racf_enableNPVCheckbox.click();
				}
				
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
						.sendKeys(pwd);
				
			} else if(RACFflag.toLowerCase().equals("yes") || RACFflag.toLowerCase().equals("y")) {
				WebElement racf_enableNPVCheckbox = driver
						.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_enableNPVCheckbox"));
				if (racf_enableNPVCheckbox.isSelected()==false) {
					racf_enableNPVCheckbox.click();
				}
				
/*				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminId")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminId")).sendKeys(RACFid);
				
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminPass")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminPass")).sendKeys(getDecryptedVal(RACFpassword));
				*/
				
			} else {
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
						.sendKeys(pwd);
				
			}
			
			
			if (transid1 != null && transid1.trim().length() > 0) {
				driver.findElement(By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
						.clear();
				driver.findElement(By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
						.sendKeys(transid1);
			}
			if (transid2 != null && transid2.trim().length() > 0) {
				if (AbCommonFiles.isElementPresent(driver, By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"),
						10)) {
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
							.clear();
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
							.sendKeys(transid2);
				}
			}
			if (transid3 != null && transid3.trim().length() > 0) {
				if (AbCommonFiles.isElementPresent(driver, By.id(
						"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[2].cfgValue"), 11))
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[2].cfgValue"))
							.sendKeys(transid3);
			}
			if (transid4 != null && transid4.trim().length() > 0) {
				if (AbCommonFiles.isElementPresent(driver, By.id(
						"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[3].cfgValue"), 12))
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[3].cfgValue"))
							.sendKeys(transid4);
			}
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(driver
						.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")));
				actions.perform();

			} catch (Exception e) {
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WebElement rsaCheckedBox = driver.findElement(
					By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_skipAACheckbox"));
			if (!rsaCheckedBox.isSelected()) {
				rsaCheckedBox.click();
			}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema")).click();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema")).clear();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema")).sendKeys(udpSchema);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")).click();
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String HomeUrl= driver.getCurrentUrl();
			String error_while_login=null;
			System.out.println("Login "+HomeUrl);
			if(HomeUrl.toLowerCase().contains("home")) {
				
				System.out.println("Login Pass "+HomeUrl);
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Login Successfully with User ID: "+userid+" Password: "+pwd+" Trans 1: "+transid1+" Trans 2: "+transid2+" UDP Schema: "+ udpSchema, testStepRowIndex, 16);

				AutoBoatExecutor.homepgType = Homepagetype(driver);
				System.out.println("Home Page type: "+AutoBoatExecutor.homepgType);
				
			}
			else {
				
				System.out.println("Login failed "+HomeUrl);
				
				
				//WebElement alert=driver.findElement(By.xpath("//strong[@class='ah-error-message ah-font-m']"));
				if(driver.findElement(By.xpath("//strong[@class='ah-error-message ah-font-m']")).isDisplayed()) {
				error_while_login=driver.findElement(By.xpath("//strong[@class='ah-error-message ah-font-m']")).getText();
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,error_while_login , testStepRowIndex, 16);
				
				System.out.println(error_while_login);
				System.out.println("Login failed "+HomeUrl);
				}

				//WebElement error_msg_after_login= driver.findElement(By.xpath("//div[@class=\"data-unavailable-message ah-placeholder-content ah-placeholder\"]"));
				if(driver.findElement(By.xpath("//div[@class=\"data-unavailable-message ah-placeholder-content ah-placeholder\"]")).isDisplayed()){
				String error_msg=driver.findElement(By.xpath("//div[@class=\"data-unavailable-message ah-placeholder-content ah-placeholder\"]")).getText();
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,error_msg, testStepRowIndex, 16);
				
				System.out.println(error_msg);
				
				}
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
				
		
			// AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS, Time: "+LocalDateTime.now().format(formatter), rowIndex, 14);
			// loginPage = true;
			
//			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS, Time: "+LocalDateTime.now().format(formatter), rowIndex, 14);
	//		loginPage = true;
			
		}	}
		
		else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}
		
	}
	


	public static void LoginURL(String browserName, String url, WebDriver driver, String testSheetName, int testStepRowIndex) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		if (browserName.toLowerCase().contains("firefox")) {

			try {
				driver.get(url);
				
			} catch (Exception e) {
				try {
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(By.id("advancedButton"))).click();
					wait.until(ExpectedConditions.elementToBeClickable(By.id("exceptionDialogButton"))).click();
				} catch (InterruptedException e2) {
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				try {
					String pathExeToast = System.getProperty("user.dir") + "//HandleConfrmCertificate.exe";
					try {
						Runtime.getRuntime().exec(pathExeToast).waitFor();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} finally {
				// try {
				// driver.manage().timeouts().implicitlyWait(2,
				// TimeUnit.SECONDS);
				// driver.findElement(By.id("cookieDialogContinueBtn")).click();
				// driver.manage().timeouts().implicitlyWait(80,
				// TimeUnit.SECONDS);
				// } catch (Exception zz) {
				// }
			}
		} else if (browserName.toLowerCase().contains("ie") || browserName.toLowerCase().contains("internet")) {
			try {
				driver.get(url);
				Thread.sleep(2000);
				if (driver.getTitle().contains("Certificate Error")) {
					wait.until(ExpectedConditions.elementToBeClickable(By.id("overridelink"))).click();
					Thread.sleep(4000);
				}
			} catch (Exception e) {
			}
		} else if (browserName.toLowerCase().contains("chrome") || browserName.toLowerCase().contains("google")) {
			driver.get(url);
			 try {
			 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			 driver.findElement(By.id("cookieDialogContinueBtn")).click();
			 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			 } catch (Exception zz) {
			 }
		}
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		try{
		if (driver.findElement(By.xpath("//button[@title='Accept cookies']")).isDisplayed()) {
			
			driver.findElement(By.xpath("//button[@title='Accept cookies']")).click();
		} else if (driver.findElement(By.xpath("//button[@title='Accept cookies']")).isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@title='Accept cookies']")).click();
		}
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		
		if (driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).isDisplayed()) {
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Login page open successfully for URL : "+url, testStepRowIndex, 16);
			
		}
		else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
		}
	}

	public static void closeFlyout(WebDriver driver, String testSheetName, int testStepRowIndex){
		boolean clicked = false;
		if (AbCommonFiles.isElementPresent(driver, By.id("alFlyout"), 10)) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.document.getElementById('alDialogCloseBtn').click()");
			System.out.println("GMC Flyout Closed");
			clicked=true;
		} else {
			if (AbCommonFiles.isElementPresent(driver, By.id("ahFlyout"), 10)) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.document.getElementById('ahDialogCloseBtn').click()");
				System.out.println("GMC Flyout Closed");
				clicked=true;
				
			}
		}
		if(clicked){
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "GMC Flyout Closed", testStepRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}
		else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "GMC Flyout does not appear", testStepRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		}
	} // End closeFlyout
	

	
	public static void clkheaderLink(WebDriver driver, String testSheetName, int testStepRowIndex, String headerLinkName, String linkName){
		
		driver.findElement(By.xpath("//*[contains(@class,'-aux-nav-primary')]//li//a[contains(.,'Your Profile')]")).click();
		if (driver.findElement(By.linkText(linkName)).isDisplayed()) {
			driver.findElement(By.linkText(linkName)).click();
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, linkName+" Link Clicked", testStepRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		} else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}
	
		
	}// End clkheaderLink
	
	public static void tableTextCheck(WebDriver driver, String testSheetName, int testStepRowIndex, String tableXpathProp, String tableXpathValue, String rowNum, String ColNum, String expectedText){
		
		int rowNumInt= Integer.parseInt(rowNum);
		int ColNumInt= Integer.parseInt(ColNum);
		////*[contains(@id,'_personalinfotabcontainerportlet_WAR_')]//tr[1]/td[1]

		String cellText = driver.findElement(By.xpath("//*[contains(@"+tableXpathProp+",'"+tableXpathValue+"')]//tr["+rowNumInt+"]/td["+ColNumInt+"]")).getText();

		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, cellText, testStepRowIndex, 16);
		
		if(AbCommonFiles.compareString(expectedText, cellText)){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}
	}
	


	
	public static void PItableTextCheck(WebDriver driver, String testSheetName, int testStepRowIndex, String tableXpathProp, String tableXpathValue, String tdXpathProp, String tdXpathValue,  String rowNum, String ColNum, String expectedText){
		
		int rowNumInt= Integer.parseInt(rowNum);
		int ColNumInt= Integer.parseInt(ColNum);
		
		String cellText = driver.findElement(By.xpath("//*[contains(@"+tableXpathProp+",'"+tableXpathValue+"')]//tr["+rowNumInt+"][@"+tdXpathProp+"='"+tdXpathValue+"']/td["+ColNumInt+"]/p")).getText();
		
		// String cellText = driver.findElement(By.xpath("//*[contains(@id,'_addressinquiryportlet_WAR_')]//tr["+rowNumInt+"][@class='readmessage']/td["+ColNumInt+"]/p")).getText();
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, cellText, testStepRowIndex, 16);
		

		if(AbCommonFiles.compareString(expectedText, cellText)){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	  }
	}
	

	
	public static void waitSeconds(WebDriver driver, String waitTime){
		int waitTimeSeconds= Integer.parseInt(waitTime);
		driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
	}
	
	public static void clickByXpath(WebDriver driver, String testSheetName, int testStepRowIndex, String clkByXpath){
		
		//driver.findElement(By.xpath("//li[contains(text(),'Personal Details')]")).click();
		
		if(AbCommonFiles.isElementPresent(driver,By.xpath(""+clkByXpath+""),5)){
			driver.findElement(By.xpath(""+clkByXpath+"")).click();
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Clicked" , testStepRowIndex, 16);
		
		System.out.println("Clicked "+clkByXpath);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Not clicked" , testStepRowIndex, 16);
			
		}
		
/*		if(driver.findElement(By.xpath(""+clkByXpath+"")).isDisplayed()){
			driver.findElement(By.xpath(""+clkByXpath+"")).click();
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}*/
	}
	

	public static void VerifyByXpath(WebDriver driver, String testSheetName, int testStepRowIndex, String verifyByXpath, String expectedResult)
	{
		if(AbCommonFiles.isElementPresent(driver,By.xpath(""+verifyByXpath+""),5)){
		String verifyText = driver.findElement(By.xpath(""+verifyByXpath+"")).getText();
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, verifyText, testStepRowIndex, 16);
		

		if(AbCommonFiles.compareString(expectedResult, verifyText)){
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		}
		
		}
		else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Does not exists" , testStepRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	  }
		
	}
	

	
	public static void tagHeading2(WebDriver driver, String testSheetName, int testStepRowIndex, String h2text, String expectedResult)
	{
		
		String tagH2 = driver.findElement(By.xpath("//h2[contains(text(),'"+h2text+"')]")).getText();
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tagH2, testStepRowIndex, 16);
		

		if(AbCommonFiles.compareString(expectedResult, tagH2)){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	
	  }
		
	}
	

	
	public static void tagHeading3(WebDriver driver, String testSheetName, int testStepRowIndex, String h3text, String expectedResult)
	{
		
		String tagH3 = driver.findElement(By.xpath("//h3[contains(text(),'"+h3text+"')]")).getText();
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tagH3, testStepRowIndex, 16);
		

		if(AbCommonFiles.compareString(expectedResult, tagH3)){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	  }
		
	}
	

	
	public static void switchToiframeByIndex(WebDriver driver, String testSheetName, int testStepRowIndex, String iframeNum, String expectedResult)
	{
		int iframeNumber =   Integer.parseInt(iframeNum);
		
		
		driver.switchTo().frame(iframeNumber);
		
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, iframeNumber + " Clicked", testStepRowIndex, 16);
		

		if(AbCommonFiles.compareString(expectedResult, "Clicked")){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	  }
		
	}
	
	public static void switchToiframeByXpath(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath, String expectedResult)
	{
	//int iframeNumber = Integer.parseInt(iframeNum);
	WebElement text1 =driver.findElement(By.xpath(""+xpath+""));
	driver.switchTo().frame(text1);
	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Switched", testStepRowIndex, 16); if(AbCommonFiles.compareString(expectedResult, "Switched")){
	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	}else{
	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);

	}
	}
	
	public static void switchToParentWindow(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath, String expectedResult)
	{
	
		//switch to parent window
		driver.switchTo().defaultContent();
		
	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Switched", testStepRowIndex, 16); if(AbCommonFiles.compareString(expectedResult, "Switched")){
	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	}else{
	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);

	}
	}

	
	 public static void CreateHTMLReport() throws IOException {
	 
		 BufferedWriter bw = new BufferedWriter(new FileWriter(AutoBoatExecutor.htmlPath));
		 bw.write("<html><head><title>New Page</title></head><body><p>This is test Body</p></body></html>");
		 bw.close();
		 
	 }
	 
		public static void verifyLink(WebDriver driver, String testSheetName, int testStepRowIndex, String linkXpath, String expectedResult) {
			String linkhref="";
			try{
			linkhref=driver.findElement(By.xpath(""+linkXpath+"")).getAttribute("href");
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, linkhref, testStepRowIndex, 16);
			}
			catch(Exception ex){
				
			}
			
			if(AbCommonFiles.containsString(expectedResult, linkhref) && expectedResult != ""){
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			}
			
		}
		
		
		public static void switchWindowByTitleName(WebDriver driver, String testSheetName, int testStepRowIndex, String childWindowTitle, String expectedResult) {
			// It will return the parent window name as a String
			String parent=driver.getWindowHandle();
			
			System.out.println("parent id "+parent+ " Title "+driver.getTitle());

			Set<String>s = driver.getWindowHandles();

			// Now iterate using Iterator
			Iterator<String> I1= s.iterator();

			while(I1.hasNext())
			{

			String child_window=I1.next();
			System.out.println("child id "+child_window);
			System.out.println(driver.switchTo().window(child_window).getTitle());
			if(!parent.equals(child_window))
			{
			driver.switchTo().window(child_window);

			String windowTitle = driver.switchTo().window(child_window).getTitle();

			//driver.close();
			
			if (windowTitle.equals(childWindowTitle)){
				System.out.println(driver.getTitle());
				break;
			}
			
			}

			}
			
			System.out.println(driver.getTitle());
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Switched", testStepRowIndex, 16); if(AbCommonFiles.compareString(expectedResult, "Switched")){
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
				}else{
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);

				}
		}
		
		
		public static void ExecuteByJScommand(WebDriver driver, String testSheetName, int testStepRowIndex, String jscommand, String expectedText)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(""+jscommand+"");
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Executed", testStepRowIndex, 16);

		if(AbCommonFiles.compareString("Executed", expectedText)){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
		}
		}
	
	    public static void pw(Browser browser){
	        System.out.println( "Hello World!" );
	        
	        

	        Page page = browser.newPage();
	    //    page.navigate("https://www.google.com");
	        
	        page.navigate("https://litlb108.upoint.qa.alight.com/web/premierUAT");
	        //page.pause();
	        
	        System.out.println(page.title());

	        page.fill("(//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_userId'])[1]", "000HEW00339003");
	        page.fill("(//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_password'])[1]", "A999999a");
	        page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue']", "L74B");
	        page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue']", "L74B");
	        page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema']", "Q600095B");
	        page.click("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_logOn']");
	        
	        System.out.println(page.locator("(//a[contains(text(),'By logging on')])[1]").textContent());
	        
	    }
	 
// Hitesh's Function End
	    
	
	 //Jyoti's Function
	 
		//Jyoti's Function
		 
		 public static void tagHeading1(WebDriver driver, String testSheetName, int testStepRowIndex, String h1text, String expectedResult)
	     {
	            
	            String tagH1 = driver.findElement(By.xpath("//h1[contains(text(),'"+h1text+"')]")).getText();
	            
	            AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tagH1, testStepRowIndex, 16);
	            

	            if(AbCommonFiles.compareString(expectedResult, tagH1)){
	            AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	            }else{
	                   AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	            
	       }
	            
	     } 

				public static void clkNavigation(WebDriver driver, String testSheetName, int testStepRowIndex, String pLinkName, String sLinkName, String tLinkName){
					
				//	  if (driver.findElement(By.linkText(pLinkName)).isDisplayed()) {
				//		driver.findElement(By.xpath("//div[@id='al-primary-nav']//a[contains(text(),'"+pLinkName+"')]")).click();
						
				            if (driver.findElement(By.linkText(sLinkName)).isDisplayed()) {
				            driver.findElement(By.xpath("//div[@id='al-secondarynav-bdr']//a[contains(text(),'"+sLinkName+"')]")).click();
				            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
						            if (driver.findElement(By.linkText(tLinkName)).isDisplayed()) {
						                   driver.findElement(By.linkText(tLinkName)).click();
						                   AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tLinkName+" Link Clicked", testStepRowIndex, 16);
						                   AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
						            } else{
						                   AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
						                   
						            }
				            }
				            
					  
				   }// End clkterheaderLink 

		 
				
				public static void Scrollslider(WebDriver driver, String testSheetName, int testStepRowIndex, String sliderXpath, String sliderxcoord, String sliderycoord, String expectedResult) {



					int sliderxcoordinate= Integer.parseInt(sliderxcoord);
					int sliderycoordinate= Integer.parseInt(sliderycoord);





					WebElement Slider=driver.findElement(By.xpath(""+sliderXpath+""));
					Actions actions =new Actions(driver);
					actions.clickAndHold(Slider).moveByOffset(sliderxcoordinate,sliderycoordinate).release().build().perform();

					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Selected", testStepRowIndex, 16);



					if(AbCommonFiles.compareString(expectedResult, "Selected")){
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
					}else{
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
					
						}
					}// End Scrollslider function
				
				
				public static void mouseHover(WebDriver driver, String testSheetName, int testStepRowIndex, String mousehov, String expectedResult) {

					WebElement hover=driver.findElement(By.xpath(""+mousehov+""));
					Actions actions =new Actions(driver);
					actions.moveToElement(hover).perform();

					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Selected", testStepRowIndex, 16);

					if(AbCommonFiles.compareString(expectedResult, "Selected")){
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
					}else{
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
					
					}
					} 
				

				public static void SwitchTab1(WebDriver driver, String testSheetName, int testStepRowIndex, String expectedResult) {



					Set<String> allWindows = driver.getWindowHandles();
					ArrayList<String> tabs=new ArrayList<>(allWindows);
					driver.switchTo().window(tabs.get(1));
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Switchedtab", testStepRowIndex, 16);



					if(AbCommonFiles.compareString(expectedResult, "Switchedtab")){
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
					}else{
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
					
				
					}
					} //Jyoti 1 End		
				
		 //End of Jyoti's Functions
		
				
				//Sonali's function Start
				
				public static String Homepagetype(WebDriver driver) {
		       
		           String homeurl= driver.getCurrentUrl();
		              
		           String hometype=null;
		           
		           if(homeurl.contains("angular")) {

		              if(homeurl.contains("newhome")) {
		                           
		                           hometype="thriveClient";
		                           //AbCommonFiles.writeExpressionDataOnExistingExcel(UFSAutomationExecutor.excelFilePath, testSheetName,hometype, testStepRowIndex, 16);
		                           System.out.println(hometype);
		                    }
		                    

		                    else if(homeurl.contains("home")) {
		                    	
		                    	if(homeurl.contains("homepage")){
		                            hometype="worklifeClient";
		                            System.out.println(hometype);
		                    	}
		                    	else{
		                           hometype="nonthriveClient";
		                           //AbCommonFiles.writeExpressionDataOnExistingExcel(UFSAutomationExecutor.excelFilePath, testSheetName,hometype, testStepRowIndex, 16);
		                           System.out.println(hometype);
		                    	}
		                    }
		    
		           }
		           
		           //For non angular
		           else {
		               if(homeurl.contains("home")) {
		                     hometype="liferayClient";
		                           //AbCommonFiles.writeExpressionDataOnExistingExcel(UFSAutomationExecutor.excelFilePath, testSheetName,hometype, testStepRowIndex, 16);
		                           System.out.println(hometype);
		                    }
		           }
		           
		           return hometype;
		       }


				       //Sonali's function End 
			
				//Neha's Functions
				
	 public static void ScrollDown(WebDriver driver) {

		           
		    	JavascriptExecutor js=(JavascriptExecutor) driver;
		    	js.executeScript("window.scrollBy(0,1000)","");
		    }
		    
		
		    public static void Handling_dropdown(WebDriver driver, String Browser, String xpath, String xpath_value, String xpath_value2)
		    {
		

		    Select dropdown=new Select(driver.findElement(By.xpath("//select[@class='ng-untouched ng-pristine ng-valid']")));
		    
		   // to find how many options present in dropdown--



		    System.out.println("No of option present in dropdown:" +dropdown.getOptions().size());



		    // extract all the option and print them---
		    List<WebElement>options=dropdown.getOptions();
		    for (WebElement e:options)
		    {
		    System.out.println(e.getText());
		    }



		    //select option from the dropdown
		    dropdown.selectByIndex(1);

		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    // then click continue
		    driver.findElement(By.xpath("//span[contains(text(),'Continue')]")).click();

		    }
		    public static void spanHeading(WebDriver driver, String testSheetName, int testStepRowIndex, String text, String expectedResult)
		     {
		            
		            String tag = driver.findElement(By.xpath("//span[contains(text(),'"+text+"')]")).getText();
		            
		           
		            
		           AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tag, testStepRowIndex, 16);
		            

		            if(AbCommonFiles.compareString(expectedResult, tag)){
		            AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		            }else{
		                   AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		            
		       }
		     }
		    
			
		      
			public static void Update_info(WebDriver driver, String Browser, String testSheetName, int testStepRowIndex, String xpath, String xpath1)
			{
			
			
			 
		
			 WebElement gender= driver.findElement(By.xpath(""+xpath+""));
			              gender.click();
			              if((gender).isDisplayed()){
				       	WebElement option=driver.findElement(By.xpath(""+xpath1+""));
				       	          option.click();
				       		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
				       		}else{
				       			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
				       			
				       		}
				
			 }
	          
			
			

		   public static void bene_selectIndex(WebDriver driver, String propName, String propValue, String selIndex) {
			   int selIndex2= Integer.parseInt(selIndex);
				
				Select s=new Select(driver.findElement(By.xpath("//select[@"+propName+"='"+propValue+"']")));
				s.selectByIndex(selIndex2);
				
		       		}
		   
		   public static void bene_selectValue(WebDriver driver, String xpath, String selValue) {
			   int selIndex1= Integer.parseInt(selValue);
				Select s=new Select(driver.findElement(By.xpath(""+xpath+"")));
				

				s.selectByIndex(selIndex1);
			   
		   }
		   
		   public static void tableCheck(WebDriver driver, String testSheetName, int testStepRowIndex, String tableXpathProp, String tableXpathValue, String rowNum, String ColNum){
				
				int rowNumInt= Integer.parseInt(rowNum);
				int ColNumInt= Integer.parseInt(ColNum);
				////*[contains(@id,'_personalinfotabcontainerportlet_WAR_')]//tr[1]/td[1]

			WebElement cellText = driver.findElement(By.xpath("//*[contains(@"+tableXpathProp+",'"+tableXpathValue+"')]//tr["+rowNumInt+"]/td["+ColNumInt+"]"));
				                     cellText.click();
				
				if(cellText.isSelected()){
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
				}else{
					AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
					
				}
			}


			
		 public static void textInput(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath,String input){
				
			 			if(AbCommonFiles.isElementPresent(driver,By.xpath(""+xpath+""),5)){
							driver.findElement(By.xpath(""+xpath+"")).clear();
			       			driver.findElement(By.xpath(""+xpath+"")).sendKeys(input);
			       			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Entered value "+input+" in textbox.", testStepRowIndex, 16);
			       		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			       		}else{
			       			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Not able to enter value "+input+" in textbox.", testStepRowIndex, 16);
			       			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			       			
			       		}
			
		 }
		 

		 public static void waitExplicit(WebDriver driver,String xpath, String waitTime)
			{
				int waitTimeSeconds= Integer.parseInt(waitTime);
			WebDriverWait wait=new WebDriverWait(driver, waitTimeSeconds);

		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""+xpath+""))).click();
			}
		 
	public static void SwitchWindow(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath) throws InterruptedException
					    {
					    	//String parent = driver.getWindowHandle();
					    	WebElement paylink = driver.findElement(By.xpath(""+xpath+""));
					    	paylink.click();

					    	driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
					        Set<String> windows = driver.getWindowHandles();
					    	Iterator<String> iterate = windows.iterator();
					    	String parentWindow = iterate.next();
					    	System.out.println(parentWindow);
					    	String childWindow = iterate.next();
					    	driver.switchTo().window(childWindow);
					    	//driver.switchTo().window(parent);
					    	String account = driver.findElement(By.xpath("//label[@for='accountname']")).getText();
				    	    System.out.println(account);
				    	    String title= driver.getTitle();
				    	    System.out.println(title);
				    	    String url=driver.getCurrentUrl();
				    	    System.out.println(url);
				    	    Thread.sleep(1000);
				    	  //  AbCommonFiles.captureScreenshot(driver, AutoBoatExecutor.screenshotpath);
				    	    WebElement next = driver.findElement(By.xpath("//button[@id='accountInfonextButton']"));
				    	    next.click();
				    	    Thread.sleep(1000);
				    	    //Close the child window;
				    	    driver.close();
				    	    driver.switchTo().window(parentWindow);
				    	  
	                         if(!childWindow.equals(parentWindow))
	                         {
							
							AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,"Switched to Main Window", testStepRowIndex, 16);
							AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
							}else{
							AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
							
					    }
					    }

	
	public static void LoginUpoint_UncheckedRSA(WebDriver driver, String browserName, String url, 
			String userid, String pwd, String udpSchema, String transid1, String transid2, 
			String transid3, String transid4, String testSheetName, int testStepRowIndex){
		

		LoginURL(browserName, url, driver, testSheetName, testStepRowIndex);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		try{
		if (driver.findElement(By.xpath("//button[@title='Accept cookies']")).isDisplayed()) {
			
			driver.findElement(By.xpath("//button[@title='Accept cookies']")).click();
		} else if (driver.findElement(By.xpath("//button[@title='Accept cookies']")).isDisplayed()) {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@title='Accept cookies']")).click();
		}
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		if (driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).isDisplayed()) {
			
			

			
			
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).click();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).clear();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_userId")).sendKeys(userid);
			

			String RACFflag="NA";
			
			if(RACFflag.toLowerCase().equals("no") || RACFflag.toLowerCase().equals("n")) {
				WebElement racf_enableNPVCheckbox = driver
						.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_enableNPVCheckbox"));
				if (racf_enableNPVCheckbox.isSelected()) {
					racf_enableNPVCheckbox.click();
				}
				
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
						.sendKeys(pwd);
				
			} else if(RACFflag.toLowerCase().equals("yes") || RACFflag.toLowerCase().equals("y")) {
				WebElement racf_enableNPVCheckbox = driver
						.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_enableNPVCheckbox"));
				if (racf_enableNPVCheckbox.isSelected()==false) {
					racf_enableNPVCheckbox.click();
				}
				
	/*				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminId")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminId")).sendKeys(RACFid);
				
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminPass")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_adminPass")).sendKeys(getDecryptedVal(RACFpassword));
				*/
				
			} else {
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password")).clear();
				driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_password"))
						.sendKeys(pwd);
				
			}
			
			
			if (transid1 != null && transid1.trim().length() > 0) {
				driver.findElement(By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
						.clear();
				driver.findElement(By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue"))
						.sendKeys(transid1);
			}
			if (transid2 != null && transid2.trim().length() > 0) {
				if (AbCommonFiles.isElementPresent(driver, By
						.id("_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"),
						10)) {
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
							.clear();
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue"))
							.sendKeys(transid2);
				}
			}
			if (transid3 != null && transid3.trim().length() > 0) {
				if (AbCommonFiles.isElementPresent(driver, By.id(
						"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[2].cfgValue"), 11))
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[2].cfgValue"))
							.sendKeys(transid3);
			}
			if (transid4 != null && transid4.trim().length() > 0) {
				if (AbCommonFiles.isElementPresent(driver, By.id(
						"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[3].cfgValue"), 12))
					driver.findElement(By.id(
							"_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[3].cfgValue"))
							.sendKeys(transid4);
			}
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(driver
						.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")));
				actions.perform();

			} catch (Exception e) {
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WebElement rsaCheckedBox = driver.findElement(
					By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_skipAACheckbox"));
			if (!rsaCheckedBox.isSelected()) {
			//	rsaCheckedBox.click();
			}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema")).click();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema")).clear();
			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema")).sendKeys(udpSchema);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.id("_ParticipantLogon20_WAR_ahcommonauthportlet_logOn")).click();
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Login Successfully with User ID: "+userid+" Password: "+pwd+" Trans 1: "+transid1+" Trans 2: "+transid2+" UDP Schema: "+ udpSchema, testStepRowIndex, 16);
			
//			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS, Time: "+LocalDateTime.now().format(formatter), rowIndex, 14);
//			loginPage = true;
			
		}	else {
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			String homeScreenshotPath = AutoBoatExecutor.screenshotpath + "/" + LocalDateTime.now() + ".png";
			System.out.println("QAQCScreenshots: " + homeScreenshotPath);
			
		}
		
	}   

	 
	public static void verifyUrl(WebDriver driver, String testSheetName, int testStepRowIndex, String data) throws InterruptedException
	{
		
		
	    String expectedTxt = data;
	    String actualTxt = driver.getCurrentUrl();
	    System.out.println(actualTxt);
	    if(actualTxt.equals(expectedTxt)) {
	        
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, actualTxt, testStepRowIndex, 16);
	    	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    } else {
	    	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    }
	   

	}

	public static void SwitchToParentTab(WebDriver driver, String testSheetName, int testStepRowIndex, String expectedResult) {



		Set<String> allWindows = driver.getWindowHandles();
		ArrayList<String> tabs=new ArrayList<>(allWindows);
		driver.switchTo().window(tabs.get(0));
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Switchedtab", testStepRowIndex, 16);



		if(AbCommonFiles.compareString(expectedResult, "Switchedtab")){
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		

		}
		}

	public static void SwitchToParentWindow(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath) throws InterruptedException
	{
		//String parent = driver.getWindowHandle();
		//WebElement paylink = driver.findElement(By.xpath(""+xpath+""));
		//paylink.click();

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	    Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterate = windows.iterator();
		String parentWindow = iterate.next();
		System.out.println(parentWindow);
		String childWindow = iterate.next();
		driver.switchTo().window(childWindow);
		Thread.sleep(1000);
		driver.close();
		driver.switchTo().window(parentWindow);
		if(!childWindow.equals(parentWindow))
	     {
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,"Switched to Main Window", testStepRowIndex, 16);
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	}
		
	}

	           public static void verifyTitle(WebDriver driver, String testSheetName, int testStepRowIndex, String data) {
	    String expectedTxt = data;
	    String actualTxt = driver.getTitle();
	    System.out.println(actualTxt);
	    if(actualTxt.equals(expectedTxt)) {
	        
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, actualTxt, testStepRowIndex, 16);
	    	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    } else {
	    	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    }
	   
	}

	   
	           
	           public static void BrowserForwardbutton(WebDriver driver, String testSheetName, int testStepRowIndex) throws InterruptedException {
	        	   driver.navigate().forward();
	        	   Thread.sleep(6000);
	        	   
	        	   
	        	    if("" != null) {
	        	        
	        	        
	        	    	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	        	    } 
	        	}

	      public static void VerifyTooltip(WebDriver driver, String testSheetName, int testStepRowIndex, String verifyxpath,String expectedResult)
	            {
	    	  WebElement tip = driver.findElement(By.xpath(""+verifyxpath+""));
	    	  String verifyText = tip.getAttribute("title");
	    	  AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, verifyText, testStepRowIndex, 16);
	    	  if(AbCommonFiles.compareString(expectedResult, verifyText)){
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	             }
	    	  else{
	       AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	       }
	                         }
	      

	        public static void mouseHoverTooltip(WebDriver driver, String testSheetName, int testStepRowIndex, String mousehov, String spanxpath, String expectedResult)
	        {
	        	WebElement hover=driver.findElement(By.xpath(""+mousehov+""));
	                 Actions actions =new Actions(driver);
	              actions.moveToElement(hover).perform();
	                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	                 String tooltip = driver.findElement(By.xpath(""+spanxpath+"")).getText();
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tooltip, testStepRowIndex, 16);
	                      }
	        

	        public static void CheckBold(WebDriver driver, String testSheetName, int testStepRowIndex, String id, String expectedResult)
	        {
	        // Assert tag name of bold text
	        // String tagName1= driver.findElement(By.id(""+id+"")).getTagName();
	        // Assert.assertEquals(tagName1, "strong");
	        // AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,tagName1, testStepRowIndex, 16);
	        // Get value of font-weight and assert if it is bold
	        String fontWeight= driver.findElement(By.xpath(""+id+"")).getCssValue("font-weight");
	        System.out.println(fontWeight);
	        try {
	       // Assert.assertTrue(Integer.parseInt(fontWeight)>100);
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,"text is bold", testStepRowIndex, 16);
	        }
	        catch (IllegalArgumentException e){
	        e.printStackTrace();
	        if(AbCommonFiles.compareString(expectedResult, "text is bold")){
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	        }else{
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	        }
	        }
	        }
	        
	        public static void Print_Button(WebDriver driver) throws InterruptedException
	        {
	        	// open print dropdown
	        	//WebDriverWait wait = new WebDriverWait(driver, 10);
	        	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//cr-button[@class='cancel-button']"))).click();
	          Thread.sleep(5000);
	        	// click print button
	        	//WebElement printButton = driver.findElement(By.xpath("//*[@id=\"sidebar\"]//print-preview-button-strip//div/cr-button[2]"));
	        	//printButton.click();
	        	//driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
	        	WebElement webElement = driver.findElement(By.xpath("/html/body/print-preview-app//print-preview-sidebar//print-preview-button-strip//div/cr-button[2]"));
	        	webElement.sendKeys(Keys.TAB);
	        	webElement.sendKeys(Keys.ENTER);
	        	//driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
	        }
	        
	        
	    	 public static void Verifyautosuggestion(WebDriver driver, String testSheetName, int testStepRowIndex, String entertextxpath, String input, String autosearchxpath, String match, String expectedResult) throws InterruptedException
	    	 {
	    			
	    			WebElement text1 =driver.findElement(By.xpath(""+entertextxpath+""));
	    			                text1.clear();
	    			               // String textToSelect = "Defined Benefits"; 
	    				     WebElement auto= driver.findElement(By.xpath(""+entertextxpath+""));
	    				     auto.sendKeys(input);
	    		 
	    	                 Thread.sleep(2000);
	    	                // WebDriverWait wait = new WebDriverWait(driver,30);
	    	         		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(""+verifyxpath+"")));
	    	         		
	    	         		List<WebElement> list = driver.findElements(By.xpath(""+autosearchxpath+""));
	    	         		String options="";
	    	         		
	    	         
	    	         		System.out.println("Auto Suggest List ::" + list.size());
	    	         		
	    	         		for(int i = 0 ;i< list.size();i++)
	    	         		{
	    	         			options+=list.get(i).getText()+"\n";
	    	         			System.out.println(list.get(i).getText());
	    	         			
	    	         			if(list.get(i).getText().equals(match))
	    	         			{
	    	         				list.get(i).click();
	    	         				break;
	    	         			}
	    	         		}
	    	         		
	    	      	      
	    	      	      
	    	         		System.out.println(options);
	    	      AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, options, testStepRowIndex, 16);
	    	      if(AbCommonFiles.compareString(expectedResult, options)){
	    	    	     AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    	    	          }
	    	    	 	  else{
	    	    	    AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    	    	    }
	    	    	                      }
	    	 
		                      
	       

	    	 public static void back(WebDriver driver, String testSheetName, int testStepRowIndex, String expectedResult) {

	    		 driver.navigate().back();
	    		 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Back", testStepRowIndex, 16);

	    		 if(AbCommonFiles.compareString(expectedResult, "Back")){
	    		 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    		 }else{
	    		 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    		 //captureScreenshot(driver, AutoBoatExecutor.screenshotpath);

	    		 }
	    		 }
	    	      


	    	 
	    		public static void SwitchToChildWindow(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath) throws InterruptedException
	    		{
	    			//String parent = driver.getWindowHandle();
	    			WebElement paylink = driver.findElement(By.xpath(""+xpath+""));
	    			paylink.click();

	    			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	    		    Set<String> windows = driver.getWindowHandles();
	    			Iterator<String> iterate = windows.iterator();
	    			String parentWindow = iterate.next();
	    			System.out.println(parentWindow);
	    			String childWindow = iterate.next();
	    			driver.switchTo().window(childWindow);
	    			if(!childWindow.equals(parentWindow))
	    		     {
	    			
	    			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,"Switched to Main Window", testStepRowIndex, 16);
	    			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    			}else{
	    			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    			
	    		}
	    		}
	        
	

   	
	    	//Neha's Functions End here
	        
   	
	    // Allwyn Functions Start here
	        
	        public static void Verifyinputtext(WebDriver driver, String testSheetName, int testStepRowIndex, String verifyByXpath, String expectedResult)
	        { String verifyText = driver.findElement(By.xpath(""+verifyByXpath+"")).getAttribute("value"); 
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, verifyText, testStepRowIndex, 16); 
	        if(AbCommonFiles.compareString(expectedResult, verifyText)){
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	        }else{
	        AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17); } }



	        public static void clickAndHold(WebDriver driver, String testSheetName, int testStepRowIndex, String sliderXpath,String expectedResult) {

	            WebElement Slider=driver.findElement(By.xpath(""+sliderXpath+""));
	            Actions actions =new Actions(driver);
	            actions.clickAndHold(Slider).build().perform();

	            AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Selected", testStepRowIndex, 16);



	            if(AbCommonFiles.compareString(expectedResult, "Selected")){
	            AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	            }else{
	            AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	            
	                   }
	            }
	        
	    	public static void Click_Cookies(String browserName, String url, WebDriver driver) {
	    		WebDriverWait wait = new WebDriverWait(driver, 10);
	    		if (browserName.toLowerCase().contains("firefox")) {

	    			try {
	    				driver.get(url);
	    			} catch (Exception e) {
	    				try {
	    					Thread.sleep(2000);
	    					wait.until(ExpectedConditions.elementToBeClickable(By.id("advancedButton"))).click();
	    					wait.until(ExpectedConditions.elementToBeClickable(By.id("exceptionDialogButton"))).click();
	    				} catch (InterruptedException e2) {
	    				}
	    				try {
	    					Thread.sleep(5000);
	    				} catch (InterruptedException e2) {
	    					e2.printStackTrace();
	    				}
	    				try {
	    					String pathExeToast = System.getProperty("user.dir") + "//HandleConfrmCertificate.exe";
	    					try {
	    						Runtime.getRuntime().exec(pathExeToast).waitFor();
	    					} catch (IOException e1) {
	    						// TODO Auto-generated catch block
	    						e1.printStackTrace();
	    					}
	    				} catch (InterruptedException e1) {
	    					e1.printStackTrace();
	    				}
	    				try {
	    					Thread.sleep(5000);
	    				} catch (InterruptedException e1) {
	    					e1.printStackTrace();
	    				}
	    			} finally {
	    				// try {
	    				// driver.manage().timeouts().implicitlyWait(2,
	    				// TimeUnit.SECONDS);
	    				// driver.findElement(By.id("cookieDialogContinueBtn")).click();
	    				// driver.manage().timeouts().implicitlyWait(80,
	    				// TimeUnit.SECONDS);
	    				// } catch (Exception zz) {
	    				// }
	    			}
	    		} else if (browserName.toLowerCase().contains("ie") || browserName.toLowerCase().contains("internet")) {
	    			try {
	    				driver.get(url);
	    				Thread.sleep(2000);
	    				if (driver.getTitle().contains("Certificate Error")) {
	    					wait.until(ExpectedConditions.elementToBeClickable(By.id("overridelink"))).click();
	    					Thread.sleep(4000);
	    				}
	    			} catch (Exception e) {
	    			}
	    		} else if (browserName.toLowerCase().contains("chrome") || browserName.toLowerCase().contains("google")) {
	    			driver.get(url);
	    			 try {
	    			 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    			 driver.findElement(By.id("cookieDialogContinueBtn")).click();
	    			 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	    			 } catch (Exception zz) {
	    			 }
	    		}
	    		else if (browserName.toLowerCase().contains("firefox") || browserName.toLowerCase().contains("firefox")) {
	    			driver.get(url);
	    			 try {
	    			 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    			 driver.findElement(By.xpath("//button[@title='Accept cookies' and @class='al-button-positive al-button-large']")).click();
	    			 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	    			 } catch (Exception zz) {
	    			 }
	    		}


	    	}
	    	public static void switchToiframeByName(WebDriver driver, String testSheetName, int testStepRowIndex, String xpath, String expectedResult)
	    	{
	    		//int iframeNumber =   Integer.parseInt(iframeNum);
	    		
	    		
	    		driver.switchTo().frame(""+xpath+"");
	    		
	             
	    		
	    		
	    		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Switched to Parent", testStepRowIndex, 16);
	    		

	    		if(AbCommonFiles.compareString(expectedResult, "Switched to Parent")){
	    		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    		}else{
	    			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    		
	    	  }
	    		
	    	}
	    	
	    	 public static void Verifyattribute(WebDriver driver, String testSheetName, int testStepRowIndex, String verifyxpath, String attribute, String expectedResult)
	         {
	 	  WebElement tip = driver.findElement(By.xpath(""+verifyxpath+""));
	 	  String verifyText = tip.getAttribute(""+attribute+"");
	 	  AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, verifyText, testStepRowIndex, 16);
	 	  if(AbCommonFiles.compareString(expectedResult, verifyText)){
	     AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	          }
	 	  else{
	    AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    }
	                      }
	    	 
	    	 
	    	 public static void Verifydropdown(WebDriver driver, String testSheetName, int testStepRowIndex, String verifyxpath, String expectedResult)
	    	 {
	    		 
	    		 
	    		  Select s = new Select(driver.findElement(By.xpath(""+verifyxpath+"")));
	    	      // getting the list of options in the dropdown with getOptions()
	    	      List <WebElement> op = s.getOptions();
	    	      String options="";
	    	      int size = op.size();
	    	      for(int i =0; i<size ; i++){
	    	    	  
	    	    	  options+=op.get(i).getText()+"\n";
	    	      
	   
	    	      }
	    	      //List<String> list = new ArrayList<String>();
	    	      //for(WebElement element : s.getOptions())
	    	      //{
	    	    	//  list.add(element.getText());
	    	     // }
	    	      System.out.println(options);
	    	      AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, options, testStepRowIndex, 16);
	    	      if(AbCommonFiles.compareString(expectedResult, options)){
	    	    	     AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
	    	    	          }
	    	    	 	  else{
	    	    	    AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	    	    	    }
	    	    	                      }


	    	      
				 
		//Allwyn Functions End here

}
