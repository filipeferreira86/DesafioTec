package func;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class PrtScr {
	
	public static int capturar(WebDriver driver, String nomeprt, int prtnu) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			TakesScreenshot ts = (TakesScreenshot)driver;
			
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(source, new File("./evidencias/"+nomeprt+prtnu+".png"));
			prtnu++;
		} catch (Exception e) {
			System.out.println("Erro "+e.toString());
		}
		return prtnu;
	}
	
}
