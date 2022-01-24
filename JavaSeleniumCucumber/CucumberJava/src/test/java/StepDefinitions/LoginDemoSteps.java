package StepDefinitions;

import java.util.concurrent.TimeUnit;



import StepsForHooks.HooksDemoSteps;
import io.cucumber.java.en.*;
import pagefactory.HomePage_PF;
import pagefactory.LoginPage_PF;


public class LoginDemoSteps {

	LoginPage_PF login;
	HomePage_PF home;

	@Given("browser is open")
	public void browser_is_open() {
		
		System.out.println("  ===  I am inside LoginDemoSteps_PF class ===== ");
		
		System.out.println("Inside Step - browser is open");
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is : "+projectPath);
		
		HooksDemoSteps.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		HooksDemoSteps.driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		//HooksDemoSteps.driver.manage().window().maximize();
		
	}

	@And("user is on login page")
	public void user_is_on_login_page() {
		
		HooksDemoSteps.driver.navigate().to("https://example.testproject.io/web/");
		
	}

	@When("^user enters (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) throws InterruptedException  {
		
		login = new LoginPage_PF(HooksDemoSteps.driver);
		
		login.enterUsername(username);
		login.enterPassword(password);
		
		// HooksDemoSteps.driver.findElement(By.id("name")).sendKeys(username);
		// HooksDemoSteps.driver.findElement(By.id("password")).sendKeys(password);
		
		Thread.sleep(2000);
	}
	
	@And("user clicks on login")
	public void user_clicks_on_login() {
		
		login.clickOnLogin();
		// HooksDemoSteps.driver.findElement(By.id("login")).click();
	}


	@Then("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() throws InterruptedException {
		
		home.checkLogoutIsDisplayed();
		// HooksDemoSteps.driver.findElement(By.id("logout")).isDisplayed();
		
		Thread.sleep(2000);
		HooksDemoSteps.driver.close();
		HooksDemoSteps.driver.quit();
		
	}




}
