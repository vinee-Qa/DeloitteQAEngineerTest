package com.deloitte.test.maven.object.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StorePage {
    public WebDriver driver;

    public StorePage(WebDriver driver) {
        this.driver=driver;
    }

    By firstProductAddToCart = By.xpath("//*[text()='Sour Puss Raspberry']//parent::div//div/button");
    By secondProductAddToCart = By.xpath("//*[text()='Glass Clear 8 Oz']//parent::div//div/button");
    By cart = By.cssSelector("a[href='/cart']");

    public WebElement addToCartButton() {
        return driver.findElement(firstProductAddToCart);
    }

    public WebElement goToCart() {
        return driver.findElement(cart);
    }

    public WebElement addNewProduct() {
        return driver.findElement(secondProductAddToCart);
    }
}
