package framework;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		

		features = {"Resource/Feature/fet1.feature",
				"Resource/Feature/fet2.feature"}

		, glue = { "step" }, dryRun = false, monochrome=true)
public class runner {
	static Library reuse = new Library();

	@BeforeClass
	public static void setup() throws IOException  {
		try{
		reuse.diverSetup();
		
			reuse.openExecl();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void teardown() throws IOException {

		reuse.diverTear();
	}

}
