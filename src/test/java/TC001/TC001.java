package TC001;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import func.Campos;
import func.PrtScr;

public class TC001 {

	WebDriver driver;
	Campos campos;
	String caso = "TC001";
	int prtnu = 1;
	PrtScr prt;

	@DataProvider(name = "userLogin")
	public Object[][] createData1() {
		return new Object[][] {
			{ "demouser", "abc123"}
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
	}

	@Test(dataProvider = "userLogin")
	public void f(String u, String p) {
		prtnu = campos.preencherCampos(u, p, prtnu, caso);
		try{
			WebElement invoice = driver.findElement(By.cssSelector("h2.mt-5"));
			invoice.isDisplayed();
		}catch(Error e){
			Reporter.log(caso + " - Login falhou");
			Assert.assertTrue(false);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
