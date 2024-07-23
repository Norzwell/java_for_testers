package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
    public WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private AddressHelper address;
    private jdbcHelper jdbc;
    private HibernateHelper hbm;


    private Properties properties;


    public void init(String browser, Properties properties) throws MalformedURLException {
        this.properties = properties;
        if (driver == null) {
            var seleniumServer = properties.getProperty("seleniumServer");
            if ("chrome".equals(browser)) {
                if (seleniumServer != null) {
                    driver = new RemoteWebDriver(new URL(seleniumServer), new ChromeOptions());
                } else {
                    driver = new ChromeDriver();
                }
            } else if ("firefox".equals(browser)) {
                if (seleniumServer != null) {
                    driver = new RemoteWebDriver(new URL(seleniumServer), new FirefoxOptions());
                } else {
                    driver = new FirefoxDriver();
                }
            }else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseurl"));
            driver.manage().window().maximize();
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public AddressHelper address() {
        if (address == null) {
            address = new AddressHelper(this);
        }
        return address;
    }

    public jdbcHelper jdbc() {
        if (jdbc == null) {
            jdbc = new jdbcHelper(this);
        }
        return jdbc;
    }

    public HibernateHelper hbm() {
        if (hbm == null) {
            hbm = new HibernateHelper(this);
        }
        return hbm;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
