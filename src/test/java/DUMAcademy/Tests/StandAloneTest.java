package DUMAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import DUMAcademy.pageobjects.Landingpage;
import DUMAcademy.pageobjects.ProductCatalogue;

public class StandAloneTest {
 
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName="ZARA COAT 3";
				
		Landingpage landingPage = new Landingpage(driver);
		landingPage.goTo();
		landingPage.loginApplication("dhananjay1.more@gmail.com", "India@2025");
		
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		List<WebElement> products=productCatalogue.getProductList();
				
		WebElement prod=products.stream().filter(p->p.findElement(By.cssSelector("div.mb-3 b"))
		.getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match =cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(true);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions act= new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector("a.action__submit")).click();
		String confirmMessage=driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
}

}
