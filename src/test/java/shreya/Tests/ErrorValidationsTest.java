package shreya.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import shreya.TestComponents.BaseTest;
import shreya.pageobjects.CartPage;
import shreya.pageobjects.CheckoutPage;
import shreya.pageobjects.ConfirmationPage;
import shreya.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";
		landingPage.loginApplication("anshika@gmail.com", "Iamking@00");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingPage.loginApplication("gayatri98@gmail.com", "Gayatri@123");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartPage = productcatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
