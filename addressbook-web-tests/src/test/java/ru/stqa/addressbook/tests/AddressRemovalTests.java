package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;


public class AddressRemovalTests extends TestBase{

    @Test
    public void canRemoveAddress() {
        if (app.hbm().getContatCount() == 0) {
            app.hbm().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru", "", "", "", "", "", ""));
        }
        var oldAddress = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddress.size());
        app.address().removeAddress(oldAddress.get(index));
        var newAddress = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldAddress);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress, expectedList);
    }

    @Test
    public void canRemoveAllAddressAtOnce() {
        if (app.hbm().getContatCount() == 0) {
            app.address().creatingAddress(new AddressData("", "Test", "Testovich", "Test, st.Test, h.Test", "+79001234567", "Test@test.ru", "", "", "src/test/resources/images/avatar.png", "", "", ""));
        }
        app.address().removeAllAddresses();
        Assertions.assertEquals(1, app.address().getCount());
    }


    @Test
    void canRemoveContactInGroup() {
        if (app.hbm().getContatCount() == 0) {
        var contact = new AddressData()
                .withFirsName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Test", "Header", "Footer"));
            var group = app.hbm().getGroupList().get(0);
            app.address().creatingAddressInGroup(contact, group);
        }}

        var groupContact = app.hbm().getGroupList().get(0);
        var oldRelatedContact = app.hbm().getContactsInGroup(groupContact);
        var rnd = new Random();
        var index = rnd.nextInt(oldRelatedContact.size());

        app.address().removeAddressInGroup(oldRelatedContact.get(index), groupContact);
        var newAddress = app.hbm().getContactsInGroup(groupContact);
        var expectedList = new ArrayList<>(oldRelatedContact);
        expectedList.remove(index);
        Assertions.assertEquals(newAddress, expectedList);
    }



//    @Test
//    void canRemoveContactInGroup() {
//            var contact = new AddressData()
//                    .withFirsName(CommonFunctions.randomString(10))
//                    .withLastName(CommonFunctions.randomString(10))
//                    .withPhoto(randomFile("src/test/resources/images"));
//            if (app.hbm().getGroupCount() == 0) {
//                app.hbm().createGroup(new GroupData("", "Test", "Header", "Footer"));
//            }
//            var group = app.hbm().getGroupList().get(0);
//
//            var oldRelated = app.hbm().getContactsInGroup(group);
//            app.address().creatingAddressInGroup(contact, group);
//            var newRelated = app.hbm().getContactsInGroup(group);
//            Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
//
//
//        var groupContact = app.hbm().getGroupList().get(0);
//        var oldRelatedContact = app.hbm().getContactsInGroup(groupContact);
//        var rnd = new Random();
//        var index = rnd.nextInt(oldRelatedContact.size());
//
//        app.address().removeAddressInGroup(oldRelatedContact.get(index), groupContact);
//        var newAddress = app.hbm().getContactsInGroup(groupContact);
//        var expectedList = new ArrayList<>(oldRelatedContact);
//        expectedList.remove(index);
//        Assertions.assertEquals(newAddress, expectedList);
//    }

}