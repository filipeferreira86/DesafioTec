package TC002;

import org.junit.validator.ValidateWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import func.Campos;
import func.PrtScr;
public class TC002 {

	WebDriver driver;
	Campos campos;
	int prtnu = 1;
	String caso = "TC002";
	PrtScr prt;

	@DataProvider(name = "userLogin")
	public Object[][] createData1() {
		return new Object[][] {
			{ "Demouser" , "abc123"  },
			{ "demouser_", "xyz"     },
			{ "demouser" , "nananana"},
			{ "Demouser" , "abc123"  }
		};
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Filipe/Documents/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automation-sandbox.herokuapp.com/");
		campos = new Campos(driver);
		prtnu = prt.capturar(driver, caso+"/print", prtnu);
		prtnu++;
	}

	@Test(dataProvider = "userLogin")
	public void f(String u, String p) {
		prtnu = campos.preencherCampos(u, p, prtnu, caso);
		String vAlerta = null;
		
		try {
			WebElement alerta = driver.findElement(By.cssSelector(".alert"));
			alerta.isDisplayed();
			vAlerta = alerta.getText();
		} catch (Exception e) {
			Assert.assertTrue(false);
		}

		if(vAlerta!=null) {
			String msg = "Wrong Username or Password";
			Assert.assertEquals(vAlerta, msg, 
					caso + " - Mensagem incorreta sendo ");
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
