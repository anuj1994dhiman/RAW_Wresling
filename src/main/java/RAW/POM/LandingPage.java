package RAW.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends POMUtils{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPass;
	
	@FindBy(id="login")
	WebElement UserLogin;
	
	@FindBy(css=".toast-message")
	WebElement errMsg;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void login(String username, String password) {
		userName.sendKeys(username);
		userPass.sendKeys(password);
		UserLogin.click();
	}
	
	public String getErrMessage() {
		waitForElement(errMsg);
		return errMsg.getText();
	}

}
