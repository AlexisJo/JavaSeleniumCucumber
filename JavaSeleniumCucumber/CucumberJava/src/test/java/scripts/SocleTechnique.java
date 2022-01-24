package scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;

import StepsForHooks.HooksDemoSteps;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class SocleTechnique {

	public static void renseignerChamps(String s, String locator) throws Exception {
	    if (s == null){
	        s = ""; }  
	    WebElement element = locatorToFind(locator);
	    element.clear();
	    element.sendKeys(s);
	}
	
	public static void cliquerSur(String locator) throws Exception {
		
		WebElement element = locatorToFind(locator);
		element.click();
	}
	
	public static void attendreSecondes(int secondes) throws Exception {
		Thread.sleep(secondes);
	}
	
	public static void appuyerSurEntree(String locator) throws Exception {
	    WebElement element = locatorToFind(locator);
	    element.clear();
	    element.sendKeys(Keys.ENTER);
	}
	
	public static void cliquerSurSiBesoin(String locator) throws Exception {
		
		try {
		WebElement element = locatorToFind(locator);
		element.click();
		}
		catch(Exception e) {
			System.out.println("Nothing to click");
			System.out.println(locator);
		}
	}

	//string = ''.join([str(item) for item in json_files])

	// key="Champ Recherche"

	public static WebElement locatorToFind(String keyVal) {
				String valFinal = "";
		
			    // search all json files in directory
				File folder = new File("src/test/java/pages/json");
				File[] listOfFiles = folder.listFiles();
				
				for(var i=0; i<listOfFiles.length; i++) {
					 
				        // parsing file "JSONExample.json"
				        
						try {
							Object obj = new JSONParser().parse(new FileReader(listOfFiles[i]));
							// typecasting obj to JSONObject
					        JSONObject jo = (JSONObject) obj;
					        try {
						        // getting firstName and lastName
						        valFinal = (String) jo.get(keyVal);
						        System.out.println("No error happened. The value of xpath is: " +valFinal);
						        break;}
						        catch (Exception e){
						        	//do nothing if name not in file
						        	System.out.println("An error happened. The value of xpath is: " +valFinal);
						        }
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				}
				
				System.out.println("the value of xpath is: " +valFinal);
				
				WebElement We = HooksDemoSteps.driver.findElement(By.xpath(valFinal));
			
				return We;
			    
 };

	public static boolean isElementPresent(WebElement we, Logger log) {
		boolean resultat = we.isDisplayed();
		if (resultat == false) {
			log.error(we + " indisponible");
		} else {
			log.info(we + " présent");
		}
		return resultat;
	}

	public static int retournerNumeroDeLigne(List<WebElement> liste_lignes, String valeur_connue) {
		int numero_de_ligne = 1;
		for (WebElement ligne : liste_lignes) {
			System.out.println(numero_de_ligne);
			List<WebElement> liste_cellules = ligne.findElements(By.xpath("td"));
			for (WebElement cellule : liste_cellules) {
				if (cellule.getText().equals(valeur_connue)) {
					System.out.println("J'ai trouvé ! " + numero_de_ligne);
					return numero_de_ligne;
				}
			}
			numero_de_ligne++;
		}
		return -1;
	}

	public static WebDriver choisirNavigateur(Logger logger, String navigateur) {
		switch (navigateur) {
		case "f":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			return new FirefoxDriver();
		case "c":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			return new ChromeDriver();
		case "e":
			System.setProperty("webdriver.edge.driver", "src/main/resources/driver/msedgedriver.exe");
			return new EdgeDriver();
		case "ie":
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			return new InternetExplorerDriver();
		default:
			logger.warn("le navigateur choisi n'existe pas");
			return null;
		}
	}

}