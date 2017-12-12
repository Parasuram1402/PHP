package SeleniumTraining.PHP;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.testng.Assert;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public 	class PhPCollabTest {
  	XSSFRow row;
    File file ;
    FileInputStream fis;
    String CurrentSheet;
    XSSFWorkbook workbook;
    XSSFSheet spreadsheet;
    Iterator < Row >  rowIterator;
    WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	SoftAssert sa;
@Test(priority=1)
public void CreateUser() throws IOException, InterruptedException {
	driver.findElement(By.linkText("User Management")).click();
	logger = extent.startTest("CreateUser");
	row = (XSSFRow) rowIterator.next();
	while (rowIterator.hasNext()) {
	    row = (XSSFRow) rowIterator.next();
	    driver.findElement(By.xpath("//img[@alt='Add']")).click();
	    Iterator < Cell >  cellIterator = row.cellIterator();
	    Cell cell = cellIterator.next();
		driver.findElement(By.xpath("//input[@name='un']")).sendKeys(cell.getStringCellValue());
		cell = cellIterator.next();
		driver.findElement(By.xpath("//input[@name='fn']")).sendKeys(cell.getStringCellValue());
		cell = cellIterator.next();
		driver.findElement(By.xpath("//input[@name='pw']")).sendKeys(cell.getStringCellValue());
		driver.findElement(By.xpath("//input[@name='pwa']")).sendKeys(cell.getStringCellValue());
		cell = cellIterator.next();
		List<WebElement> permissions=driver.findElements(By.xpath("//input[@name='perm']"));
		  
		if(cell.getStringCellValue().equals("Project Manager"))
		{
			permissions.get(0).click();
		} else if (cell.getStringCellValue().equals("Project Administrator")) {
			permissions.get(3).click();
		} else if (cell.getStringCellValue().equals("User")) {
			permissions.get(1).click();
		}
		driver.findElement(By.xpath("//input[@name='Save']")).click();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//td")).getText());
		if(driver.findElement(By.xpath("//td")).getText().equalsIgnoreCase("Success : Addition succeeded")){
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "Create User for " + cell.getStringCellValue() + " passed");
		} else {
			Assert.assertTrue(false);
			logger.log(LogStatus.FAIL, "Create User for " + cell.getStringCellValue() + " failed");
		}

	}
	
	
}

@Test(priority=2, dependsOnMethods={"CreateUser"})
  public void CreateProject() throws IOException, InterruptedException {
	row = (XSSFRow) rowIterator.next();
	logger = extent.startTest("CreateProject");
	while (rowIterator.hasNext()) {
		row = (XSSFRow) rowIterator.next();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//img[@name='saP0']")).click();
		Iterator < Cell >  cellIterator = row.cellIterator();			
		Cell cell = cellIterator.next();
		driver.findElement(By.xpath("//input[@name='pn']")).sendKeys(cell.getStringCellValue());
		cell = cellIterator.next();
		Select priority=new Select(driver.findElement(By.xpath("//select[@name='pr']")));
		priority.selectByVisibleText(cell.getStringCellValue());
		
		cell = cellIterator.next();
		Select owner=new Select(driver.findElement(By.xpath("//select[@name='pown']")));
		owner.selectByVisibleText(cell.getStringCellValue());
		driver.findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(2000);
		
		System.out.println(driver.findElement(By.xpath("//td")).getText());
		if(driver.findElement(By.xpath("//td")).getText().equalsIgnoreCase("Success : Addition succeeded")){
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "Create Project for " + cell.getStringCellValue() + " passed");
		} else {
			Assert.assertTrue(false);
			logger.log(LogStatus.FAIL, "Create Project for " + cell.getStringCellValue() + " failed");
		}		
		
	 }

  }	

@BeforeMethod
public void beforeMethod(Method method) throws IOException {
//System.out.println("Here is the magic "+method.getName());
spreadsheet=workbook.getSheet(method.getName());
rowIterator = spreadsheet.iterator();

}

@AfterMethod
public void getResult(ITestResult result){
	if(result.getStatus() == ITestResult.FAILURE){
		logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
	}else if(result.getStatus() == ITestResult.SKIP){
		logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	}
	extent.endTest(logger);
}

@BeforeTest
public void beforeTest() throws IOException {
  file= new File(System.getProperty("user.dir") + "/data/PhpCollab.xlsx");
  extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
  extent
  .addSystemInfo("Host Name", "Excers Training")
  .addSystemInfo("Environment", "Automation Testing")
  .addSystemInfo("User Name", "Parasuram");

  extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
  
  
  fis = new FileInputStream(file);
  workbook = new XSSFWorkbook(fis);		  
  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver.exe");
  driver=new ChromeDriver();
  driver.manage().window().maximize();
  driver.get("http://localhost/");
  driver.findElement(By.xpath("//input[@name='loginForm']")).sendKeys("admin");
  driver.findElement(By.xpath("//input[@name='passwordForm']")).sendKeys("phpcadmin");
  driver.findElement(By.xpath("//input[@name='save']")).click();		  

  
}

@AfterTest
public void afterTest() throws IOException {
  fis.close();
  driver.findElement(By.linkText("Log Out")).click();
  driver.quit();
  extent.flush();
  extent.close();
}
  
}
