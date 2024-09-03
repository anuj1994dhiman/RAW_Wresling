package RAW.POM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POMUtils {
	
	WebDriver driver;
	WebDriverWait wait;

	public POMUtils(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.wait = wait;
	}
	
	public void waitForElement(WebElement elament) {
		wait.until(ExpectedConditions.visibilityOf(elament));
	}
}
