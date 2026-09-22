package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class CheckoutTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));
    }

    @Test
    public void completeCheckoutSuccessfully() {
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), "1", "Cart should show 1 item");

        inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isItemInCart(), "Item should be in cart");

        cartPage.clickCheckout();
        checkoutPage.fillInfo("Tharindu", "Darshana", "10250");
        checkoutPage.finishCheckout();

        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!",
                "Order confirmation message should appear");
    }

}
