package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddressModificationTests extends TestBase {

        @Test
        void canModifyAddress() {
            if (app.hbm().getContatCount() == 0) {
                app.hbm().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru","" ));
            }
            var oldAddress = app.hbm().getContactList();
            var rnd = new Random();
            var index = rnd.nextInt(oldAddress.size());
            var testData = new AddressData().withLastName("modified name").withEmail("TESTOVICH").withPhoto(randomFile("src/test/resources/images"));
            app.address().modifyAddress(oldAddress.get(index), testData);
            var newGroups = app.hbm().getContactList();
            var expectedList = new ArrayList<>(oldAddress);
            expectedList.set(index, testData.withId(oldAddress.get(index).id()));
            Comparator<AddressData> compareById = (o1, o2) -> {
                return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
            };
            newGroups.sort(compareById);
            expectedList.sort(compareById);
            Assertions.assertEquals(newGroups, expectedList);
        }
}

