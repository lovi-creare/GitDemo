

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class AbCommonFiles{

	// Read from Excel
	public static String readColDataBasisonRowIndexFromExcel(String excelPath, String sheetName, int colIndex,
			int rowindex) {
		FileInputStream inputStream = null;
		Workbook workbook = null;
		Sheet sheet;

		String cellVal = "";
		try {
			inputStream = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowindex);
			Cell cell = row.getCell(colIndex);

			CellType type = cell.getCellTypeEnum();

			if (type == CellType.STRING)
				cellVal = cell.getStringCellValue();
			else if (type == CellType.NUMERIC) {
				cellVal = cell.getNumericCellValue() + "";
				cellVal = cellVal.split("\\.")[0];

			}

		} catch (Exception e) {

		} finally {
			try {

				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cellVal;
	}

	   public static boolean waitForClickableElementPresent(WebDriver driver, WebElement elem, long timeInSeconds) {
		      try {
		         WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		         return ((WebElement)wait.until(ExpectedConditions.elementToBeClickable(elem))).isDisplayed();
		      } catch (Exception var5) {
		         return false;
		      }
		   }
	   
	   public static boolean waitForElementPresent(WebDriver driver, By by, long timeInSeconds) {
		      try {
		         WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		         return ((WebElement)wait.until(ExpectedConditions.presenceOfElementLocated(by))).isDisplayed();
		      } catch (Exception var5) {
		         return false;
		      }
		   }
	   
	   
		public static List<List<String>> allRow(String excelFilePath, String sheetName, int collength) {
			List<List<String>> executedFlow = new ArrayList<List<String>>();

			int rowCount = AbCommonFiles.getExcelSheetPhysicalRowCount(excelFilePath, sheetName);
			System.out.println(sheetName+" row Count "+ rowCount);
			for (int rowindex = 2; rowindex < rowCount; rowindex++) {
				List<String> rowListCellValues = new ArrayList<String>();
			//	System.out.println("row index Count "+ rowindex);
				for (int colIndex = 0; colIndex <= collength; colIndex++) {
					String cellValue = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, sheetName, colIndex,
							rowindex);
					rowListCellValues.add(cellValue);

				}


		//		System.out.println(rowListCellValues.get(10));
		//		if (rowListCellValues.get(9).toLowerCase().equals("fail")) {
					executedFlow.add(rowListCellValues);
		//		}

			}
			return executedFlow;
		}
		
		public static List<List<String>> selectedRow(String excelFilePath, String sheetName, int collength) {
			List<List<String>> executedFlow = new ArrayList<List<String>>();

			int rowCount = AbCommonFiles.getExcelSheetPhysicalRowCount(excelFilePath, sheetName);
			for (int rowindex = 2; rowindex < rowCount; rowindex++) {
				List<String> rowListCellValues = new ArrayList<String>();

				for (int colIndex = 0; colIndex <= collength; colIndex++) {
					String cellFlagValue = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, sheetName, 0,
							rowindex);
					if(cellFlagValue.toLowerCase().equals("yes") || cellFlagValue.toLowerCase().equals("y")){
					String cellValue = AbCommonFiles.readColDataBasisonRowIndexFromExcel(excelFilePath, sheetName, colIndex,
							rowindex);
					rowListCellValues.add(cellValue);
					}
				}

		//		System.out.println(rowListCellValues.get(9));
		//		System.out.println(rowListCellValues.get(10));
		//		if (rowListCellValues.get(9).toLowerCase().equals("fail")) {
					executedFlow.add(rowListCellValues);
		//		}

			}
			return executedFlow;
		}

	   public static boolean chooseOrgFunctionality(WebDriver driver, String orgname) {
		      boolean status = false;
		      waitForClickableElementPresent(driver, driver.findElement(By.xpath("//strong")), 80L);
		      driver.findElement(By.xpath("//strong")).click();

		      try {
		         Thread.sleep(2000L);
		         driver.findElement(By.xpath("//*[@class='lfr-menu-list-search-container']/input")).sendKeys(new CharSequence[]{orgname});
		         Thread.sleep(2000L);
		         driver.findElement(By.xpath("//*[@aria-labelledby='_160_groupSelectorButton']//li[not(@class='aui-helper-hidden')]")).click();
		         waitForClickableElementPresent(driver, driver.findElement(By.xpath("//div[@id='panel-manage-content']//ul[@class='category-portlets']/li")), 80L);
		         status = true;
		      } catch (Exception var7) {
		         List<WebElement> elemList = driver.findElements(By.xpath("//div[@class='lfr-component lfr-menu-list']//ul//li/a"));
		         driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		         Iterator var6 = elemList.iterator();

		         WebElement elem;
		         do {
		            if (!var6.hasNext()) {
		               return status;
		            }

		            elem = (WebElement)var6.next();
		            System.out.println(elem.getText().toLowerCase().contains(orgname.toLowerCase()) || elem.getAttribute("innerText").toLowerCase().contains(orgname.toLowerCase()));
		         } while(!elem.getText().toLowerCase().contains(orgname.toLowerCase()) && !elem.getAttribute("innerText").toLowerCase().contains(orgname.toLowerCase()));

		         ((JavascriptExecutor)driver).executeScript("arguments[0].click();", new Object[]{elem});
		         status = true;
		      }

		      return status;
		   }
	   
	public static int readColDataFromExcel(String excelPath, String sheetName, int colIndex) {
		FileInputStream inputStream = null;
		Workbook workbook = null;
		Sheet sheet;
		int failureCount = 0;
		String cellVal = "";
		try {
			inputStream = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			int existRowCount = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < existRowCount; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(colIndex);
				cellVal = cell.getStringCellValue();
				System.out.println("cellVal "+cellVal);
				if (cellVal.toLowerCase().contains("fail")) {

					failureCount = failureCount + 1;
					System.out.println("failureCount "+failureCount);
				}

			}

		} catch (Exception e) {

		} finally {
			try {

				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return failureCount;
	}
	

	public static boolean switchWindowByTitle(WebDriver driver, String windowTitle) {
		boolean status = false;

		int count = 0;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (count <= 40) {
			Set<String> allWindows = driver.getWindowHandles();
			System.out.println(allWindows.size());
			for (String content : allWindows) {
				driver.switchTo().window(content);
				if (driver.getTitle() == null || driver.getTitle().length() <= 1) {
					System.out.println("Title is null");
					status = true;
					break;
				}
				System.out.println("Window Title: " + driver.getTitle());
				if (driver.getTitle().contains(windowTitle)) {
					status = true;
					break;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = count + 1;
			if (status) {
				break;
			}
		}
		return status;

	}

	public static int getExcelSheetPhysicalRowCount(String excelPath, String sheetName) {
		FileInputStream inputStream = null;
		Workbook workbook = null;
		Sheet sheet;
		int existRowCount = 0;
		try {
			inputStream = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			existRowCount = sheet.getPhysicalNumberOfRows();

		} catch (Exception e) {

		} finally {
			try {

				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return existRowCount;
	}

	public static int getExcelSheetPhysicalColumnCount(String excelPath, String sheetName) {
		FileInputStream inputStream = null;
		Workbook workbook = null;
		Sheet sheet;
		int existColumnCount = 0;
		try {
			inputStream = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
			existColumnCount = sheet.getRow(0).getPhysicalNumberOfCells();

		} catch (Exception e) {

		} finally {
			try {

				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return existColumnCount;
	}

	public static List<String> getPhysicalSheetsName(String excelPath) {
		FileInputStream inputStream = null;
		Workbook workbook = null;

		List<String> sheetNames = new ArrayList<String>();
		try {
			inputStream = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(inputStream);

			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				sheetNames.add(workbook.getSheetName(i));
			}

		} catch (Exception e) {

		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sheetNames;
	}

	public final static int BUF_SIZE = 1024; // can be much bigger, see comment
												// below

	public static void copyFile(String fileName) {
	//	String srcLoc = "D:\\FunctionalTestScripts\\SmokeTestInputs\\" + fileName;
	//	String srcLoc = "C:\\Automation\\" + fileName;
		String srcLoc = System.getProperty("user.dir") + "\\"  + fileName;
		
		
		String DestinationLocation = System.getProperty("user.dir") + "\\Results_" + fileName;
		File file = new File(DestinationLocation);
		if (file.exists())
			file.delete();
		try {
			FileUtils.copyFile(new File(srcLoc), file, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFile(File in, File out) throws Exception {
		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[BUF_SIZE];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}
	}
	
	

	public static void captureScreenshot(WebDriver driver, String srcFile, String sheetName, String srcName) {
		try {
			
			srcFile = srcFile+"/"+sheetName+"/"+srcName+"_Date_"+AutoBoatExecutor.dateWithFormat+"_Time_"+AutoBoatExecutor.timeWithFormat+".png";
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
					.takeScreenshot(driver);
			final BufferedImage image = screenshot.getImage();
			File imagePath = new File(srcFile);
			File parentDir = imagePath.getParentFile();
			if (parentDir != null && !parentDir.exists()) {
				if (!parentDir.mkdirs()) {
					throw new IOException("error creating directories");
				}
			}

			ImageIO.write(image, "PNG", imagePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public static ArrayList<String> getXpathTextInArrayList(WebDriver driver, String xpath) {

		ArrayList<String> textList = new ArrayList<String>();
		try {
			List<WebElement> listElmtsList = driver.findElements(By.xpath(xpath));

			for (WebElement elem : listElmtsList) {

				if ((elem.getAttribute("innerText").trim().length() > 0
						|| elem.getAttribute("innerHTML").trim().length() > 0)
						&& (!elem.getAttribute("innerText").trim().contains("Debug Link"))
						&& (!elem.getAttribute("innerText").trim().contains("AUI().use("))
						&& (!elem.getAttribute("innerText").trim().contains("Home")))
					if (elem.getAttribute("innerText").trim().length() > 0)
						textList.add(elem.getAttribute("innerText").trim());
					else
						textList.add(elem.getAttribute("innerHTML").trim());

			}

			Object[] st = textList.toArray();
			for (Object s : st) {
				if (textList.indexOf(s) != textList.lastIndexOf(s)) {
					textList.remove(textList.lastIndexOf(s));
				}
			}

			Collections.sort(textList, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s1.compareToIgnoreCase(s2);
				}
			});
		} catch (Exception e) {
		}

		return textList;

	}
	
	

	public static List<WebElement> getWebElementsList(WebDriver driver, String xpath) {
		List<WebElement> listElmtsList = driver.findElements(By.xpath(xpath));

		return listElmtsList;

	}

	public static void writeExpressionDataOnExistingExcel(String excelFilePath, String sheetName, String value,
			int rowIndex, int colIndex) {

		FileInputStream inputStream;
		Workbook workbook = null;
		Sheet sheet;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			Cell cell = row.createCell(colIndex);
			cell.setCellValue(rowIndex);
			if (value.toLowerCase().equals("pass")) {
				CellStyle backgroundStyle = workbook.createCellStyle();
				backgroundStyle.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
				//backgroundStyle.setFillPattern(CellStyle.ALIGN_CENTER);
				cell.setCellValue("PASS");
				cell.setCellStyle(backgroundStyle);
			} else if (value.toLowerCase().equals("fail")) {
				CellStyle backgroundStyle = workbook.createCellStyle();
				backgroundStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			//	backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellValue("FAIL");
				cell.setCellStyle(backgroundStyle);
			} else if (value.toLowerCase().contains("exception")) {
				CellStyle backgroundStyle = workbook.createCellStyle();
				backgroundStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
			//	backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellValue(value);
				cell.setCellStyle(backgroundStyle);
			} else {
				CellStyle backgroundStyle = workbook.createCellStyle();
				backgroundStyle.setFillBackgroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			//	backgroundStyle.setFillPattern(CellStyle.ALIGN_CENTER);
				cell.setCellValue(value);
				cell.setCellStyle(backgroundStyle);

			}

			inputStream.close();
		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			FileOutputStream outputStream;
			try {
				outputStream = new FileOutputStream(excelFilePath);
				workbook.write(outputStream);
				workbook.close();
				outputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public static void sendHtmlEmail(String mailFrom, String toAddress, String message, String subject)
			throws AddressException, MessagingException {
		String host = "smtprelay.hewitt.com";
		// sets SMTP server properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// creates a new session with an authenticator
		Session session = Session.getDefaultInstance(properties);
		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(mailFrom));
		String[] str = toAddress.split(",");
		for (int j = 0; j < str.length; j++) {
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(str[j]));
		}

		DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

		TimeZone timeZone = TimeZone.getTimeZone("IST");
		sdf.setTimeZone(timeZone);

		Date date = new Date();
		msg.setSubject(subject + " - Testing Results - Date:" + sdf.format(date) + " IST");
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(message, "text/html");

		
		/* 
		Multipart multipart = new MimeMultipart();



        MimeBodyPart attachmentBodyPart= new MimeBodyPart();
        DataSource source = new FileDataSource(AutoBoatExecutor.htmlPath+"/temp.html"); // ex : "C:\\test.pdf"
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName("temp.html"); // ex : "test.pdf"
        multipart.addBodyPart(attachmentBodyPart); // add the attachement part


       DataSource source1 = new FileDataSource(AutoBoatExecutor.htmlPath+"/temp1.html"); // ex : "C:\\test.pdf"
        attachmentBodyPart.setDataHandler(new DataHandler(source1));
        attachmentBodyPart.setFileName("temp1.html"); // ex : "test.pdf"
        multipart.addBodyPart(attachmentBodyPart); // add the attachement part

        DataSource source2 = new FileDataSource(AutoBoatExecutor.htmlPath+"/temp2.html"); // ex : "C:\\test.pdf"
        attachmentBodyPart.setDataHandler(new DataHandler(source2));
        attachmentBodyPart.setFileName("temp2.html"); // ex : "test.pdf"
        multipart.addBodyPart(attachmentBodyPart); // add the attachement part
       
        
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(message, "text/html");
        

        
        multipart.addBodyPart(textBodyPart);  // add the text part

        msg.setContent(multipart);

		 */
		
		// sends the e-mail
		Transport.send(msg);

	}


	public static void tempsendHtmlEmail(String mailFrom, String toAddress, String subject, String msgContentMain)
			throws AddressException, MessagingException {
		String host = "smtprelay.hewitt.com";
		// sets SMTP server properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// creates a new session with an authenticator
		Session session = Session.getDefaultInstance(properties);
		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(mailFrom));
		String[] str = toAddress.split(",");
		for (int j = 0; j < str.length; j++) {
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(str[j]));
		}

		DateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

		TimeZone timeZone = TimeZone.getTimeZone("IST");
		sdf.setTimeZone(timeZone);

		Date date = new Date();
		msg.setSubject(subject + " - Testing Results - Date:" + sdf.format(date) + " IST");
		msg.setSentDate(new Date());
		// set plain text message
	//	msg.setContent(message, "text/html");

		
	 
		Multipart multipart = new MimeMultipart();
		String testSheetName;

		for (int tsNum=0; tsNum<AutoBoatExecutor.testSheet.length; tsNum++){
			if(AutoBoatExecutor.testSheet[tsNum]!=null){
				testSheetName=AutoBoatExecutor.testSheet[tsNum];

		        //Create 1st attachment Multipart
		        MimeBodyPart messageBodyPart = new MimeBodyPart(); 
		       // DataSource source = new FileDataSource("D:\\Users\\AH0109598\\Eclipse\\workspace\\AutoBoat\\TestReports\\"+testSheetName+".html");
		        DataSource source = new FileDataSource(AutoBoatExecutor.htmlPath+testSheetName+".html");
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(testSheetName+".html");
		        
		        multipart.addBodyPart(messageBodyPart);
			}
	
	
		}

/*
        MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 
        DataSource source2 = new FileDataSource("D:\\Users\\AH0109598\\Eclipse\\workspace\\AutoBoat\\temp2.html");
        messageBodyPart2.setDataHandler(new DataHandler(source2));
        messageBodyPart2.setFileName("tem2.html");
        
        multipart.addBodyPart(messageBodyPart2);

        MimeBodyPart attachmentBodyPart= new MimeBodyPart();
        DataSource source = new FileDataSource("D:\\Users\\AH0109598\\Eclipse\\workspace\\AutoBoat\\temp4.html"); // ex : "C:\\test.pdf"
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName("temp4.html"); // ex : "test.pdf"

        multipart.addBodyPart(attachmentBodyPart); // add the attachement part
     */


        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setContent(msgContentMain, "text/html");
        

        
        multipart.addBodyPart(textBodyPart);  // add the text part

        msg.setContent(multipart);

		
		
		// sends the e-mail
		Transport.send(msg);

	}

	public static void deleteFiles(String dir) {
		File folder = new File(dir);
		if (!folder.exists())
			folder.mkdir();
		File fList[] = folder.listFiles();
		// Searchs .lck
		for (int i = 0; i < fList.length; i++) {
			File pes = fList[i];
			if (pes.getName().contains(".png")) {
				pes.delete();
			}
		}
	}

	public static String getDecryptedVal(String entrycryptedVal) {
		int intOrigStringLen = entrycryptedVal.length() / 4;
		String Decrypt_password = entrycryptedVal.substring(0, intOrigStringLen);
		String OriginalString = "";
		for (int j1 = 1; j1 <= intOrigStringLen; j1++) {
			int intAsciiVal = Decrypt_password.substring(0, 1).charAt(0) - 5;
			OriginalString = OriginalString + String.valueOf((char) intAsciiVal);
			Decrypt_password = Decrypt_password.substring(1);
		}

		return OriginalString;
	}

	public static String getdateFromFile() {
		BufferedReader br = null;
		FileReader fr = null;
		String dateWithFormat = null;
		try {
			fr = new FileReader("date.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader("date.txt"));
			String line = "";
			while ((sCurrentLine = br.readLine()) != null) {
				line = line + sCurrentLine;
			}

			dateWithFormat = line;
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return dateWithFormat;

	}

	public static void pushAllResultFilesIntoSVVLocation(String excelFilePath, String svnlocation,
			String screenshotpath, String svnUserName, String password) throws IOException {
		String dateWithFormat = getdateFromFile();

		File f = new File(excelFilePath);
		final String svnscreenshotLoc = svnlocation + "/SmokeTestScreenshot";
		final String svnExlLoc = svnlocation + "/ExcelResults/" + f.getName();
		System.out
				.println("    *********************  Start:Pushing data into svn location  *********************    ");
		System.out.println("");
		System.out.println("");
		try {
			importDirectoryContentToSubversion(svnExlLoc, f.getAbsolutePath(), svnUserName,
					getDecryptedVal(password).trim(), "xlsx directory and file added" + dateWithFormat);
			System.out.println("  For Excel output, Please refer below URL: " + svnExlLoc);
		} catch (SVNException e) {

			// e.printStackTrace();

			System.out.println("Error message :" + e.getMessage());

		}

		try {
			importDirectoryContentToSubversion(svnscreenshotLoc, screenshotpath, svnUserName,
					getDecryptedVal(password).trim(), "screenshots directory and files added" + dateWithFormat);
			System.out.println("  For Screenshots, Please refer below URL: " + svnscreenshotLoc);

		} catch (SVNException e) {

			// e.printStackTrace();

			System.out.println("Error message :" + e.getMessage());

		}

		System.out.println("");
		System.out.println("");
		System.out.println("*********************  End:Pushing data into svn location  *********************");

	}

	public static SVNCommitInfo importDirectoryContentToSubversion(final String repositoryURL,
			final String subVersionedDirectory, final String userName, final String hashedPassword,
			final String commitMessage) throws SVNException {
		final SVNClientManager cm = SVNClientManager.newInstance(new DefaultSVNOptions(), userName, hashedPassword);

		// deleteExistingDirectoryContentToSubversion(repositoryURL, userName,
		// hashedPassword);
		return cm.getCommitClient().doImport(new File(subVersionedDirectory), SVNURL.parseURIEncoded(repositoryURL),
				"<import> " + commitMessage, null, false, true, SVNDepth.fromRecurse(true));
	}

	public static void sendMail(String excelFilePath, String svnLocation, String sendEmailIds,
			HashMap<String, String> executedFlow, String subject) {

		String svnscreenshotLoc = svnLocation + "/SmokeTestScreenshot";
		String svnExlLoc = svnLocation + "/ExcelResults/" + excelFilePath;

		StringBuilder str = new StringBuilder();
		str.append("<i>Hi All,</i><br>");
		str.append("<br>");
		str.append("<b>Smoke Testing Execution Results in below excel File link: </b><br>");
		str.append(svnExlLoc + "<br>");
		str.append("<br>");
		str.append("<b>For screenshots, see the below date-wise folder under this url: </b><br>");
		str.append(svnscreenshotLoc + "<br>");
		str.append("<br>");

		str.append("<html><head>");
		str.append(
				"<style>table {font-family: arial, sans-serif;border-collapse: collapse;width: 50%;}td, th { border: 1px solid #dddddd;text-align: left; padding: 8px;}tr:nth-child(even) { background-color: #dddddd;}</style></head>");
		str.append("<body>");
		str.append(
				"<table width='50%' align='left' border='2' cellpadding='0' cellspacing='0' style='border-top:5px solid white;'");
		str.append("<tr><th width=30%>Executed Sheet Name</th><th width=20%>Browser Name</th></tr>");
		int i = 1;
		for (String key : executedFlow.keySet()) {
			i++;
			str.append(
					"<tr><td width=30%><b>" + key + "</b></td><td width=20%>" + executedFlow.get(key) + "</td></tr>");
		}
		str.append(" </table>");
		for (int x = 0; x < i; x++) {
			str.append("<br />");
		}
		str.append("<br />");
		str.append("<br />");
		str.append("<b>Thanks</b>" + "</body></html>");
		try {
			sendHtmlEmail("DG-AH-India-FoundationQA-AutomationTeam@alight.com", sendEmailIds, str.toString(), subject);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
	}
	
	
	public static void premierSendMail(String excelFilePath, String sendEmailIds, List<List<String>> ExecutedFlow, String sheetName, String tsName_Desc, String executedBy) {


		StringBuilder str = new StringBuilder();
		str.append("<i>Hi All,</i><br>");
		str.append("<br>");
		str.append("<b>Results</b><br>");

		str.append("<br>");

		str.append("<html><head>");
		str.append(
				"<table width='1200px' align='left' border='2' cellpadding='0' cellspacing='0' style='border-top:2px solid white;'");
		str.append(
				"<tr><th>Test Case No.</th><th>Description</th><th>Expected Result</th><th>Actual Result</th><th>Test Results</th>"
				+ "</tr>");

		Boolean TCFlag;
		Boolean TestStepFlag;
		String TCNo;
		String TestStepDesc;
		String TestStepExpResult;
		String TestStepActualResult;
		String TestStepTestResults;
		String keywordText;

		String color = "";
		boolean loginStatus, keywordTextEmpty;
		
		//Test Cases For loop
		for (int tcRowIndex = 0; tcRowIndex < ExecutedFlow.size(); tcRowIndex++) {
			TCFlag = ExecutedFlow.get(tcRowIndex).get(0).trim().toLowerCase().contains("y");
			TestStepFlag = ExecutedFlow.get(tcRowIndex).get(1).trim().toLowerCase().contains("y");
			TCNo = ExecutedFlow.get(tcRowIndex).get(2).trim();
			TestStepDesc = ExecutedFlow.get(tcRowIndex).get(3).trim();	
			keywordText = ExecutedFlow.get(tcRowIndex).get(4).trim();	
			//keywordTextEmpty = ExecutedFlow.get(tcRowIndex).get(4).trim().isEmpty();
			TestStepExpResult = ExecutedFlow.get(tcRowIndex).get(15).trim();
			TestStepActualResult = ExecutedFlow.get(tcRowIndex).get(16).trim();
			TestStepTestResults = ExecutedFlow.get(tcRowIndex).get(17).trim();
			loginStatus = ExecutedFlow.get(tcRowIndex).get(17).trim().toLowerCase().contains("pass");
			
			keywordTextEmpty = ExecutedFlow.get(tcRowIndex).get(17).trim().isEmpty();
			
	//		if (TCFlag) {

				//Test Steps loop
		//		for (int testStepRowIndex = tcRowIndex; testStepRowIndex < ExecutedFlow.size(); testStepRowIndex++) {
					
					if (!keywordTextEmpty) {


						if (loginStatus==true) {
							color = "Green";
							//resultStatus = "Server Down";
							
							str.append("<tr><td>" + TCNo + "</td><td>" + TestStepDesc + "</td><td>" + TestStepExpResult + "</td><td>" + TestStepActualResult + "</td><td style='background-color:" + color + ";'>" + TestStepTestResults + "</td></tr>");
							} else if(loginStatus==false) {
							color = "red";
							
							str.append("<tr><td>" + TCNo + "</td><td>" + TestStepDesc + "</td><td>" + TestStepExpResult + "</td><td>" + TestStepActualResult + "</td><td style='background-color:" + color + ";'>" + TestStepTestResults + "</td></tr>");
							} 
						
						
				
					
					//else 							
						//break;  // Exit Test Steps For loop if there is 'e' or 'end' in Test Step column.
					
					}
		
			
		}

		str.append(" </table>");
		str.append("<br />");
		str.append("<br />");
		str.append("   ");
		str.append("Executed By: <b>"+executedBy+"</b>");
		str.append("<br />");
		str.append("<b>Thanks!</b>" + "</body></html><br />");


	try {

		sendHtmlEmail("allwyn.lobo@alight.com", sendEmailIds, str.toString(), sheetName+" Description :"+tsName_Desc);
		System.out.println("Email sent.");
	} catch (Exception ex) {
		System.out.println("Failed to sent email. "+ex.getMessage());
		ex.printStackTrace();
	}
	}
	
	
	public static void temppremierSendMail(String excelFilePath, String sendEmailIds, List<List<String>> ExecutedFlow, String sheetName, int rowno) {


		StringBuilder str = new StringBuilder();
		str.append("<br>");
		str.append("<br>");
		str.append("<b>Test Flow Name : "+sheetName+", Execution Results  </b><br>");
		str.append("<br>");
	//	str.append("<b>Description : "+tsName_Desc+"   </b><br>");
		str.append("<br>");

		str.append("<html><head>");
		str.append(
				"<style>table {font-family: arial, sans-serif;border-collapse: collapse;width: 50%;}td, th { border: 1px solid #dddddd;text-align: left; padding: 8px;}tr:nth-child(even) { background-color: #dddddd;}</style></head>");
		str.append("<body>");
		str.append(
				"<table width='1200px' align='left' border='2' cellpadding='0' cellspacing='0' style='border-top:2px solid white;'");
		str.append(
				"<tr><th>Test Case No.</th><th>Description</th><th>Expected Result</th><th>Actual Result</th><th>Test Results</th>"
				+ "</tr>");

		Boolean TCFlag;
		Boolean TestStepFlag;
		String TCNo;
		String TestStepDesc;
		String TestStepExpResult;
		String TestStepActualResult;
		String TestStepTestResults;
		String keywordText;
		

		String color = "";
		boolean loginStatus, keywordTextEmpty, failStatus;
		int StatusPassCount, StatusFailCount;
		StatusPassCount=0;
		StatusFailCount=0;
		
		//Test Cases For loop
		for (int tcRowIndex = 0; tcRowIndex < ExecutedFlow.size(); tcRowIndex++) {
			TCFlag = ExecutedFlow.get(tcRowIndex).get(0).trim().toLowerCase().contains("y");
			TestStepFlag = ExecutedFlow.get(tcRowIndex).get(1).trim().toLowerCase().contains("y");
			TCNo = ExecutedFlow.get(tcRowIndex).get(2).trim();
			TestStepDesc = ExecutedFlow.get(tcRowIndex).get(3).trim();	
			keywordText = ExecutedFlow.get(tcRowIndex).get(4).trim();	
			//keywordTextEmpty = ExecutedFlow.get(tcRowIndex).get(4).trim().isEmpty();
			TestStepExpResult = ExecutedFlow.get(tcRowIndex).get(15).trim();
			TestStepActualResult = ExecutedFlow.get(tcRowIndex).get(16).trim();
			TestStepTestResults = ExecutedFlow.get(tcRowIndex).get(17).trim();
			loginStatus = ExecutedFlow.get(tcRowIndex).get(17).trim().toLowerCase().contains("pass");
			failStatus = ExecutedFlow.get(tcRowIndex).get(17).trim().toLowerCase().contains("fail");
			

			keywordTextEmpty = ExecutedFlow.get(tcRowIndex).get(17).trim().isEmpty();
			
	//		if (TCFlag) {

				//Test Steps loop
		//		for (int testStepRowIndex = tcRowIndex; testStepRowIndex < ExecutedFlow.size(); testStepRowIndex++) {
					
					if (!keywordTextEmpty) {


						if (loginStatus==true) {
							color = "Green";
							//resultStatus = "Server Down";
							
							str.append("<tr><td>" + TCNo + "</td><td>" + TestStepDesc + "</td><td>" + TestStepExpResult + "</td><td>" + TestStepActualResult + "</td><td style='background-color:" + color + ";'>" + TestStepTestResults + "</td></tr>");
							StatusPassCount=StatusPassCount+1;
							
							} else {
							color = "red";
							
							str.append("<tr><td>" + TCNo + "</td><td>" + TestStepDesc + "</td><td>" + TestStepExpResult + "</td><td>" + TestStepActualResult + "</td><td style='background-color:" + color + ";'>" + TestStepTestResults + "</td></tr>");
							StatusFailCount= StatusFailCount+1;
							} 

					//else 							
						//break;  // Exit Test Steps For loop if there is 'e' or 'end' in Test Step column.
					
					}

		}
		
		str.append(" </table>");
		str.append("<br />");
		str.append("<br />");
		str.append("<br />");
		str.append("" + "</body></html><br />");


	try {
		int totalCount = StatusPassCount+StatusFailCount;
		System.out.println("Pass Count"+StatusPassCount+" Fail count"+StatusFailCount+" Todal count"+totalCount);
		writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, "Master Sheet", Integer.toString(StatusPassCount) ,rowno , 4);
		writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, "Master Sheet", Integer.toString(StatusFailCount) ,rowno , 5);
		writeExpressionDataOnExistingExcel(AutoBoatExecutor.excelFilePath, "Master Sheet", Integer.toString(totalCount) , rowno, 3);
		
		 BufferedWriter bw = new BufferedWriter(new FileWriter(AutoBoatExecutor.htmlPath+sheetName+".html"));
				
		 bw.write(str.toString());
		 bw.close();

		
		System.out.println("Report Created.");
	} catch (Exception ex) {
		System.out.println("Failed to create Report "+ex.getMessage());
		ex.printStackTrace();
	}
	}
	
	
	public static boolean isElementPresent(WebDriver driver, By by, int time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			WebElement element = new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(by));

			return element.isDisplayed();
		} catch (Exception e) {

			return false;
		}
	}

	public static WebDriver getbrowserInstance(String browserName) throws MalformedURLException {
		WebDriver wd = null;
		if (browserName.toLowerCase().contains("ie") || browserName.toLowerCase().contains("internet")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/IEDriverServer.exe");

			System.out.println("IE is starting...");
			wd = new InternetExplorerDriver();
		} else if (browserName.toLowerCase().contains("chrome") || browserName.toLowerCase().contains("google")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
			ChromeOptions cap = new ChromeOptions();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			System.out.println("Chrome is starting...");
			wd = new ChromeDriver(cap);

		} else if (browserName.toLowerCase().contains("firefox") || browserName.toLowerCase().contains("mozilla")) {
			System.out.println("Firefox is starting...");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver.exe");
			// wd = new FirefoxDriver();
			final FirefoxOptions options = new FirefoxOptions();
			options.addPreference("security.sandbox.content.level", 5);
			wd = new FirefoxDriver(options);
			// wd = new FirefoxDriver(cap);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// driver.manage().window().maximize();
		return wd;

	}
	
	
	public static boolean compareString(String s1, String s2) {
		return s1.trim().equals(s2.trim());
		
	}
	
	public static boolean containsString(String expectedResults, String actualResults) {
		
		return actualResults.trim().contains(expectedResults.trim());
	}

		    
		
}  // End of Class AbCommonFiles


