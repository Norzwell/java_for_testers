package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class AddressCreationTests extends TestBase{

    public static List<AddressData> addressProvider() {
        var result = new ArrayList<AddressData>();
        for (var firstname : List.of("test", "firstname")) {
            for (var lastname : List.of("test", "lastname")) {
                for (var address : List.of("test", "address")) {
                    for (var mobile : List.of("mobile", "+79001234567")) {
                        for (var email : List.of("email", "Test@test.ru")) {
                            result.add(new AddressData()
                                    .withFirsName(firstname)
                                    .withLastName(lastname)
                                    .withAddress(address)
                                    .withMobile(mobile)
                                    .withEmail(email));
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new AddressData().withFirsName(randomString(i+5)).withLastName(randomString(i+5)).withAddress(randomString(i+5)).withMobile(randomString(i+10)).withEmail(randomString(i+10)));
            }
        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("addressProvider")
    public void canCreateMultipleAddress(AddressData address) {
        int addressCount = app.address().getCount();
        app.address().creatingAddress(address);

        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount + 1, newAddressCount);
    }

    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>(List.of(
                new AddressData("", "firsname'", "", "", "", "")));
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

/*    @Test
    public void canCreateAddress() {
        int addressCount = app.address().getCount();
        app.address().creatingAddress(new AddressData("Mark", "Fitz", "Russia, Murmansck, Leningradskaya stret, d.000", "+79001234567", "Test@test.ru"));
        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount + 1, newAddressCount);
    }*/

/*    @Test //пятикратный запуск теста
    public void canCreateMultipleAddress() {
        int n = 5;
        int addressCount = app.address().getCount();

        for (int i = 0; i < n; i++) {
            app.address().creatingAddress(new AddressData(randomString(i+5), randomString(i+10), "Russia, Murmansck, Leningradskaya stret, d.000", "+79001234567", "Test@test.ru"));
        }
        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount + n, newAddressCount);
    }*/
}
