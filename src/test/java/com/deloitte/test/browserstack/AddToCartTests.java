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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddToCartTests extends BrowserStackTestNGTest {

    @Test
    public void verifyAddedProductPresentInCart() throws IOException {
        driver.get("https://react-shooping-cart.netlify.app/");
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        String addedProduct = cart.getProductName().getText().trim();
        assert (addedProduct.equalsIgnoreCase("Sour Puss Raspberry"));
        setTestStatus(addedProduct.equalsIgnoreCase("Sour Puss Raspberry"));
    }

    @Test
    public void verifyTotalPaymentsAndItemsPresentInCart() throws IOException {
        driver.get("https://react-shooping-cart.netlify.app/");
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        cart.getTotalItems().isDisplayed();
        String totalItemsValue = cart.getTotalItems().getText().trim();
        cart.getTotalPayments().isDisplayed();
        assert (totalItemsValue.equalsIgnoreCase("1"));
        setTestStatus(totalItemsValue.equalsIgnoreCase("1"));
    }

    @Test
    public void verifyClearFunctionaltyInCart() throws IOException {
        driver.get("https://react-shooping-cart.netlify.app/");
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        cart.getClearButton().click();
        String emptyCartText = cart.getEmptyCart().getText().trim();
        assert (emptyCartText.equalsIgnoreCase("Your cart is empty"));
        setTestStatus(emptyCartText.equalsIgnoreCase("Your cart is empty"));
    }
    @Test
    public void verifyIncreasingQuantityInCart() throws IOException {
        driver.get("https://react-shooping-cart.netlify.app/");
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.addNewProduct().click();
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        for (int i = 0; i < 3; i++) {
            cart.getIncreaseIcon().click();
        }
        String totalItemsValue = cart.getTotalItems().getText();
        assert (totalItemsValue.equalsIgnoreCase("5"));
        setTestStatus(totalItemsValue.equalsIgnoreCase("5"));
    }
    @Test
    public void verifyDecreasingQuantityAndCheckout() throws IOException {
        driver.get("https://react-shooping-cart.netlify.app/");
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.addNewProduct().click();
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        cart.getTotalPayments().isDisplayed();
        cart.getReduceIcon().isDisplayed();
        cart.getdeleteIcon().isDisplayed();
        for (int i = 0; i < 3; i++) {
            cart.getIncreaseIcon().click();
        }
        String totalItemsValue = cart.getTotalItems().getText();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(cart.getdeleteIcon()));
        cart.getdeleteIcon().click();
        cart.getCheckoutButton().click();
        String successMessage = cart.getCheckoutSuccessMessage().getText().trim();
        assert (successMessage.equalsIgnoreCase("Checkout successfull"));
        setTestStatus(successMessage.equalsIgnoreCase("Checkout successfull"));
    }

    @Test
    public void verifyClearFunctionlity() throws IOException {
        driver.get("https://react-shooping-cart.netlify.app/");
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.addNewProduct().click();
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        for (int i = 0; i < 3; i++) {
            cart.getIncreaseIcon().click();
        }
        String totalItemsValue = cart.getTotalItems().getText();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(cart.getdeleteIcon()));
        cart.getdeleteIcon().click();
        cart.getCheckoutButton().click();
        String successMessage = cart.getCheckoutSuccessMessage().getText().trim();
        String clearCart = cart.getEmptyCart().getText().trim();
        assert (clearCart.equalsIgnoreCase("Your cart is empty"));
        setTestStatus(clearCart.equalsIgnoreCase("Your cart is empty"));
    }
}
