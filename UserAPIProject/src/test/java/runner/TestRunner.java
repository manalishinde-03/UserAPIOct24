package runner;


	import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;

	@CucumberOptions(
	    features ="src/test/resources/features",
	    //tags ="@TC5",
	    glue = {"stepDefinitions"},
	    plugin = {"pretty", "html:target/cucumber-reports.html",
	    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	    		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
	    	
	    monochrome = true
	)
	public class TestRunner extends AbstractTestNGCucumberTests{
		
		 @Override
		    @DataProvider(parallel = false)
		    public Object[][] scenarios() {
		        return super.scenarios();
		    }
	}

