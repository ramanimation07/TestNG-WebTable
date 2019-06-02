package com.testng.webtable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestNGWebTable {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		//*[@id="customers"]/tbody/tr[1]/th[1]
		//*[@id="customers"]/tbody/tr[2]/td[1]
		//*[@id="customers"]/tbody/tr[2]/td[2]
		
		//*[@id="customers"]/tbody/tr[1]/th[1]
		
		
		String beforeXpath= "//*[@id=\"customers\"]/tbody/tr[";
		String afterXpath="]/td[1]";
		
		String beforeXpath_contact= "//*[@id=\"customers\"]/tbody/tr[";
		String afterXpath_contact="]/td[2]";
		
		List<WebElement> rows=driver.findElements(By.xpath("//table[@id='customers']//tr"));
		System.out.println("Total number of rows:"+ (rows.size()-1));
		int rowCount=rows.size();
		
		Xls_Reader reader=new Xls_Reader("C:\\Users\\welcome\\eclipse-workspace\\TestNGWebTable\\src\\main\\java\\com\\testdata\\TestDataRediff.xlsx");
		if(!reader.isSheetExist("TableData"))
		{
			reader.addSheet("TableData");
			reader.addColumn("TableData", "CompanyName");
			reader.addColumn("TableData", "ContactName");
		}
	
		
		
		for(int i=2;i<=rowCount;i++)
		{
			String actualXpath=beforeXpath+i+afterXpath;
			String companyName=driver.findElement(By.xpath(actualXpath)).getText();
			System.out.println(companyName);
			reader.setCellData("TableData", "CompanyName", i, companyName);
			
			String actualXpath_contact=beforeXpath_contact+i+afterXpath_contact;
			String contactName=driver.findElement(By.xpath(actualXpath_contact)).getText();
			System.out.println(contactName);
			reader.setCellData("TableData", "ContactName", i, contactName);
		}
	}

}
