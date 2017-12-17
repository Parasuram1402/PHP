package SeleniumTraining.PHP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExcelErrorTest {
	WebDriver driver;
	File inputfile;
	FileInputStream inputstream;
	XSSFWorkbook workbook;
	XSSFWorkbook workbook1;
	XSSFSheet worksheet;
	XSSFCell row;
@Test
public void newTest()
{
System.out.println("entered");
driver.findElement(By.linkText("User Management")).click();
driver.findElement(By.xpath("//table[1]//img[@alt='Add']")).click();

}
	
@BeforeMethod
public void beforeMethod(Method methd)
{
	System.out.println(workbook.getSheet(methd.getName()));
	}

@BeforeTest
public void beforeTest() throws IOException {  
	String fileName="PhpCollab.xlsx";
	inputfile = new File("E:\\PhpCollab.xlsx");
	inputstream=new FileInputStream(inputfile);

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx")){

    //If it is xlsx file then create object of XSSFWorkbook class
    	System.out.println("enterd loop of if");

    
	workbook1=new XSSFWorkbook(inputstream);
	System.setProperty("webdriver.chrome.driver","F:\\chromedriver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("http://localhost/phpcollab/");
	System.out.println("got credentials");
	driver.findElement(By.xpath("//input[@name='loginForm']")).sendKeys("admin");
	driver.findElement(By.xpath("//input[@name='passwordForm']")).sendKeys("phpcadmin");
	driver.findElement(By.xpath("//input[@name='save']")).click();
   }
}
@AfterTest
public void afterTest() throws IOException
{ 	inputstream.close();
	driver.quit();
}
}