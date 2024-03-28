package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class HomePage extends PageObject{

   // @FindBy(xpath = "//*[contains(text(),'{dynamic}')]", timeoutInSeconds="10")
   // public WebElementFacade envHeader;
    
    //Home Page Will also have common Elements 
    @FindBy(xpath = "//*[(@class=\"indicatorContainer forceInlineSpinner\")]")
    public WebElementFacade spinner;
    
    public String envHeader = "//span[contains(text(),'{dynamic}')]";

}
 