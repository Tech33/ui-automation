package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.annotations.Managed;

public class LoginPage extends PageObject {

    @Managed
    WebDriver driver;


    @FindBy(id = "username")
    public WebElementFacade username;

    @FindBy(id = "password")
    public WebElementFacade password;

    @FindBy(id = "Login")
    public WebElementFacade logInToSandboxButton;

    @FindBy(xpath = "//img[@class=\"standard_logo\" and @alt=\"Salesforce\"]")
    public WebElementFacade salesforceLoginPage;
}
