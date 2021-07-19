package com.deloitte.test.maven.object;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
    public WebDriver driver;

    public WebDriver initialiseBrowser() throws IOException {
        Properties prop = new Properties();


        FileInputStream browserType = new FileInputStream("src/main/resources/data.properties");
        prop.load(browserType);
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
