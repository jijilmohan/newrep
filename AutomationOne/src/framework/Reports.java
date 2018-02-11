package framework;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class Reports extends Library{
	
	static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	
		static void initiate() throws IOException
	{	 
		 File file = new File(System.getProperty("user.dir") +"/test-output");
		 File file2 = new File(System.getProperty("user.dir") +"/test-output/Screens");
	        if (file.exists()) {
	        	FileUtils.deleteDirectory(file);
	        	
	        }
	            if (file.mkdir()) {
	            	file2.mkdir();
	                System.out.println("Directory is created!");
	            } else {
	                System.out.println("Failed to create directory!");}
	   
	                htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
	                extent = new ExtentReports();
	                extent.attachReporter(htmlReporter);
	                 
	                extent.setSystemInfo("OS", "win 7");
	                extent.setSystemInfo("Host Name", "abc");
	                extent.setSystemInfo("Environment", "QA");
	                extent.setSystemInfo("User Name", "Jijil");
	                 
	                htmlReporter.config().setChartVisibilityOnOpen(true);
	                htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
	                htmlReporter.config().setReportName("sample");
	                htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	                htmlReporter.config().setTheme(Theme.DARK);
	            
	   }
	    
	public static ExtentTest createTest(String name, String description){	
		test = extent.createTest(name, description);
		return test;
	}

	public void failshot(String eleName) throws IOException {
		String screenShotPath = capture(driver, "screenShotName");
        test.log(Status.FAIL, MarkupHelper.createLabel(" Test case FAILED due to issues: "+eleName, ExtentColor.RED));
        test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
		//test.log(Status.FAIL, eleName );
       
        extent.flush();
      // extent.removeTest(test);
		
		
	}
public void pass(String eleName) throws IOException {
		
		test.log(Status.PASS, eleName );
		extent.flush();
}
public static String capture(WebDriver driver,String screenShotName) throws IOException
{errorFlag=true;
    TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String dest = System.getProperty("user.dir") +"/test-output/Screens/"+System.currentTimeMillis()+".png";
    File destination = new File(dest);
    FileUtils.copyFile(source, destination);        
                 
    return dest;
}
}