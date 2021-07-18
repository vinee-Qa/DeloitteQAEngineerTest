package com.deloitte.test.browserstack;

import com.deloitte.test.maven.object.repo.CartPage;
import com.deloitte.test.maven.object.repo.StorePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;

public class AddToCartTestsBrowserStackVC {
    public WebDriver driver;

    @BeforeTest
    public void setup() {
        String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
        String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
        final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("name", "Vineeta's First Test for Deloitte");

        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void verifyAddingSingleProductToCart() throws IOException {

        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        String addedProduct = cart.getProductName().getText().trim();

        //verify if added product is showing up in the cart
        assert (addedProduct.equalsIgnoreCase("Sour Puss Raspberry"));

        //check total items value
        cart.getTotalItems().isDisplayed();
        String totalItemsValue = cart.getTotalItems().getText().trim();

        //check total payments value
        cart.getTotalPayments().isDisplayed();
        assert (totalItemsValue.equalsIgnoreCase("1"));
        cart.getClearButton().click();
        String emptyCartText = cart.getEmptyCart().getText().trim();

        //verify cart is empty
        assert (emptyCartText.equalsIgnoreCase("Your cart is empty"));
    }

    @Test
    public void verifyAddingMultipleProductsToCart() throws IOException {

        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);

        //add a product to cart
        sp.addToCartButton().click();

        //scroll up
        js.executeScript("window.scrollBy(0,-1000)");

        //Add a new product to cart
        sp.addNewProduct().click();
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);

        // increase quantity of first product by 3 times
        for (int i = 0; i < 3; i++) {
            cart.getIncreaseIcon().click();
        }

        String totalItemsValue = cart.getTotalItems().getText();

        //Total Number of product becomes 5
        assert (totalItemsValue.equalsIgnoreCase("5"));
        cart.getTotalPayments().isDisplayed();
        cart.getReduceIcon().isDisplayed();
        cart.getdeleteIcon().isDisplayed();

        //decrease quantity by 2
        for (int i = 0; i < 2; i++) {
            cart.getReduceIcon().click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(cart.getdeleteIcon()));

        // delete the second product
        cart.getdeleteIcon().click();

        //check if first product is removed
        cart.getProductName().isDisplayed();

        wait.until(ExpectedConditions.elementToBeClickable(cart.getCheckoutButton()));
        //click checkout button
        cart.getCheckoutButton().click();

        //check checkout success message displays
        String successMessage = cart.getCheckoutSuccessMessage().getText().trim();
        assert (successMessage.equalsIgnoreCase("Checkout successfull"));

        //check cart is clear
        String clearCart = cart.getEmptyCart().getText().trim();
        assert (clearCart.equalsIgnoreCase("Your cart is empty"));
    }

    @AfterTest(alwaysRun = true)
    public void shutdownDriver() {
        driver.quit();
    }
}
