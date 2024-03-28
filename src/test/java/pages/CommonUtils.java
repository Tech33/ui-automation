package pages;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


public class CommonUtils extends PageObject{

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);   
	HomePage homePage;

	Actions builder; 
	public void clickJS(WebElement we) {
		try {
			homePage.spinner.waitUntilNotVisible();
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].click();", we);
			spinnerWait();
			//Thread.sleep(1000);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void spinnerWait() {
		try {
			//System.out.println(getImplicitWaitTimeout());
			//System.out.println(getWaitForTimeout());
			//withTimeoutOf(60, ChronoUnit.SECONDS).waitFor(homePage.spinner).waitUntilNotVisible();
			waitForCondition()
			.withTimeout(Duration.ofSeconds(400))
			.pollingEvery(Duration.ofSeconds(10))
			.withMessage("Spinner Is still Visible")
			.until(ExpectedConditions.invisibilityOf((homePage.spinner)));
			checkPageIsReady();
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}
		
	public void clickJSWait(WebElement we, int time) {
		try {
			homePage.spinner.waitUntilNotVisible();
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].click();", we);
			homePage.spinner.waitUntilNotVisible();
			Thread.sleep(1000 * time);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickWithBuilder(WebElement we) {
		try {
			Actions builder = new Actions(getDriver());
			builder.moveToElement(we)
			.click().build().perform();    		
//			homePage.spinner.waitUntilNotVisible();
//			checkPageIsReady();
			spinnerWait();
		}catch (Exception e) {
			e.printStackTrace();
		}       
	}

	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		//Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			//System.out.println("Page Is loaded..........");
			return; 
		} 

		//This loop will rotate for 25 times to check If page Is ready after every 1 second.
		//You can replace your value with 25 If you wants to Increase or decrease wait time.
		for (int i=0; i<25; i++){ 
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {} 
			//To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				break; 
			}   
		}
	} 

	public WebElementFacade dynamicXpath(String Xpath, String dynamicElement)
	{
		String target = "{dynamic}";
		String newString = Xpath.replace(target, dynamicElement);
		return find(By.xpath(newString));
	}

	public void waitTime(double d) {
		try {
			Thread.sleep((long) (1000 * d));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeTabs(List<WebElementFacade> btnClose) {
		try {
			if(btnClose.size() > 0)
			{
				for(int i=btnClose.size(); i>0; i-- )
					clickJS(btnClose.get(i-1));
				waitTime(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		checkPageIsReady();
	}


	public void selectByOption(String optionValue) {
		try {
			List<WebElement> optionsToSelect = getDriver().findElements(By.tagName("option"));
			for(WebElement option : optionsToSelect){           
				//LOGGER.info(option.getText()); 
				if(option.getText().equals(optionValue)) {
					LOGGER.info("Trying to select: " + optionValue);
					option.click();
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	} 

	public Date nextMonday()
	{
		Calendar now = Calendar.getInstance();
		int weekday = now.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.MONDAY)
		{
			// calculate how much to add
			// the 2 is the difference between Saturday and Monday
			int days = (Calendar.SATURDAY - weekday + 2) % 7;
			now.add(Calendar.DAY_OF_YEAR, days);
		}
		// now is the date you want
		Date nextMondaydate = now.getTime();
		//String format = new SimpleDateFormat("dd-MMM-YYYY").format(date1);

		return nextMondaydate;
	}   
}

