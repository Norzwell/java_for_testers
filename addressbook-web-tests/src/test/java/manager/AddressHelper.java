package manager;
import model.AddressData;
import org.openqa.selenium.By;

public class AddressHelper extends HalperBase{
    public AddressHelper(ApplicationManager manager) {
        super(manager);
    }

    public void creatingAddress(AddressData address) {  //Создание адреса
        openAddNewPage();
        fillAddressForm(address);
        submitAddressCreation();
    }
    public void removeAddress() { //удаление адреса из списка
        isAddressPresent();
        initCheckBoxAddress();
        deleteAddress();
    }

    public void openAddNewPage() {
        click(By.xpath("//*[@id='nav']/ul/li[2]/a"));  // нажатие на add new в хедере
        }
    private void submitAddressCreation() {  // нажатие кнопки Enter при создании адреса
        click(By.cssSelector("input:nth-child(75)"));
    }
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

    public void initCheckBoxAddress() {  //нажатие чекбокса
        click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[1]"));
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
}

