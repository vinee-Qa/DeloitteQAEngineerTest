package com.deloitte.test.maven.object.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstProductName = By.xpath("//*[text()='Sour Puss Raspberry']");
    By clearButton = By.cssSelector("button[class='btn btn-outlineprimary btn-sm']");
    By deleteIconSecondProduct =  By.xpath("//*[text()='Glass Clear 8 Oz']/following::div[2]"
            + "/button[@class='btn btn-danger btn-sm mb-1']");
    By increaseIcon = By.xpath("//h5[text()='Sour Puss Raspberry']/following::div[2]"
            + "/button[@class='btn btn-primary btn-sm mr-2 mb-1']");
    By reduceIcon = By.xpath("//h5[text()='Sour Puss Raspberry']/following::div[2]"
            + "/button[@class='btn btn-danger btn-sm mb-1']");
    By totalItems = By.xpath("//p[contains(.,'Total Items')]/parent::div/h4");
    By totalPayments = By.xpath("//p[contains(.,'Total Items')]/parent::div/h3");
    By emptyCart = By.xpath("//div[@class='p-3 text-center text-muted']");
    By secondProductName = By.xpath("//*[text()='Glass Clear 8 Oz']");
    By checkoutButton = By.xpath("//button[text()='CHECKOUT']");
    By checkoutSuccess = By.xpath("//div[@class='p-3 text-center text-success']/p");


    public WebElement getProductName() {
        return driver.findElement(firstProductName);
    }

    public WebElement getClearButton() {
        return driver.findElement(clearButton);
    }

    public WebElement getdeleteIcon() {
        return driver.findElement(deleteIconSecondProduct);
    }

    public WebElement getIncreaseIcon() {
        return driver.findElement(increaseIcon);
    }

    public WebElement getTotalItems() {
        return driver.findElement(totalItems);
    }

    public WebElement getTotalPayments() {
        return driver.findElement(totalPayments);
    }

    public WebElement getEmptyCart() {
        return driver.findElement(emptyCart);
    }

    public WebElement getReduceIcon() {
        return driver.findElement(reduceIcon);
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(checkoutButton);
    }

    public WebElement getCheckoutSuccessMessage() {
        return driver.findElement(checkoutSuccess);
    }
}
