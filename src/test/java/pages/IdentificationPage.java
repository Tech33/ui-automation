package pages;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.WebDriver;

public class IdentificationPage extends PageObject {

    @Managed
    WebDriver driver;

    @FindBy(xpath = "//button[text()=\"Search...\"]")
    public WebElementFacade searchField;
    
    @FindBy(xpath = "//input[@placeholder=\"Search...\"]")
    public WebElementFacade searchEntryField;

    @FindBy(xpath = "//span[text()=\"Name\"]//ancestor::table//tbody//th/span/a")
    //*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//tr/th
    public WebElementFacade foundSearchName;

    @FindBy(xpath = "//*[@data-aura-class=\"uiVirtualDataTable\"]", timeoutInSeconds="6")
    //*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//tr/th
    public WebElementFacade dataTable;

    @FindBy(xpath = "//span[text()=\"Account Name\"]//ancestor::table//tbody//th/span/a")
    //*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//tr/th
    public WebElementFacade foundSearchAccountName;
}