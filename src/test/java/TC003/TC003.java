package TC003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import func.Campos;
import func.PrtScr;

public class TC003 {

	WebDriver driver;
	Campos campos;
	String caso = "TC003";
	int prtnu = 1;
	PrtScr prt;

	@DataProvider(name = "valores")
	public Object[][] createData1() {
		return new Object[][] {
			{ "Rendezvous Hotel"						},  
			{ "14/01/2018"								},  
			{ "15/01/2018"								},  
			{ "110"										},  
			{ "0875"									},  
			{ "JOHNY SMITH"								},  
			{ "R2, AVENUE DU MAROC"						},  
			{ "123456"									},  
			{ "Superior Double"							},  
			{ "14/01/2018"								},  
			{ "15/01/2018"								},  
			{ "1"										},  
			{ "$150"									},  
			{ "USD $20.90"								},  
			{ "USD $19.00"								},  
			{ "USD $209.00"								}   
		};
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Filipe/Documents/webdriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automation-sandbox.herokuapp.com/");
		campos = new Campos(driver);
		prt.capturar(driver, caso+"/print", prtnu);
		prtnu++;
		prtnu = campos.preencherCampos("demouser", "abc123", prtnu, caso);
		WebElement invoice = driver.findElement(By.cssSelector("h2.mt-5"));
		invoice.isDisplayed();

	}

	@Test
	public void click(){
		WebElement link = driver.findElement(By.cssSelector(".row:nth-child(3) a"));
		link.click();

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<String> abas = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(abas.get(1));
		prt.capturar(driver, caso+"/print", prtnu);
		prtnu++;
	}

	@Test(dataProvider = "valores")
	public void f(String valor) {
		campos.validarCampos(valor, prtnu);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

