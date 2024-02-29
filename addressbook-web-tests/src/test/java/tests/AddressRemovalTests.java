package tests;

import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        if (!app.address().thePresenceOfAContactInTheAddressBook()) {
            app.address().creatingAddress(new AddressData("Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru"));
        }
        app.address().removeAddress();
    }
}