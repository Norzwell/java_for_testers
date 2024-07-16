package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.security.PublicKey;

public class SessionHelper extends HelperBase{

    public SessionHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));

    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    //регистрация нового пользователя
    public void logInToTheAccountCreated(String username, String email) {
        click(By.xpath("//a[@class=\"back-to-login-link pull-left\"]"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void openPage(String url) {
        manager.driver().get(url);
    }

    public void endOfRegistration (String username, String password) {
            type(By.id("realname"), username);
            type(By.id("password"), password);
            type(By.id("password-confirm"), password);
            click(By.xpath("//button[@type='submit']"));

    }
}
