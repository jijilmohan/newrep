package step;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.Library;
import framework.Reports;

import org.junit.Assert.*;
import org.openqa.selenium.WebElement;

public class Stepdefinition {
	static Reports report=new Reports();
	
	Library reuse=new Library();
	@Given("^I am in \"(.*?)\" with \"(.*?)\" Header$")
	public void i_am_in_with_Header(String eleName, String text) throws Throwable {
		if(!reuse.errorFlag){
		String elePath=reuse.getXpath(eleName);
		WebElement element=reuse.locateElement(eleName,elePath);
		reuse.verifyElementText(element,text,eleName);
		report.pass(eleName+" Page compared with "+text);
		}
		
	}

	@When("^I Click on \"(.*?)\" Button$")
	public void i_Click_on_Button(String eleName) throws Throwable {
		if(!reuse.errorFlag){
		String elePath=reuse.getXpath(eleName);
		WebElement element=reuse.locateElement(eleName,elePath);
		reuse.click(element,eleName);
		report.pass("clicked "+eleName);
	}
	}
	@Then("^I Verify \"(.*?)\" with \"(.*?)\" Value$")
	public void i_Verify_with_Value(String eleName, String text) throws Throwable {
		if(!reuse.errorFlag){
		String elePath=reuse.getXpath(eleName);
		WebElement element=reuse.locateElement(eleName,elePath);
		reuse.verifyElementText(element,text,eleName);
		report.pass(eleName+" compared with "+text);
		}
	}

	@Given("^Test Begins for \"(.*?)\"$")
	public void test_Begins_for(String arg1) throws Throwable {
		System.out.println("\n\nTest begins for: "+arg1);
		reuse.errorFlag=false;
	    report.createTest(arg1, null);
	}

}
