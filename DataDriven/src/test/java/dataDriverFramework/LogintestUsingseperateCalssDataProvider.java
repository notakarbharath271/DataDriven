package dataDriverFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogintestUsingseperateCalssDataProvider {
	@Test(dataProvider = "readDatafromExcel",dataProviderClass = ReadDataFromExcel1.class)
	public void freeCRMLogin1(String username,String password) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://classic.freecrm.com/index.html");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Login']")).click();		
		driver.quit();
	}

}
