

import java.nio.file.Paths;

import org.openqa.selenium.JavascriptExecutor;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import Playwrightbasic.AbCommonFiles;



public class AbPlaywrightFunctions {
	
	
	public static void pwCaptureScreenshot(Page page, String srcFile, String sheetName, String srcName) {

		srcFile = srcFile+"/"+sheetName+"/"+srcName+"_Date_"+AutoBoatExecutor.dateWithFormat+"_Time_"+AutoBoatExecutor.timeWithFormat+".png";
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(srcFile)));
		
		}
	
	 public static void pwtextInput(Page page, String testSheetName, int testStepRowIndex, String xpathj,String input){

		 Locator xpathLocator = page.locator(""+xpathj+"");
			 if(xpathLocator.isVisible()){
//				 xpathLocator.fill(""+input+"");
				 page.fill(""+xpathj+"", ""+input+""+" ");
				 page.keyboard().press("Backspace");
				 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Entered value "+input+" in textbox.", testStepRowIndex, 16);
				 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			 }
			 else{
				 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Not able to enter value "+input+" in textbox.", testStepRowIndex, 16);
				 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
	
			 }

		 }

     public static void pwVerifyTooltip(Page page, String testSheetName, int testStepRowIndex, String verifyByXpath,String expectedResult)
     {
   		String verifyText;
   		//if(isElementPresent(driver,By.xpath(""+verifyByXpath+""),5)){
   		//String verifyText = driver.findElement(By.xpath(""+verifyByXpath+"")).getText();
   		
   		Locator xpathLocator = page.locator(""+verifyByXpath+"");
   		if(xpathLocator.isVisible()){
   			verifyText = xpathLocator.getAttribute("title");
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
     
	public static String pwHomepagetype(Page page) {
	       
        String homeurl= page.url();
           
        String hometype=null;
        
        if(homeurl.contains("angular")) {

           if(homeurl.contains("newhome")) {
                        
                        hometype="thriveClient";
                        //writeExpressionDataOnExistingExcel(UFSAutomationExecutor.excelFilePath, testSheetName,hometype, testStepRowIndex, 16);
                        System.out.println(hometype);
                 }
                 

                 else if(homeurl.contains("home")) {
                 	
                 	if(homeurl.contains("homepage")){
                         hometype="worklifeClient";
                         System.out.println(hometype);
                 	}
                 	else{
                        hometype="nonthriveClient";
                        //writeExpressionDataOnExistingExcel(UFSAutomationExecutor.excelFilePath, testSheetName,hometype, testStepRowIndex, 16);
                        System.out.println(hometype);
                 	}
                 }
 
        }
        
        //For non angular
        else {
            if(homeurl.contains("home")) {
                  hometype="liferayClient";
                        //writeExpressionDataOnExistingExcel(UFSAutomationExecutor.excelFilePath, testSheetName,hometype, testStepRowIndex, 16);
                        System.out.println(hometype);
                 }
        }
        
        return hometype;
    }

	public static void pwLoginUpoint(Page page, String browserName, String url, 
			String userid, String pwd, String udpSchema, String transid1, String transid2, 
			String transid3, String transid4, String RACFflag, String testSheetName, int testStepRowIndex){

		Locator cookiesPopup = null;
		
		if(url.isEmpty() || url.toLowerCase().equals("na")){}
		
		else{		
			page.navigate(url);
			}
		
		
		Locator userIdLocator = page.locator("(//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_userId'])[1]");
		if (userIdLocator.isVisible()) {
			
			
			cookiesPopup = page.locator("(//button[@title='Accept cookies'])[1]");
			if(cookiesPopup.isVisible()){
				cookiesPopup.click();
			}

			userIdLocator.fill(userid);
		//	page.fill("(//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_userId'])[1]", userid);

			if(RACFflag.toLowerCase().equals("no") || RACFflag.toLowerCase().equals("n")) {

				Locator racf_enableNPVCheckbox = page.locator("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_enableNPVCheckbox']");
			
				if (racf_enableNPVCheckbox.isChecked()) {
					racf_enableNPVCheckbox.click();
				}
				
				page.fill("(//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_password'])[1]", pwd);
				
			} else if(RACFflag.toLowerCase().equals("yes") || RACFflag.toLowerCase().equals("y")) {
				Locator racf_enableNPVCheckbox = page.locator("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_enableNPVCheckbox']");
				if (racf_enableNPVCheckbox.isChecked()==false) {
					racf_enableNPVCheckbox.click();
				}
				
				
			} else {
				page.fill("(//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_password'])[1]", pwd);
				
			}
			
			
			if (transid1 != null && transid1.trim().length() > 0) {
				
				page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[0].cfgValue']", transid1);

			}
			
			if (transid2 != null && transid2.trim().length() > 0) {
					page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[1].cfgValue']", transid2);
				}
			
			if (transid3 != null && transid3.trim().length() > 0) {
				page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[2].cfgValue']", transid3);
			}
			if (transid4 != null && transid4.trim().length() > 0) {
				page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_testCfgList[3].cfgValue']", transid4);
			}

			Locator rsaCheckedBox = page.locator("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_skipAACheckbox']");
				if (rsaCheckedBox.isVisible()){
						if (!rsaCheckedBox.isChecked()) {
							rsaCheckedBox.click();
						}
				}
	        page.fill("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_udpSchema']", udpSchema);
	        page.click("//input[@id='_ParticipantLogon20_WAR_ahcommonauthportlet_logOn']");

	        
	        page.waitForNavigation(() -> {
	            page.click("text=Log On");
	          });

			String HomeUrl=page.url();
			String error_while_login=null;
			System.out.println("Login "+HomeUrl);
			
			if(cookiesPopup.isVisible()){
				cookiesPopup.click();
			}
			
			if(HomeUrl.toLowerCase().contains("home")) {
				
				System.out.println("Login Pass "+HomeUrl);
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Login Successfully with User ID: "+userid+" Password: "+pwd+" Trans 1: "+transid1+" Trans 2: "+transid2+" UDP Schema: "+ udpSchema, testStepRowIndex, 16);

				AutoBoatExecutor.homepgType = pwHomepagetype(page);
				System.out.println("Home Page type: "+AutoBoatExecutor.homepgType);
				
			}
			else {
				
				System.out.println("Login failed "+HomeUrl);
				
				Locator error_while_login_msg = page.locator("//strong[@class='ah-error-message ah-font-m']");
				//WebElement alert=driver.findElement(By.xpath("//strong[@class='ah-error-message ah-font-m']"));
				if(error_while_login_msg.isVisible()) {
				error_while_login=error_while_login_msg.textContent();
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,error_while_login , testStepRowIndex, 16);
				
				System.out.println(error_while_login);
				System.out.println("Login failed "+HomeUrl);
				}

				Locator error_msg_locator = page.locator("//div[@class=\"data-unavailable-message ah-placeholder-content ah-placeholder\"]");
				//WebElement alert=driver.findElement(By.xpath("//strong[@class='ah-error-message ah-font-m']"));
				if(error_msg_locator.isVisible()) {
				String error_msg=error_msg_locator.textContent();
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName,error_msg, testStepRowIndex, 16);
				
				System.out.println(error_msg);
				
				}
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
				
			}}
			// writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS, Time: "+LocalDateTime.now().format(formatter), rowIndex, 14);
			// loginPage = true;
			
//			writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS, Time: "+LocalDateTime.now().format(formatter), rowIndex, 14);
//			loginPage = true;
			
		

		else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}
	}
		
    public static void pwmouseHoverTooltip(Page page, String testSheetName, int testStepRowIndex, String mousehov, String spanxpath, String expectedResult)
    {
    	String verifyText;
		
		Locator xpathLocator = page.locator(""+mousehov+"");
		if(xpathLocator.isVisible()){
		 xpathLocator.hover();

			Locator tooltip = page.locator(""+spanxpath+"");
			if(tooltip.isVisible()){
				verifyText = tooltip.textContent();
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, verifyText, testStepRowIndex, 16);

			if(AbCommonFiles.compareString(expectedResult, verifyText)){
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			}else{
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			}
		
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Does not exists" , testStepRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	 
    }
		
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Does not exists" , testStepRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	 
    }
    }
    
    public static void pwVerifyinputtext(Page page, String testSheetName, int testStepRowIndex, String verifyByXpath, String expectedResult)
    {
    	Locator xpathLocator = page.locator(""+verifyByXpath+"");
    	String verifyText= xpathLocator.textContent();

    	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, verifyText, testStepRowIndex, 16); 
    	if(AbCommonFiles.compareString(expectedResult, verifyText)){
    		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
    	}else{
    		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17); } }

	public static void pwWaitForXpath(Page page, String xpath) {
		// TODO Auto-generated method stub
		
		page.waitForNavigation(() -> {
            page.click(""+xpath+"");
          });
		
	}

	public static void pwCloseFlyout(Page page, String testSheetName, int testStepRowIndex){
		boolean clicked = false;
		Locator flyoutXpath = page.locator("//*[@id='alFlyout']");
		if (flyoutXpath.isVisible()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			JavascriptExecutor js = (JavascriptExecutor) page;
			js.executeScript("window.document.getElementById('alDialogCloseBtn').click()");
			System.out.println("GMC Flyout Closed");
			clicked=true;

		} else {
			if (flyoutXpath.isVisible()) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				JavascriptExecutor js = (JavascriptExecutor) page;
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

	public static void pwPItableTextCheck(Page page, String testSheetName, int testStepRowIndex, String tableXpathProp, String tableXpathValue, String tdXpathProp, String tdXpathValue,  String rowNum, String ColNum, String expectedText){
		
		int rowNumInt= Integer.parseInt(rowNum);
		int ColNumInt= Integer.parseInt(ColNum);
		
		String cellText = "";
		
		Locator tableXpath = page.locator("//*[contains(@"+tableXpathProp+",'"+tableXpathValue+"')]//tr["+rowNumInt+"][@"+tdXpathProp+"='"+tdXpathValue+"']/td["+ColNumInt+"]/p");
		if(tableXpath.isVisible()){
			cellText = tableXpath.textContent();
		}
		// String cellText = driver.findElement(By.xpath("//*[contains(@id,'_addressinquiryportlet_WAR_')]//tr["+rowNumInt+"][@class='readmessage']/td["+ColNumInt+"]/p")).getText();
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, cellText, testStepRowIndex, 16);
		

		if(AbCommonFiles.compareString(expectedText, cellText)){
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
		
	  }
	}

	public static void pwTableTextCheck(Page page, String testSheetName, int testStepRowIndex, String tableXpathProp, String tableXpathValue, String rowNum, String ColNum, String expectedText){
		String cellText="";
		int rowNumInt= Integer.parseInt(rowNum);
		int ColNumInt= Integer.parseInt(ColNum);
		////*[contains(@id,'_personalinfotabcontainerportlet_WAR_')]//tr[1]/td[1]

		Locator tableXpath = page.locator("//*[contains(@"+tableXpathProp+",'"+tableXpathValue+"')]//tr["+rowNumInt+"]/td["+ColNumInt+"]");
		if(tableXpath.isVisible()){
			cellText = tableXpath.textContent();
		}
		
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, cellText, testStepRowIndex, 16);
		
		if(AbCommonFiles.compareString(expectedText, cellText)){
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}
	}
	
	public static void pwClickByXpath(Page page, String testSheetName, int testStepRowIndex, String clkByXpath){
		
		//driver.findElement(By.xpath("//li[contains(text(),'Personal Details')]")).click();
		
		Locator xpathLocator = page.locator(""+clkByXpath+"");
		if(xpathLocator.isVisible()){
			xpathLocator.click();
			
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Clicked" , testStepRowIndex, 16);
		
		System.out.println("Clicked "+clkByXpath);
		}else{
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Not clicked" , testStepRowIndex, 16);
			
		}
		
/*		if(driver.findElement(By.xpath(""+clkByXpath+"")).isDisplayed()){
			driver.findElement(By.xpath(""+clkByXpath+"")).click();
		writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
		}else{
			writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			
		}*/
	}
	
	public static void pwVerifyByXpath(Page page, String testSheetName, int testStepRowIndex, String verifyByXpath, String expectedResult)
	{
		String verifyText;
		//if(isElementPresent(driver,By.xpath(""+verifyByXpath+""),5)){
		//String verifyText = driver.findElement(By.xpath(""+verifyByXpath+"")).getText();
		
		Locator xpathLocator = page.locator(""+verifyByXpath+"");
		if(xpathLocator.isVisible()){
			verifyText = xpathLocator.textContent();
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

	public static void pwTagHeading2(Page page, String testSheetName, int testStepRowIndex, String h2text, String expectedResult)
	{
		String tagH2="";
		Locator xtagH2Locator = page.locator("//h2[contains(text(),'"+h2text+"')]");
		if(xtagH2Locator.isVisible()){
		tagH2 = xtagH2Locator.textContent();
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tagH2, testStepRowIndex, 16);
		}
			if(AbCommonFiles.compareString(expectedResult, tagH2)){
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			}else{
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			}
		
	}
	
	public static void pwTagHeading3(Page page, String testSheetName, int testStepRowIndex, String h3text, String expectedResult)
	{
		String tagH3="";
		Locator xtagH3Locator = page.locator("//h3[contains(text(),'"+h3text+"')]");
		if(xtagH3Locator.isVisible()){
		tagH3 = xtagH3Locator.textContent();
		AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, tagH3, testStepRowIndex, 16);
		}
			if(AbCommonFiles.compareString(expectedResult, tagH3)){
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "PASS", testStepRowIndex, 17);
			}else{
				AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "FAIL", testStepRowIndex, 17);
			}
		
	}
	
	public static void pwmouseHover(Page page, String testSheetName, int testStepRowIndex, String mousehov, String expectedResult) {

		//String verifyText;
		//if(isElementPresent(driver,By.xpath(""+verifyByXpath+""),5)){
		//String verifyText = driver.findElement(By.xpath(""+verifyByXpath+"")).getText();
		
		Locator xpathLocator = page.locator(""+mousehov+"");
		if(xpathLocator.isVisible()){
		 xpathLocator.hover();
		 AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Selected", testStepRowIndex, 16);
		

			if(AbCommonFiles.compareString(expectedResult, "Selected")){
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
	
	
}
