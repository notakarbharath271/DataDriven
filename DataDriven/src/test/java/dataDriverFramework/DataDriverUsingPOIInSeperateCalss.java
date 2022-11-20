package dataDriverFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDriverUsingPOIInSeperateCalss {
	
	static List<String> usernamelist = new ArrayList<String>();
	static List<String> passwordlist = new ArrayList<String>();
	
	
	public static void readatafromexcel() throws IOException {
		String path = "src/test/resources/TestDataLoginFreeCRM.xlsx";

		FileInputStream fileipsexcel = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fileipsexcel);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row rowValue = rowIterator.next();
			Iterator<Cell> columnIterator = rowValue.cellIterator();
			int i=2;
			while (columnIterator.hasNext()) {
				//Cell cellValue = columnIterator.next();
				//System.out.println(cellValue);
				
				if(i%2==0) {
					//Cell username = columnIterator.next();
					usernamelist.add(columnIterator.next().getStringCellValue());
				}else {
					//Cell password = columnIterator.next();
					passwordlist.add(columnIterator.next().getStringCellValue());
				}
				i++;
			}

		}
		//System.out.println(usernamelist);
		//System.out.println(passwordlist);
	}
		
		public static void loginTest(String username, String password) {
			
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

			driver.get("https://classic.freecrm.com/index.html");
			driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
			driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			driver.quit();

		}
		
		public static  void execution() {
			for(int i=0;i<usernamelist.size();i++) {
				loginTest(usernamelist.get(i),passwordlist.get(i));
			}
		}

	
	public static void main(String[] args) throws IOException {
		readatafromexcel();
		execution();
		
	}


}
