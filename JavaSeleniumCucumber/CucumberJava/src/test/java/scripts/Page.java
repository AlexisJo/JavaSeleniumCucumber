package scripts;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import StepsForHooks.HooksDemoSteps;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import java.util.stream.Stream;

public class Page{
	


public static void ouvrirPage(String URL) throws InterruptedException {
	
	HooksDemoSteps.driver.get(URL);
    Thread.sleep(2);
    }

public static void extraireDonnees(String locator, String nom) throws Exception {
    
    WebElement element = SocleTechnique.locatorToFind(locator);
    
    var f = new File("src/files/"+nom+".xlsx");

    try (var wb = new ReadableWorkbook(f)) {

        Sheet sheet = wb.getFirstSheet();

        try (Stream<Row> rows = sheet.openStream()) {

            var it = rows.iterator();

            int i = 0;
            while (it.hasNext()) {

                var row = it.next();
                row.stream().forEach(cell -> System.out.println(cell.getText()));

                i++;
            }
        }
    }
    }
    
}