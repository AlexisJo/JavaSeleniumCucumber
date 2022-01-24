package StepsForHooks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HooksDemoSteps {

//	WebDriver driver = Driver.startdriver();
	
	public static WebDriver driver;

    public static WebDriver startdriver(){

        return driver;

        }
	
	
	@Before(order=0)
	public void browserSetup() {
		
		System.out.println("  I am inside browserSetup");
    	ChromeOptions chromeOptions = new ChromeOptions();
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Before(value="@smoke", order=1)
	public void setup2() {
		System.out.println("   I am inside setup2 \n");
		
	}
	
	@After(order=1)
	public void teardown() {
		System.out.println("   I am inside teardown");
		//driver.close();
		driver.quit();
	}
	
	@After(order=2)
	public void teardown2() {
		System.out.println("  I am inside teardown2 \n");
	}
	
	@BeforeStep
	public void beforeSteps() {
		System.out.println("  I am inside beforeSteps ----");
	}
	
	@AfterStep
	public void afterSteps() {
		System.out.println("  I am inside asfterSteps ====");
	}

}
