package SeleniumTraining.PHP;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelExecution {
	   public WebDriver driver;
	   public String URL, Node;
	   protected ThreadLocal<RemoteWebDriver> threadDriver = null;
	   
	@Parameters({"node","browser"})
	@BeforeTest
	public void launch(String node, String browser) throws MalformedURLException{
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
            //driver.manage().window().maximize();
         } else if (browser.equalsIgnoreCase("chrome")) {
            System.out.println(" Executing on CHROME");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            
            driver = new RemoteWebDriver(new URL(node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            
            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
         }
	}
	   
	   
	@Parameters("TestName")
	@Test
  public void loginTest(String TestName) throws InterruptedException, MalformedURLException{
        System.out.println(" Executing " + TestName + " Test");
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("mercury");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mercury");
		driver.findElement(By.xpath("//input[@name='login']")).click();
		Thread.sleep(3000);
		driver.close();	

  }
	
	
   @AfterTest
   public void closeBrowser() {
      driver.quit();
   }
}
