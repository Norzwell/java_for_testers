package ru.stqa.addressbook.tests;

import org.openqa.selenium.By;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.random.RandomGenerator;

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


        @Test
    void canContactAddInGroup() {

            if (app.hbm().getContatCount() == 0) {
            app.hbm().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru", "src/test/resources/images/avatar.png"));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Test", "Header", "Footer"));
        }

        var oldAddress = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());

        var oldGroups = app.hbm().getGroupList();
        var rndGroups = new Random();
        var indexGroup = rndGroups.nextInt(oldGroups.size());

        var group = app.hbm().getGroupList().get(indexGroup);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.address().addressAddInGroup(oldAddress.get(index), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    void canExclusiveContactAddInGroup() {

        if (app.hbm().getContatCount() == 0) {
            app.hbm().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Test", "Header", "Footer"));
        }

        var oldAddress = app.hbm().getContactList();
        var rnd = new Random();
        var indexAddress = rnd.nextInt(oldAddress.size());
        var contact = app.hbm().getContactList().get(indexAddress);

        var oldGroups = app.hbm().getGroupList();
        var indexGroup = rnd.nextInt(oldGroups.size());
        var group = app.hbm().getGroupList().get(indexGroup);


        var contactInGroup = app.hbm().getContactsInGroup(group).contains(contact);
        if (contactInGroup) {
            app.address().removeAddressInGroup(contact,group);
        }

            var oldRelated = app.hbm().getContactsInGroup(group);
            app.address().addressAddInGroup(contact, group);
            var newRelated = app.hbm().getContactsInGroup(group);
            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}

