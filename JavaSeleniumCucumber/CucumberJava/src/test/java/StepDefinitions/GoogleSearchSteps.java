package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import StepsForHooks.HooksDemoSteps;
import io.cucumber.java.en.*;
import scripts.SocleTechnique;

public class GoogleSearchSteps {

	
	@Given("browser window is open")
	public void browser_is_open() {
		
		System.out.println("Inside Step - browser is open");
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is : "+projectPath);
	}

	@And("user is on google search page")
	public void user_is_on_google_search_page() {
		
		System.out.println("Inside Step - user is on goolge search page");
		
		HooksDemoSteps.startdriver().navigate().to("https://google.com");
		
	}

	@When("user enters a text in search box")
	public void user_enters_a_text_in_search_box() throws Exception {
		
		System.out.println("Inside Step - user enters a text in search box");
		
		SocleTechnique.cliquerSurSiBesoin("Accepter Google Cookies");
		
		HooksDemoSteps.startdriver().findElement(By.name("q")).sendKeys("Automation Step by Step");
		
		Thread.sleep(2000);
	}

	@And("hits enter")
	public void hits_enter() throws InterruptedException {
		
		System.out.println("Inside Step - hits enter");
		
		HooksDemoSteps.startdriver().findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}

	@Then("user is navigated to search results")
	public void user_is_navigated_to_search_results() {
		
		System.out.println("Inside Step - user is navigated to search results");
		
		HooksDemoSteps.startdriver().getPageSource().contains("Online Courses");
		
		HooksDemoSteps.startdriver().close();
		HooksDemoSteps.startdriver().quit();
	}




}
