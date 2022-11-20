package dataDriverFramework;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadDataFromExcel1 {
	/*
	 * String[][] data = null;
	 * 
	 * @DataProvider(name = "logindata1") public String[][] dataprovider() throws
	 * IOException { return data = readDatafromExcel(); }
	 */

	@DataProvider
	public String[][] readDatafromExcel() throws IOException {
		//

		FileInputStream fis = new FileInputStream("src/test/resources/TestDataLoginFreeCRM.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		int columncount = sheet.getRow(0).getLastCellNum();

		String[][] testdata = new String[rowcount][rowcount];

		for (int i = 0; i < rowcount - 1; i++) {
			for (int j = 0; j < columncount; j++) {
				DataFormatter dataFormatter = new DataFormatter();
				testdata[i][j] = dataFormatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
			//s	System.out.println(testdata[i][j] );
			}

		}
		return testdata;
		

	}
	
	public static void main(String[] args) throws IOException {
		
		ReadDataFromExcel1 dataFromExcel = new ReadDataFromExcel1();
		dataFromExcel.readDatafromExcel();
	}

}
