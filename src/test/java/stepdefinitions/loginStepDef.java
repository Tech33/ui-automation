package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.*;
import  net.serenitybdd.core.Serenity.*;
import net.serenitybdd.core.di.SerenityInfrastructure;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.remote.CapabilityType;  
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import pages.CasesPage;
import pages.CommonUtils;
import pages.HomePage;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class loginStepDef {

    @Managed
    WebDriver driver;
	
    LoginPage loginPage;
    HomePage homePage;
    CommonUtils commonUtils;
    CasesPage casePage;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(loginStepDef.class);

    EnvironmentVariables environmentVariables = SerenityInfrastructure.getEnvironmentVariables();
    EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
    String username = EnvironmentSpecificConfiguration.from(variables).getProperty("user.manager.username");
    String password = EnvironmentSpecificConfiguration.from(variables).getProperty("user.manager.password");
    String environment = EnvironmentSpecificConfiguration.from(variables).getProperty("user.manager.environment");
    
    @Given("I have launched the login page - Login Page")
    public void iHaveLaunchedTheLoginPageLoginPage() throws Exception {
    	
			//String chromepath = "/Users/user/Desktop/CRM/CRM (zipped) Folder/src/test/resources/webdriver/mac/chromedriver";
			//System.setProperty("webdriver.chrome.driver", chromepath);
    		//ChromeOptions option = new ChromeOptions();    		
    		//option.addArguments("user-data-dir=/Users/user/Library/Application Support/Google/Chrome/");
    		//driver = new ChromeDriver(option); 
 
    		//String nodeURL = "http://localhost:4444/wd/hub";
    		//driver = new RemoteWebDriver(new URL(nodeURL), option);
    		//driver.get("https://trgl--csr1sit.lightning.force.com/lightning/page/home"); 
    		  	
    		loginPage.open();
    		driver.manage().window().maximize();
    		//driver.manage().window().setSize(new Dimension(1920, 1080));
    		commonUtils.checkPageIsReady();

    		
    		if(loginPage.salesforceLoginPage.isVisible())
    		{
    			loginPage.salesforceLoginPage.waitUntilClickable();
    			loginPage.username.clear();
    			loginPage.username.type(username);
    			byte[] decodedPassword = Base64.decodeBase64(password);
    			commonUtils.waitTime(1);
    			loginPage.password.type(new String(decodedPassword));
    			loginPage.logInToSandboxButton.click();
    			commonUtils.checkPageIsReady();
    		}
    		commonUtils.spinnerWait();
    		LOGGER.info("I have launched the login page - Salesforce");
    }


    //Show which Environment has been reached in the console and prove Home page has been reached
    @Then("^Home page is reached$")
    public void home_page_is_reached() throws Throwable {
 
    	int counter=0;
    	

    	while(!driver.getCurrentUrl().contains("lightning.force.com") && counter < 5)
    	{
    		driver.navigate().refresh();
    		commonUtils.checkPageIsReady();
    		counter++;
    	}
    	assertThat(driver.getCurrentUrl()).as("Env Mismatch").contains("lightning.force.com");
    	LOGGER.info("Home page for Environment URL" + (driver.getCurrentUrl()));
    	

    }
    
    @And("^I verify that testing will commence in expected Enviromnent$")
    public void i_verify_that_testing_will_commence_in_expected_enviromnent() throws Throwable {

    	assertThat(driver.getCurrentUrl()).as("Env Mismatch").contains(environment);
    	commonUtils.dynamicXpath(homePage.envHeader, environment.toUpperCase()).waitUntilVisible();
    	assertThat(commonUtils.dynamicXpath(homePage.envHeader, environment.toUpperCase()).isDisplayed()).as("Env Mismatch").isTrue();
    	
        commonUtils.closeTabs(casePage.btnClose);
        commonUtils.checkPageIsReady();
    }
    

    @When("user login into application with credentials")
    public void userLoginIntoApplicationWithCredentials(DataTable usercredentials) throws Throwable{
        List<List<String>> data = usercredentials.asLists();
        loginPage.username.sendKeys(data.get(0).get(0));     
        byte[] password = Base64.decodeBase64(data.get(0).get(1));
        
        LOGGER.info("Password getting entered is:- " + new String(password));
        loginPage.password.sendKeys(new String(password));
        
        loginPage.logInToSandboxButton.shouldBePresent();
        loginPage.logInToSandboxButton.shouldBeVisible();
        loginPage.logInToSandboxButton.click();
        //Sleep/Pause to catch verification code request if it occurs
    }
    
    @And("^I verify that Login Screen is Displayed$")
    public void i_verify_that_login_screen_is_displayed() throws Throwable {
    	
    	assertThat(loginPage.logInToSandboxButton.isPresent()).as("Login Screen Not Coming....").isTrue();
    }
}
