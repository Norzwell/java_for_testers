package tests;

import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        if (app.address().getCount() == 0) {
            app.address().creatingAddress(new AddressData("Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru"));
        }
        int addressCount = app.address().getCount();
        app.address().removeAddress();
        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount - 1, newAddressCount);
    }

    @Test
    public void canRemoveAllAddressAtOnce() {
        if (app.address().getCount() == 0) {
            app.address().creatingAddress(new AddressData("Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru"));
        }
        app.address().removeAllAddresses();
        Assertions.assertEquals(1, app.address().getCount());
    }
}