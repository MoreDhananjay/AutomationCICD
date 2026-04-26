package DUMAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DUMAcademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	public Boolean verifyOrderDisplay(String productName) 
	{
		Boolean match =productNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() 
	{
		checkoutEle.click();
		CheckoutPage checkoutPage =new CheckoutPage(driver);
		return checkoutPage;
	}
}
