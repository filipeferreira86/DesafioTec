package func;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class Campos {

	WebDriver driver;
	PrtScr prt;
	
	public Campos(WebDriver d) {
		this.driver = d;
	}
	
	public int preencherCampos(String u, String p, int prtnu, String caso){
		WebElement user = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement btn = driver.findElement(By.id("btnLogin"));

		user.sendKeys(u);
		password.sendKeys(p);
		prt.capturar(driver, caso+"/print", prtnu);
		prtnu++;
		btn.click();
		
		prt.capturar(driver, caso+"/print", prtnu);
		prtnu++;
		return prtnu;
	}
	
	public void validarCampos(String valor, int prtnu) {
		
		if(driver.getPageSource().contains(valor)) {
			Assert.assertTrue(true);
		}else {
			Reporter.log(valor + " não encontrado na página");
			Assert.assertTrue(false);
		}
	}

}
