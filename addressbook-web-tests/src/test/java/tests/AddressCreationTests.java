package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressCreationTests extends TestBase{

    @Test
    public void canCreateAddress() {
        app.address().creatingAddress(new AddressData("Mark", "Fitz", "Russia, Murmansck, Leningradskaya stret, d.000", "+79001234567", "Test@test.ru"));
    }
}
