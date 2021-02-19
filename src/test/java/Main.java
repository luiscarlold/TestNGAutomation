import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class Main {
	String driverPath = "C:\\geckodriver.exe";
	WebDriver driver;
	By Costumer = By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/ul/li[1]/a/span");
	By addNewCostumer = By.xpath("/html/body/div[3]/div[3]/div/form[1]/div[1]/div/a");
	By email = By.id("Email");
	By password = By.id("Password");
	By firstName = By.id("FirstName");
	By lastName = By.id("LastName");
	By gender = By.xpath("//*[@id=\"Gender_Male\"]");
	By dateOfBirth = By.xpath("/html/body/div[4]/div/div/div[2]/table/tbody/tr[3]/td[7]/a");
	By companyName = By.id("Company");
	By taxExemp = By.xpath("//*[@id=\"IsTaxExempt\"]");
	By newsletter = By.xpath(
			"/html/body/div[3]/div[3]/div/form/div[3]/div/nop-panels/nop-panel/div/div[2]/div[1]/div[9]/div[2]/div/div[1]/div");
	By textArea = By.id("AdminComment");
	By btnSave = By.xpath("/html/body/div[3]/div[3]/div/form/div[1]/div/button[1]");

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://admin-demo.nopcommerce.com/login");
	}

	@Test
	public void Tarea1() throws InterruptedException {
		// Login
		driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/input")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		// Explicit wait para sidebar Costumer.
		WebElement button = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/a/span")));
		button.click();

		// Validamos si Costumer es visible.
		if (driver.findElement(Costumer).isDisplayed()) {
			driver.findElement(Costumer).click();
			Thread.sleep(1000);

			// Explicit wait para el botón de Agregar costumer.
			WebElement addNCostumer = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/form[1]/div[1]/div/a")));
			addNCostumer.click();
			Thread.sleep(1000);

			// Assert para validar que estamos en el modulo de agregar Customers.
			String ActualTitle = driver.getTitle();
			String ExpectedTitle = "Add a new customer / nopCommerce administration";
			Assert.assertEquals(ActualTitle, ExpectedTitle);

			// Explicit wait para que carguen los id de los formularios
			WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("Email")));
			email.sendKeys("homework1@test.com");
			driver.findElement(password).sendKeys("123");
			driver.findElement(firstName).sendKeys("Tarea");
			driver.findElement(lastName).sendKeys("Uno");
			driver.findElement(gender).click();
			driver.findElement(By.xpath("//*[@id=\"customer-info\"]/div[2]/div[1]/div[6]/div[2]/span[1]/span/span"))
					.click();
			driver.findElement(dateOfBirth).click();
			driver.findElement(companyName).sendKeys("Centic");
			driver.findElement(taxExemp).click();
			driver.findElement(newsletter).click();
			driver.findElement(By.xpath("//*[@id=\"SelectedNewsletterSubscriptionStoreIds_listbox\"]/li[1]")).click();

			// Select para menejar el dropdown
			Select vendor = new Select(driver.findElement(By.id("VendorId")));
			vendor.selectByIndex(2);

			driver.findElement(textArea).sendKeys("Mi primer tarea del curso de automatización.");

			// Creando la interface del JavascriptExecutor
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement BtnSave = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form/div[1]/div/button[1]"));
			String buttonText = (String) js.executeScript("return arguments[0].innerText", BtnSave);
			js.executeScript("arguments[0].click();", BtnSave);
			js.executeScript("console.log('" + buttonText + "')");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr[1]/td[9]/a")).click();

		} else {
			System.out.println("Test failed");
		}
	}
}
