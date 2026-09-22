package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpackToCart() {
        driver.findElement(addToCartBackpack).click();
    }
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }
}
