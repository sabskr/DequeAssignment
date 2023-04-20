package Deque;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.naming.OperationNotSupportedException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.deque.html.axecore.extensions.WebDriverExtensions;
import com.deque.html.axecore.results.Results;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Deque_Assignment {
	
	WebDriver driver;
@BeforeMethod(alwaysRun = true)	
public void initialization() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\browser\\chromedriver.exe");	
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://dequeuniversity.com/demo/mars");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
	@Test(priority=1,enabled = true)
	public void Validation_mainNav_Loaded(){
		WebElement mainNav = driver.findElement(By.xpath("//nav[@id='main-nav']"));
		Assert.assertEquals(mainNav.isDisplayed(), true);	
		}
		
	
	@Test(priority = 2,enabled=true)
	public void numberOf_RadioButtons() {
		List<WebElement> radio = driver.findElements(By.xpath("//input[@name='widget-type']"));
		int size = radio.size();
		Assert.assertEquals(size, 5);	
	}
	
	@Test(priority = 3,enabled=true)
	public void add_Traveler() {
		int i;
	WebElement btnAddAtraveller = driver.findElement(By.xpath("//a[@class='add-traveler']"));
	for(i=1;i<=5;i++) {
		btnAddAtraveller.click();
		WebElement passenger = driver.findElement(By.xpath("//div[@id='r-passenger"+ i +"']"));
		Assert.assertEquals(passenger.isDisplayed(),true);
	}
	
	}
	
	@Test(priority = 4,enabled=true)
	public void heading_validation() {
		WebElement btnNextArrow = driver.findElement(By.xpath("//i[@class='vid-next icon-video-right-arrow']"));
		for(int i=0; i<=2; i++) {
			if(i == 0) {
				WebElement text = driver.findElement(By.xpath("//h3[text()='Life was possible on Mars']"));
				Assert.assertEquals(text.getText(),"Life was possible on Mars");	
			}	
		
		if(i == 1) {
			btnNextArrow.click();
			WebElement text = driver.findElement(By.xpath("//h3[text()='Why Mars died']"));
			Assert.assertEquals(text.getText(),"Why Mars died");
		}	
		
		if(i == 2) {
			btnNextArrow.click();
			WebElement text = driver.findElement(By.xpath("//h3[text()='The world that never was']"));
			Assert.assertEquals(text.getText(),"The world that never was");
		}	
		}
	}
		
		@Test(priority = 5,enabled=true)
		public void accessibility_test() throws OperationNotSupportedException, IOException {
	        Results axeResult = WebDriverExtensions.analyze(driver);
			ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String jsonReport = writer.writeValueAsString(axeResult);
			Assert.assertEquals(axeResult.violationFree(), jsonReport);
			}	
		
		
		@AfterMethod(alwaysRun = true)	
		public void close_Browser() {
			driver.quit();
			
		}
		
		
	}
	
	
	
	
	
	


