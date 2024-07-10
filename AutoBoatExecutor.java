

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import Playwrightbasic.AbCommonFiles;



public class AutoBoatExecutor {
	
	
	static DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	static DateFormat timeFormat = new SimpleDateFormat("HH_mm_ss");
	static Date date = new Date();
	static String dateWithFormat;
	static String timeWithFormat;
	static String excelFilePath = "Premier_Regression_Testing.xlsm";
	static String htmlPath;
	static String executedBy;
	static String screenshotpath;
	static String[] testSheet= new String[40];
	static int masterRowCount;
	static String masterSheetName = "Master Sheet";
	static String GenerateReportFlag;
	static String browserName;
	static String emailAddress;
	static String homepgType=null;
	static String clearResultsVBS = "Clear_Results.vbs";
	//static String scriptType1; 
	static String scriptType;


	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, InterruptedException {
		// TODO Auto-generated method stub
	
		//Date & Time format
		dateWithFormat = dateFormat.format(date);
		timeWithFormat = timeFormat.format(date);

		browserName = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,2,1);
		GenerateReportFlag = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,2,2);
		emailAddress = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,2,3);
		executedBy = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,2,4);
		scriptType= AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,4,1);
		scriptType=scriptType.toLowerCase();
		System.out.println("Script Value "+scriptType);
		screenshotpath = System.getProperty("user.dir") + "/Screenshots";
		
	//	htmlPath = System.getProperty("user.dir") + "/Premier_Regression_HTML_Reports/Date_"+dateWithFormat+"_Time_"+timeWithFormat+".html";
		
		htmlPath = System.getProperty("user.dir")+"/TestReports/";
		System.out.println(htmlPath);
		
		//Count the row of Master Sheet tab
		masterRowCount = AbCommonFiles.getExcelSheetPhysicalRowCount(excelFilePath, masterSheetName);

		if (GenerateReportFlag.toLowerCase().equals("yes") || GenerateReportFlag.toLowerCase().equals("y")) {
		TCSheetExecute();
		sendEmail();
		} else if (GenerateReportFlag.toLowerCase().equals("sesr")) {
			sendEmail();
		} else {
			TCSheetExecute();
		}
		
		String testSheetName ="";
		
	} // End of main function

	public static void TCSheetExecute() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, InterruptedException{
		for (int masterRowIndex = 7; masterRowIndex <= masterRowCount; masterRowIndex++) {
			String yesNoStatusMaster = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName, 0, masterRowIndex);
			if (yesNoStatusMaster.toLowerCase().equals("yes") || yesNoStatusMaster.toLowerCase().equals("y")) {
				String testSheetName = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,1, masterRowIndex);
				
						System.out.println("Execution of "+testSheetName+" Flow started");
						AbCMFlowCodeFiles.TCSheetExecutor(excelFilePath, screenshotpath, browserName, testSheetName);

						System.out.println("Execution of "+testSheetName+" Flow Completed");
						
						
			}
			
			
		} // End of for loop
	}
	
	public static void sendEmail(){


			for(int rowNo=7; rowNo<=masterRowCount; rowNo++){
				String yesNoStatusMaster = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName, 0, rowNo);
				if (yesNoStatusMaster.toLowerCase().equals("yes") || yesNoStatusMaster.toLowerCase().equals("y")) {
					String testSheetName = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,1, rowNo);
				//	String testSheetName_Desc = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,2, i);
					testSheet[rowNo-1]=testSheetName;
					System.out.println(testSheet[rowNo-1]);
					// Create HTML Report
		
					System.out.println("Execution of "+testSheetName+" Create HTML Report - Started at Date"+dateWithFormat+"_Time_"+timeWithFormat);
					List<List<String>> CMExecutedFlow = AbCommonFiles.allRow(excelFilePath, testSheetName, 17);
				//	AbCommonFiles.premierSendMail(excelFilePath, emailAddress, CMPersonalInfonExecutedFlow, testSheetName, testSheetName_Desc, executedBy);
					AbCommonFiles.temppremierSendMail(excelFilePath, emailAddress, CMExecutedFlow, testSheetName, rowNo);
					System.out.println("Test Sheet "+testSheetName+" Create HTML Report - End at Date"+dateWithFormat+"_Time_"+timeWithFormat);
					
					}
			}
	
		try {
			AbCommonFiles.tempsendHtmlEmail("allwyn.lobo@alight.com", emailAddress, "Regression ", createMsg());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public static String createMsg(){
		
		//String msgContent;
		//msgContent="<i>Hi All,</i><br> <b>Premier Regression Testing Execution Results </b><br> ";
		
		StringBuilder str = new StringBuilder();
		str.append("<i>Hi All,</i><br>");
		str.append("<br>");
		str.append("<b>Premier Regression Testing Execution Results </b><br>");
		str.append("<html><head>");
	/*	str.append(
				"<style>table {font-family: arial, sans-serif;border-collapse: collapse;width: 50%;}td, th { border: 1px solid #dddddd;text-align: left; padding: 8px;}tr:nth-child(even) { background-color: #dddddd;}</style></head>");
	*/	str.append("<body>");

		str.append(
				"<table width='1200px' align='left' border='2' cellpadding='0' cellspacing='0' style='border-top:2px solid white;'");
		
		str.append("<tr><th>S.No.</th><th>Flow Name</th><th>Description</th><th>Total No. of Test Cases Executed</th><th>Count of Pass</th><th>Count of Fail</th></tr>");
		//int i = 1;

		
		for(int j=7; j<=masterRowCount; j++){
			String yesNoStatusMaster = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName, 0, j);
			if (yesNoStatusMaster.toLowerCase().equals("yes") || yesNoStatusMaster.toLowerCase().equals("y")) {
				String testSheetName = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,1, j);
				String testDesc = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,2, j);
				String total= AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,3, j);
				String pass= AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,4, j);
				String fail = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, masterSheetName,5, j);
				str.append(
						"<tr><td><b>" + (j-6) + "</b></td><td>" + testSheetName+ "</td><td>"+testDesc+"</td><td>"+total+"</td><td>"+pass+"</td><td>"+fail+"</td></tr>");
				}
		}
		
		str.append("</table>");
		str.append("<br>");
		str.append("<br />");
		str.append("Executed By <b>"+executedBy+"</b>");
		str.append("<br />");
		str.append("<b>Thanks!</b>");

		str.append("</body></html>");
		
		
		String singleString = str.toString();
		return singleString;
	}
	
}// End of AutoBoatExecutor class

