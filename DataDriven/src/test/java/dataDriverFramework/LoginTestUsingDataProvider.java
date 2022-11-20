package dataDriverFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginTestUsingDataProvider {

	WebDriver driver;
	String[][] logindata = null;
	/*
	 * String[][] logindata = { {"BharathTest","Bharath@test"},
	 * {"BharathTest","Bharathtest"}, {"BharathTest1","Bharath@test"},
	 * {"BharathTest1","Bharath@test1"} };
	 * 
	 * String[][] logindata = null;
	 * 
	 * @DataProvider(name="Login") public String[][] logindataProvider() { return
	 * logindata;
	 * 
	 * }
	 */


	@DataProvider(name = "Login")
	public String[][] logindataProvider() throws BiffException, IOException {
		return logindata = getExcelData();

	}

	public String[][] getExcelData() throws BiffException, IOException {

		String path = "src/test/resources/TestDataLoginFreeCRM.xls";

		FileInputStream fileips = new FileInputStream(path);

		Workbook workbook = Workbook.getWorkbook(fileips);
		Sheet sheet = workbook.getSheet(0);
		int rowscount = sheet.getRows();
		int columnscount = sheet.getColumns();
		String testdata[][] = new String[rowscount - 1][columnscount];
		for (int i = 1; i < rowscount; i++) {
			for (int j = 0; j < columnscount; j++) {
				testdata[i - 1][j] = sheet.getCell(j, i).getContents();
			}
		}
		return testdata;

	}

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
	}

	@Test(dataProvider = "Login", enabled = false)
	public void loginTestUsingDataProvider(String username, String password) {

		driver.get("https://classic.freecrm.com/index.html");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		// driver.findElement(By.cssSelector("input[value='Login']")).click();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}


}
