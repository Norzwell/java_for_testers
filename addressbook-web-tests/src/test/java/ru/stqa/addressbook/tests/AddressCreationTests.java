package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressCreationTests extends TestBase{

    public static List<AddressData> addressProvider() throws IOException {
        var result = new ArrayList<AddressData>();
//        for (var firstname : List.of("test", "firstname")) {
//            for (var lastname : List.of("test", "lastname")) {
//                for (var address : List.of("test", "address")) {
//                    for (var mobile : List.of("mobile", "+79001234567")) {
//                        for (var email : List.of("email", "Test@test.ru")) {
//                                result.add(new AddressData()
//                                        .withFirsName(firstname)
//                                        .withLastName(lastname)
//                                        .withAddress(address)
//                                        .withMobile(mobile)
//                                        .withEmail(email)
//                                        .withPhoto(randomFile("src/test/resources/images")));
//                    }
//                }
//            }
//        }
//        Этот блок меняем на чтение данных из файла .json
//        for (int i = 0; i < 5; i++) {
//            result.add(new AddressData().withFirsName(randomString(i+5)).withLastName(randomString(i+5)).withAddress(randomString(i+5)).withMobile(randomString(i+10)).withEmail(randomString(i+10)));
//            }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("address.json"), new TypeReference<List<AddressData>>() {});
        result.addAll(value);
        return result;
    }


    @ParameterizedTest
    @MethodSource("addressProvider")
    public void canCreateMultipleAddress(AddressData address) {
//        int addressCount = app.address().getCount();
        var oldAddress = app.address().getList();
//        var countOldAddress = oldAddress.size();
        app.address().creatingAddress(address);
        var newAddressCount = app.address().getList();
//        var countNewAddress = newAddressCount.size();
        var expectedCountNewAddress = new ArrayList<>(oldAddress);
        Assertions.assertEquals(oldAddress, expectedCountNewAddress);
    }

    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>(List.of(
                new AddressData("", "firsname'", "", "", "", "", "")));
        return result;
    }
    @ParameterizedTest
    @MethodSource ("negativeAddressProvider")
    public void canNotCreateAddress(AddressData address) {
        int addressCount = app.address().getCount();
        app.address().creatingAddress(address);

        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount, newAddressCount);
    }

    @Test
    void canCreateContactPhoto() {
        var contact = new AddressData()
                .withFirsName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))

                //выбрать конкретную картинку
//                .withPhoto("src/test/resources/images/avatar.png");
                .withPhoto(randomFile("src/test/resources/images"));
        app.address().creatingAddress(contact);
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new AddressData()
                .withFirsName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "Test", "Header", "Footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.address().creatingAddressInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
