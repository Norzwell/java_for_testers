package manager;
import model.AddressData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class AddressHelper extends HalperBase{
    public AddressHelper(ApplicationManager manager) {
        super(manager);
    }

    public void creatingAddress(AddressData address) {  //Создание адреса
        openAddNewPage();
        fillAddressForm(address);
        submitAddressCreation();

    }
    public void removeAddress(AddressData address) { //удаление адреса из списка
        isAddressPresent();
        initCheckBoxAddress(address);
        deleteAddress();
    }

    public void openAddNewPage() {
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));  // нажатие на add new в хедере
        }
    private void submitAddressCreation() {  // нажатие кнопки Enter при создании адреса
        click(By.cssSelector("input:nth-child(75)"));
        click(By.xpath("//*[@id=\"content\"]/div/i/a[2]"));
    ;}
    private void fillAddressForm(AddressData addressData) { //заполнение формы
        type(By.name("firstname"), addressData.firstName());
        type(By.name("lastname"), addressData.lastName());
        type(By.name("address"), addressData.address());
        type(By.name("mobile"), addressData.mobile());
        type(By.name("email"), addressData.email());
    }

    public void isAddressPresent() { // открытие home из хедера
        click(By.cssSelector("#nav > ul > li:nth-child(1) > a"));
    }

    public void initCheckBoxAddress(AddressData address) {  //нажатие чекбокса
        click(By.cssSelector(String.format("input[value='%s']", address.id())));
    }

    public void deleteAddress() {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]")); //удаление контакта
    }

    public boolean thePresenceOfAContactInTheAddressBook() {  //проверка наличия контакта в адресной строке
        isAddressPresent();
        return manager.isElementPresent(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[1]"));
    }

    public int getCount() {
        isAddressPresent();
        return manager.driver.findElements(By.cssSelector("input[type*=checkbox]")).size();
        }

    public void removeAllAddresses() {
        isAddressPresent();
        selectAllAddresses();
        deleteAddress();
    }

    private void selectAllAddresses() {
        var checkboxes = manager.driver.findElements(By.cssSelector("input[type*=checkbox]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<AddressData> getList() {
        var address = new ArrayList<AddressData>();
        var tr = manager.driver.findElements(By.name("entry"));
        for (var trIndex : tr) {
            var lastName = trIndex.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = trIndex.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var checkbox = trIndex.findElement(By.cssSelector("input[type=checkbox]"));
            var id = checkbox.getAttribute("value");
            address.add(new AddressData().withId(id).withFirsName(firstName).withLastName(lastName));
        }
        return address;
    }
}

