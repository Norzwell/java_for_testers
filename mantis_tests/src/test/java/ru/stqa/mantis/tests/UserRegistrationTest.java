package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTest extends TestBase{

    @Test
    void canRegisterUser() {
        //1. нужно создать пользователя на почтовом сервере (JamesHelper)
        //2. заполняем форму создания и отправляем (браузер)
        //3. Ждем почту (MailHelper)
        //4. Берем ссылку из письма
        //5. Проходим по ссылке и завершаем регистрацию (браузер)
        //6. Проверяем что пользователь может залогиниться (HttpSessionHelper)

        // создаем пользователя на почтовом сервере
        var username = CommonFunctions.randomString(15);
        var email = String.format("%s@localhost", username);

        var password = "password";
        app.jamesCli().addUser(email, password);

        // заполняем форму создания и отправляем
        app.session().logInToTheAccountCreated(username, email);

        //ждем письмо на зарегистрированный аккаунт
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);

        //извлекаем ссылку из письма
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            //System.out.println(url);
        }
        System.out.println(url);

        //проходим по ссылке из письма
        app.session().openPage(url);

        // завершаем регистрацию
        app.session().endOfRegistration(username, password);

        //проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());

    }
}
