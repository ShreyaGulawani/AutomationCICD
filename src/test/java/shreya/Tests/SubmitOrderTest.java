package shreya.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import shreya.TestComponents.BaseTest;
import shreya.pageobjects.CartPage;
import shreya.pageobjects.CheckoutPage;
import shreya.pageobjects.ConfirmationPage;
import shreya.pageobjects.LandingPage;
import shreya.pageobjects.OrderPage;
import shreya.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Thread.sleep(3000);
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.GetConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue = landingPage.loginApplication("gayatri98@gmail.com", "Gayatri@123");
		OrderPage orderPage = productcatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>>data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\shreya\\data\\PurchaseOder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	/*
	 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
	 * "gayatri98@gmail.com"); map.put("password", "Gayatri@123");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map2 = new HashMap<String, String>();
	 * map.put("email", "rohanpatil7778@gmail.com"); map.put("password",
	 * "Rohan@1998"); map.put("product", "ZARA COAT 3");
	 * 
	 
	 */
}
