package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.stream.Stream;

public class UserRegistrationTestThroughDeveloperMail extends TestBase{


    // создание пользователя через DeveloperMail
    DeveloperMailUser user;
    @Test
    void canCreateUserDeveloperMail() {

        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.session().logInToTheAccountCreated(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(15));

        var url = app.mail().urlExtraction(message);

        app.session().openPage(url.toString());
        app.session().endOfRegistration(user.name(), password);
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteUser() {
        app.developerMail().deleteUser(user);
    }
}
