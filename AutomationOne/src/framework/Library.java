package framework;

import java.awt.Robot;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Library {
	@SuppressWarnings("unchecked")
	static XSSFWorkbook workbook;
	static WebDriver driver;
	static Reports report=new Reports();
	public static boolean errorFlag=false;
	
	
	void diverSetup() throws IOException {
		System.setProperty("webdriver.chrome.driver", "F:\\jijil\\Eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		Reports.initiate();
		driver.get("http://toolsqa.com/automation-practice-form/");
		System.out.println("Driver initiated and page loaded");
		
		
	}

	public void diverTear() throws IOException {
	/*Process child = Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"taskkill /f /im chromedriver.exe\"");
	child.destroyForcibly();*/
		driver.close();
		((Closeable) report.extent).close();
	}

	public void openExecl() throws FileNotFoundException {
		try{
		File file=new File("C:\\Users\\Admin\\workspace\\AutomationOne\\Resource\\Execl\\config.xlsx");
		FileInputStream inputStream=new FileInputStream(file);
			workbook= new XSSFWorkbook(inputStream);
		}catch(Exception e)
		{
			System.out.println("Unable to open execl");
			e.printStackTrace();
		}
		
	}

	public String getXpath(String arg1) {
		String cellValue=null;
		try{
			XSSFSheet sheet= workbook.getSheetAt(0);
			
			Iterator<Row> iterator = sheet.iterator();
			while(iterator.hasNext())
			{	Row currentRow=iterator.next();
				if((currentRow.getCell(1).getStringCellValue()).equals(arg1))
					{
					cellValue= (currentRow.getCell(2)).getStringCellValue();
					return cellValue;
					}
			}
		
		
	}catch(Exception e)
		{
		System.out.println("Unable search path in execl("+arg1+")");
		e.printStackTrace();
		}
		return  cellValue;
	}

	public WebElement locateElement(String eleName, String elePath) throws IOException {
		WebElement element=null;
		if(!errorFlag){
		try{
			Wait wait=new WebDriverWait(driver,10);
			element=(WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elePath)));
			return element;
		}catch(Exception e)
		{	report.failshot("Failed to locate element "+eleName+"("+elePath+")");
			System.out.println("Failed to locate element "+eleName+"("+elePath+")");
			e.printStackTrace();
		}}
		return element;
	}

	public void verifyElementText(WebElement element, String arg2, String eleName) throws IOException {
		try{
			if(element.getText().equalsIgnoreCase(arg2))
					System.out.println(eleName+" compare succesfully with value "+arg2);
			else
				throw new Exception();
		}catch(Exception e)
		{	report.failshot(eleName);
			System.out.println("Failed to compare "+arg2+" with "+arg2);
			e.printStackTrace();
		}
		
	}

	public void click(WebElement element, String eleName) throws IOException {
		try{
			element.click();
		}catch(Exception e)
		{	report.failshot(eleName);
			System.out.println("Unable to click "+eleName);
			e.printStackTrace();
		}
		
	}

}