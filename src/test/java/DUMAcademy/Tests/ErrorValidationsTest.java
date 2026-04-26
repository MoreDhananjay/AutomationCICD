package DUMAcademy.Tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;
import DUMAcademy.TestComponents.Retry;

import DUMAcademy.TestComponents.BaseTest;
import DUMAcademy.pageobjects.CartPage;
import DUMAcademy.pageobjects.CheckoutPage;
import DUMAcademy.pageobjects.ConfirmationPage;
import DUMAcademy.pageobjects.Landingpage;
import DUMAcademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		String productName="ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		landingPage.loginApplication("dhananjay1.more@gmail.com", "India@202");
		System.out.println(landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		String productName="ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ProductCatalogue productCatalogue= landingPage.loginApplication("dhananjay1.more@gmail.com", "India@2025");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);		
		CartPage cartPage= productCatalogue.goToCart();	
		Boolean match =cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		
	}

}
