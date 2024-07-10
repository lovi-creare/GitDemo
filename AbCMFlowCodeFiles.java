

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import Playwrightbasic.AbCommonFiles;

public class AbCMFlowCodeFiles{
	
	public static void TCSheetExecutor(String excelFilePath, String screenshotpath, String browserName, String testSheetName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, InterruptedException {
		
		int tempno;
		File file = new File(excelFilePath);

		LocalDateTime dateTime = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		
		WebDriver driver = null;
		Browser browser = null;
		Playwright playwright = null;
		Page page = null;
		//AbCommonFiles AbCommonFilesobj = new AbCommonFiles();	
		


		
		//Count the rows of the sheet
		int sheetRowCount = AbCommonFiles.getExcelSheetPhysicalRowCount(excelFilePath, testSheetName);
		
		String keywordName;
		String keywordParm1;
		String keywordParm2;
		String keywordParm3;
		String keywordParm4;
		String keywordParm5;
		String keywordParm6;
		String keywordParm7;
		String keywordParm8;
		String keywordParm10;
		String expectedResult;
		
		//Delete existing ActualResults & TestResults
	//	String cmd = AutoBoatExecutor.clearResultsVBS;
	//	Runtime.getRuntime().exec(cmd);
	//	Runtime.getRuntime().exec("cmd /c Clear_Results.vbs");
	//	Thread.sleep(1000);
		
/*		if (AutoBoatExecutor.GenerateReportFlag.toLowerCase().equals("yes") || AutoBoatExecutor.GenerateReportFlag.toLowerCase().equals("y")) {
			System.out.println("Existing Actual Results & Status deletion started");
			for (int delRowIndex = 2; delRowIndex <= sheetRowCount; delRowIndex++) {

			AbCommonFiles.writeExpressionDataOnExistingExcel(excelFilePath, testSheetName, "", delRowIndex, 16);
			AbCommonFiles.writeExpressionDataOnExistingExcel(excelFilePath, testSheetName, "", delRowIndex, 17);
			}
			System.out.println("Deleted existing Actual Results & Status from Sheet");	
		
		}
		*/

		if(AutoBoatExecutor.scriptType.equals("selenium")){
		// Launch Browser
			try {
				System.out.println("Selenium Launch Browser");
				driver = AbCommonFiles.getbrowserInstance(browserName);
				driver.manage().window().maximize();
				
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		else{
		
			try {
				System.out.println("Playwright Launch Browser");
	
		        playwright = Playwright.create();
		        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		        page = browser.newPage();
		        
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		
		}
		
		//Test Cases For loop
		for (int tcRowIndex = 2; tcRowIndex <= sheetRowCount; tcRowIndex++) {
		
			
			String yesNoStatusTC = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName,
					0, tcRowIndex);
			if (yesNoStatusTC.toLowerCase().equals("yes") || yesNoStatusTC.toLowerCase().equals("y")) {
				
				
				//Test Steps loop
				for (int testStepRowIndex = tcRowIndex; testStepRowIndex <= sheetRowCount; testStepRowIndex++) {
					try{
						
					String yesNoStatusTestStep = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName,
							1, testStepRowIndex);
					if (yesNoStatusTestStep.toLowerCase().equals("yes") || yesNoStatusTestStep.toLowerCase().equals("y")) {
						
						
						
						// Execute Test Steps
						 keywordName = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 4, testStepRowIndex);
						 keywordParm1 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 5, testStepRowIndex);						
						 keywordParm2 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 6, testStepRowIndex);
						 keywordParm3 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 7, testStepRowIndex);
						 keywordParm4 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 8, testStepRowIndex);
						 keywordParm5 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 9, testStepRowIndex);
						 keywordParm6 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 10, testStepRowIndex);
						 keywordParm7 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 11, testStepRowIndex);
						 keywordParm8 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 12, testStepRowIndex);
						 keywordParm10 = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 14, testStepRowIndex);
						 expectedResult = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, testSheetName, 15, testStepRowIndex);
						//Method m = AbCommonFilesobj.getClass().getMethod(keywordName);
						//m.invoke(AbCommonFilesobj);
						tempno = testStepRowIndex+1;
						 System.out.println("Execution of row no "+tempno+" started for keyword "+keywordName);
						switch(keywordName.toLowerCase()){
						
						case "loginupoint":
							if(AutoBoatExecutor.scriptType.equals("selenium")){
								AbSeleniumFunctions.LoginUpoint(driver, browserName, keywordParm1, keywordParm2, keywordParm3, keywordParm4,
									keywordParm5, keywordParm6, keywordParm7, keywordParm8, keywordParm10, testSheetName, testStepRowIndex);
							} else{
								AbPlaywrightFunctions.pwLoginUpoint(page, browserName, keywordParm1, keywordParm2, keywordParm3, keywordParm4,
										keywordParm5, keywordParm6, keywordParm7, keywordParm8, keywordParm10, testSheetName, testStepRowIndex);
							}
						//	keywordName="";
							break;
							
						case "waitforxpath":
							if(AutoBoatExecutor.scriptType.equals("selenium")){
										
								} else{
									AbPlaywrightFunctions.pwWaitForXpath(page, keywordParm1);
								}
							break;
								
							case "closeflyout":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.closeFlyout(driver, testSheetName, testStepRowIndex);
								} else{
									AbPlaywrightFunctions.pwCloseFlyout(page, testSheetName, testStepRowIndex);
								}
								break;
								
							case "clkheaderLink":
								AbSeleniumFunctions.clkheaderLink(driver, testSheetName, testStepRowIndex, keywordParm1, keywordParm2);
								
								break;
								
							case "waitseconds":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.waitSeconds(driver, keywordParm1);
								} else {
									
								}
								break;
								
							case "pitabletextcheck":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.PItableTextCheck(driver, testSheetName, testStepRowIndex, keywordParm1, keywordParm2,  keywordParm3, keywordParm4,  keywordParm5, keywordParm6, expectedResult);
								}else {
									AbPlaywrightFunctions.pwPItableTextCheck(page, testSheetName, testStepRowIndex, keywordParm1, keywordParm2,  keywordParm3, keywordParm4,  keywordParm5, keywordParm6, expectedResult);
								}
								break;
								
							case "tabletextcheck":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.tableTextCheck(driver, testSheetName, testStepRowIndex, keywordParm1, keywordParm2,  keywordParm3, keywordParm4, expectedResult);
								}else {
									AbPlaywrightFunctions.pwTableTextCheck(page, testSheetName, testStepRowIndex, keywordParm1, keywordParm2,  keywordParm3, keywordParm4, expectedResult);
								}
								break;
								
							case "clickbyxpath":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.clickByXpath(driver, testSheetName, testStepRowIndex, keywordParm1);
								}else {
									AbPlaywrightFunctions.pwClickByXpath(page, testSheetName, testStepRowIndex, keywordParm1);
								}
								
								break;
								
							case "verifybyxpath":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.VerifyByXpath(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								} else {
									AbPlaywrightFunctions.pwVerifyByXpath(page, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								}
								break;
							
							case "capturescreenshot":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
								AbCommonFiles.captureScreenshot(driver, screenshotpath, testSheetName, keywordParm1);
								} else{
									AbPlaywrightFunctions.pwCaptureScreenshot(page, screenshotpath, testSheetName, keywordParm1);
								}
								break;
								
								
							case "tagheading2":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.tagHeading2(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								} else{
									AbPlaywrightFunctions.pwTagHeading2(page, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								}
								break;

							case "tagheading3":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.tagHeading3(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
									} else{
										AbPlaywrightFunctions.pwTagHeading3(page, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
									}
								break;
								
							case "switchtoiframebyxpath":
								AbSeleniumFunctions.switchToiframeByXpath(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								break;
								
							case "switchToiframebyindex":
								AbSeleniumFunctions.switchToiframeByIndex(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								break;
								
							case "switchtoparentwindow":
								AbSeleniumFunctions.switchToParentWindow(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								break;
								
							case "verifylink":
								AbSeleniumFunctions.verifyLink(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								break;
							
							case "loginurl":
								AbSeleniumFunctions.LoginURL(browserName, keywordParm1, driver, testSheetName, testStepRowIndex);
							//	keywordName="";
								break;
								
							case "switchWindowByTitleName":
								AbSeleniumFunctions.switchWindowByTitleName(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								break;
								
							case "executebyjscommand":
								AbSeleniumFunctions.ExecuteByJScommand(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
							break;
								
							//Jyoti
							
							case "tagheading1":
								AbSeleniumFunctions.tagHeading1(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
					              break; 
					              
							case "clknavigation":
								AbSeleniumFunctions.clkNavigation(driver, testSheetName, testStepRowIndex, keywordParm1, keywordParm2, keywordParm3);
					              break;
					              
							case "scrollslider":
								AbSeleniumFunctions.Scrollslider(driver, testSheetName, testStepRowIndex, keywordParm1,keywordParm2, keywordParm3, expectedResult);
								break;

							case "mousehover":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.mouseHover(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								}
								else {
									AbPlaywrightFunctions.pwmouseHover(page, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
								}
								break;
								
							case "switchtab1":
								AbSeleniumFunctions.SwitchTab1(driver, testSheetName, testStepRowIndex, expectedResult);
								break;

								// Neha's Functions

							case "waitexplicit":
								AbSeleniumFunctions.waitExplicit(driver, keywordParm1, keywordParm2);
								break;

							case "scrolldown":
								AbSeleniumFunctions.ScrollDown(driver);
								break;

							case "bene_selectindex":
								AbSeleniumFunctions.bene_selectIndex(driver, keywordParm1, keywordParm2, keywordParm3);
								break;

							case "textinput":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.textInput(driver, testSheetName, testStepRowIndex, keywordParm1,keywordParm2);
								}
								else {
									AbPlaywrightFunctions.pwtextInput(page, testSheetName, testStepRowIndex, keywordParm1,keywordParm2);
								}
								break;

							case "bene_selectvalue":
								AbSeleniumFunctions.bene_selectValue(driver, keywordParm1, keywordParm2);
								break;

							case "tablecheck":
								AbSeleniumFunctions.tableCheck(driver, testSheetName, testStepRowIndex, keywordParm1,
										keywordParm2, keywordParm3, keywordParm4);
								break;

							case "handling_dropdown":
								AbSeleniumFunctions.Handling_dropdown(driver, browserName, keywordParm1, keywordParm2,
										keywordParm3);
								break;

							case "spanheading":
								AbSeleniumFunctions.spanHeading(driver, testSheetName, testStepRowIndex, keywordParm1,
										expectedResult);
								break;

							case "update_info":
								AbSeleniumFunctions.Update_info(driver, browserName, testSheetName, testStepRowIndex,
										keywordParm1, keywordParm2);
								break;
							case "switchwindow":
								AbSeleniumFunctions.SwitchWindow(driver, testSheetName, testStepRowIndex, keywordParm1);
								break;
							
							case "switchtoparenttab":
								AbSeleniumFunctions.SwitchToParentTab(driver, testSheetName, testStepRowIndex, expectedResult);
								break;
							case "switchtochildwindow":
								AbSeleniumFunctions.SwitchToChildWindow(driver, testSheetName, testStepRowIndex,
										keywordParm1);
								break;
								
							case "verifyurl":
								AbSeleniumFunctions.verifyUrl(driver, testSheetName, testStepRowIndex,
										keywordParm1);
								break;
								
							case "verifytitle":
								AbSeleniumFunctions.verifyTitle(driver, testSheetName, testStepRowIndex, keywordParm1);
								break;

							case "browserforwardbutton":
								AbSeleniumFunctions.BrowserForwardbutton(driver, testSheetName, testStepRowIndex);
								break;
								
							case "verifytooltip":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.VerifyTooltip(driver, testSheetName, testStepRowIndex, keywordParm1,
										expectedResult);
								}
								else {
									AbPlaywrightFunctions.pwVerifyTooltip(page, testSheetName, testStepRowIndex, keywordParm1,
											expectedResult);
								}
								break;
								
							case "verifyattribute":								
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.Verifyattribute(driver, testSheetName, testStepRowIndex, keywordParm1, keywordParm2,
										expectedResult);
								}
								else {
									//AbCommonFiles.pwVerifyattribute(page, testSheetName, testStepRowIndex, keywordParm1, keywordParm2,expectedResult);
								}
								break;

					           case "verifydropdown":
					        	   AbSeleniumFunctions.Verifydropdown(driver, testSheetName, testStepRowIndex, keywordParm1,
	                                     expectedResult);
								break;

					           case "verifyautosuggestion":
					        	   AbSeleniumFunctions.Verifyautosuggestion(driver, testSheetName, testStepRowIndex, keywordParm1, keywordParm2, keywordParm3,
										keywordParm4,
	                                    expectedResult);
								break;
							case "checkbold":
								AbSeleniumFunctions.CheckBold(driver, testSheetName, testStepRowIndex, keywordParm1,
										expectedResult);
								break;
								
							case "mousehovertooltip":
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.mouseHoverTooltip(driver, testSheetName, testStepRowIndex, keywordParm1,
										keywordParm2, expectedResult);
								}else{
									AbPlaywrightFunctions.pwmouseHoverTooltip(page, testSheetName, testStepRowIndex, keywordParm1,
											keywordParm2, expectedResult);
								}
								break;
							case "click_cookies":
								AbSeleniumFunctions.Click_Cookies(browserName, keywordParm1, driver);
								break;
							case "switchtoiframebyname":
								AbSeleniumFunctions.switchToiframeByName(driver, testSheetName, testStepRowIndex,
										keywordParm1, expectedResult);
								break;
								
							case "print_button":
								AbSeleniumFunctions.Print_Button(driver);
								break;

							// Allwyn
							case "verifyinputtext":
								
								if(AutoBoatExecutor.scriptType.equals("selenium")){
									AbSeleniumFunctions.Verifyinputtext(driver, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
									}else {
										AbPlaywrightFunctions.pwVerifyinputtext(page, testSheetName, testStepRowIndex, keywordParm1, expectedResult);
									}
								
								break;
							case "clickandhold":
								AbSeleniumFunctions.clickAndHold(driver, testSheetName, testStepRowIndex, keywordParm1,
										expectedResult);
								break;
							case "back":
								AbSeleniumFunctions.back(driver, testSheetName, testStepRowIndex,expectedResult);
								break;
								

							default:
								
								break;
						}
						
						//System.out.println(keywordName+" "+keywordParm1+" "+keywordParm2+" "+keywordParm3+" "+keywordParm4);
						
					} else if (yesNoStatusTestStep.toLowerCase().equals("end") || yesNoStatusTestStep.toLowerCase().equals("e")) 
						break;  // Exit Test Steps For loop if there is 'e' or 'end' in Test Step column.
					
					
					}
					
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
						
					//	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, e.getMessage(), testStepRowIndex, 16);
					//	AbCommonFiles.writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, testSheetName, "Exception Occured", testStepRowIndex, 17);

					}
					
				} // End Test Steps For loop

			}
		
		}// End Test Cases For loop
		
		if(AutoBoatExecutor.scriptType.equals("selenium")){
			driver.quit();
		}else{
		// Send CMPersonalInfo Flow Test Case Email
		browser.close();
        playwright.close();
		}

public void message(){

      system.out.println("End of CM Flows Program");
}

	} // End of CMPersonalInfo Main class

} // End of AbFlowCodeFiles Class

