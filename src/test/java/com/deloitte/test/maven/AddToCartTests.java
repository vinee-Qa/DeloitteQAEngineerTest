package com.deloitte.test.maven;
import java.io.IOException;

import com.deloitte.test.maven.object.repo.CartPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.deloitte.test.maven.object.Base;
import com.deloitte.test.maven.object.repo.StorePage;

public class AddToCartTests extends Base {
    public WebDriver driver;

    @Test
    public void verifyAddedProductPresentInCart() throws IOException {
        driver = initialiseBrowser();
        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        String addedProduct = cart.getProductName().getText().trim();
        assert (addedProduct.equalsIgnoreCase("Sour Puss Raspberry"));
        driver.quit();
    }

    @Test
    public void verifyTotalPaymentsAndItemsPresentInCart() throws IOException {
        driver = initialiseBrowser();
        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
        driver.quit();
    }

    @Test
    public void verifyClearFunctionaltyInCart() throws IOException {
        driver = initialiseBrowser();
        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        StorePage sp = new StorePage(driver);
        sp.addToCartButton().click();
        js.executeScript("window.scrollBy(0,-1000)");
        sp.goToCart().click();
        CartPage cart = new CartPage(driver);
        cart.getClearButton().click();
        String emptyCartText = cart.getEmptyCart().getText().trim();
        assert (emptyCartText.equalsIgnoreCase("Your cart is empty"));
        driver.quit();
    }
    @Test
    public void verifyIncreasingQuantityInCart() throws IOException {
        driver = initialiseBrowser();
        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
        driver.quit();
    }
    @Test
    public void verifyDecreasingQuantityAndCheckout() throws IOException {
        driver = initialiseBrowser();
        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
        driver.quit();
    }

    @Test
    public void verifyClearFunctionlity() throws IOException {
        driver = initialiseBrowser();
        driver.get("https://react-shooping-cart.netlify.app/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
        driver.quit();
    }
}

