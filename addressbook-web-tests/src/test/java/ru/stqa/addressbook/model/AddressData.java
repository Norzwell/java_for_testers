package ru.stqa.addressbook.model;

public record AddressData(String id,
                          String firstName,
                          String lastName,
                          String address,
                          String mobile,
                          String email,
                          String email2,
                          String email3,
                          String photo,
                          String home,
                          String work,
                          String secondary) {

    public AddressData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public AddressData withId(String id) {
        return new AddressData(id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withFirsName(String firstName) {
        return new AddressData(this.id, firstName, this.lastName, this.address, this.mobile, this.email, this.email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withLastName(String lastName) {
        return new AddressData(this.id, this.firstName, lastName, this.address, this.mobile, this.email, this.email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withAddress(String address) {
        return new AddressData(this.id, this.firstName, this.lastName, address, this.mobile, this.email, this.email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, mobile, this.email, this.email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withHome(String home) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.email2, this.email3, this.photo, home, this.work, this.secondary);
    }
    public AddressData withWork(String work) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.email2, this.email3, this.photo, this.home, work, this.secondary);
    }
    public AddressData withSecondary(String secondary) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.email2, this.email3, this.photo, this.home, this.work, secondary);
    }
    public AddressData withEmail(String email) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, email, this.email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withEmail2(String email2) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, email2, this.email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withEmail3(String email3) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.email2, email3, this.photo, this.home, this.work, this.secondary);
    }
    public AddressData withPhoto(String photo) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.email2, this.email3, photo, this.home, this.work, this.secondary);
    }
}
