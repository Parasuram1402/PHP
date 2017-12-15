package SeleniumTraining.PHP;

import org.testng.annotations.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

public class GridSample {
   public WebDriver driver;
   public String URL, Node;
   protected ThreadLocal<RemoteWebDriver> threadDriver = null;
   
   @Parameters({"browser","node"})
   @BeforeTest
   public void launchapp(String browser, String node) throws MalformedURLException {
      String URL = "http://newtours.demoaut.com";
      
      if (browser.equalsIgnoreCase("firefox")) {
         System.out.println(" Executing on FireFox");
         
         DesiredCapabilities cap = DesiredCapabilities.firefox();
         cap.setBrowserName("firefox");
         
         driver = new RemoteWebDriver(new URL(node), cap);
         // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         
         // Launch website
         driver.navigate().to(URL);
         driver.manage().window().maximize();
      } else if (browser.equalsIgnoreCase("chrome")) {
         System.out.println(" Executing on CHROME");
         DesiredCapabilities cap = DesiredCapabilities.chrome();
         cap.setBrowserName("chrome");
         
         driver = new RemoteWebDriver(new URL(node), cap);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         
         // Launch website
         driver.navigate().to(URL);
         driver.manage().window().maximize();
      } else if (browser.equalsIgnoreCase("ie")) {
         System.out.println(" Executing on IE");
         DesiredCapabilities cap = DesiredCapabilities.chrome();
         cap.setBrowserName("ie");
         
         driver = new RemoteWebDriver(new URL(node), cap);
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         
         // Launch website
         driver.navigate().to(URL);
         driver.manage().window().maximize();
      } else {
         throw new IllegalArgumentException("The Browser Type is Undefined");
      }
   }
   
   @Test
   public void nettours() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("mercury");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mercury");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		
		
		if(driver.findElement(By.xpath("//img[@alt='Mercury Tours']")).isDisplayed())
		{
			System.out.println("Success");
			WebElement mySelectElement = driver.findElement(By.xpath("//select[@name='fromPort']"));
			Select dropdown=new Select(mySelectElement);
			dropdown.selectByValue("London");
			
			Thread.sleep(1000);
			List<WebElement> oRadioButton = driver.findElements(By.xpath("//input[@name='servClass']"));
			System.out.println(oRadioButton.size());
			Thread.sleep(1000);
			for(int j=0; j<oRadioButton.size();j++)
			{
				System.out.println(oRadioButton.get(j).getAttribute("value"));
				if(oRadioButton.get(j).getAttribute("value").equals("Business")){
					oRadioButton.get(j).click();
				}
			}
			
			Thread.sleep(3000);
			driver.close();	
		} else{
			System.out.println("Failure");
		}
   }
   
   @AfterTest
   public void closeBrowser() {
      driver.quit();
   }
}