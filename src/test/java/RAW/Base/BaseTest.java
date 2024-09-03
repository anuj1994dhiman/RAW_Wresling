package RAW.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RAW.POM.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else
			System.out.println("invalid driver");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public LandingPage launch(@Optional("chrome") String browser) {
		initDriver(browser);
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void terminate() {
		driver.quit();
	}

	public String takeSreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File src = sc.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("E:\\Realme 7\\Books\\reports\\" + testCaseName + ".png"));
		return "E:\\Realme 7\\Books\\reports\\" + testCaseName + ".png";
	}

	public List<HashMap<String, String>> getJsonData() throws IOException {
		String jsonData = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")+"\\src\\test\\java\\RAW\\Data\\data.json"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	public Object[][] getExelData() throws IOException {
		File exelData = new File("E:\\Realme 7\\cred.xlsx");
		FileInputStream exlFile = new FileInputStream(exelData);
		XSSFWorkbook wb = new XSSFWorkbook(exlFile);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getPhysicalNumberOfRows();
		XSSFRow r = sheet.getRow(0);
		int cellNum = r.getPhysicalNumberOfCells();
		Object[][] data = new Object[rowNum-1][cellNum];
		DataFormatter formater = new DataFormatter();
		for(int i=0; i<rowNum-1;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cellNum;j++) {
				XSSFCell cell= row.getCell(j);
				data[i][j] = formater.formatCellValue(cell);
			}
		}
		
		return data;
	}
}
