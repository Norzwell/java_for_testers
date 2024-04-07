package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        if (app.address().getCount() == 0) {
            app.address().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru", ""));
        }
        var oldAddress = app.address().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddress(oldAddress.get(index));
        var newAddress = app.address().getList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress, expectedList);
    }

    @Test
    public void canRemoveAllAddressAtOnce() {
        if (app.address().getCount() == 0) {
            app.address().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru", ""));
        }
        app.address().removeAllAddresses();
        Assertions.assertEquals(1, app.address().getCount());
    }
}