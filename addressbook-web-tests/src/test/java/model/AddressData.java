package model;

public record AddressData(String id, String firstName, String lastName, String address, String mobile, String email,
                          String photo) {

    public AddressData() {
        this("", "", "", "", "", "", "");
    }

    public AddressData withId(String id) {
        return new AddressData(id, this.firstName, this.lastName, this.address, this.mobile, this.email, this.photo);
    }
        public AddressData withFirsName(String firstName) {
        return new AddressData(this.id, firstName, this.lastName, this.address, this.mobile, this.email, this.photo);
    }
        public AddressData withLastName(String lastName) {
        return new AddressData(this.id, this.firstName, lastName, this.address, this.mobile, this.email, this.photo);
    }
    public AddressData withAddress(String address) {
        return new AddressData(this.id, this.firstName, this.lastName, address, this.mobile, this.email, this.photo);
    }
    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, mobile, this.email, this.photo);
    }
    public AddressData withEmail(String email) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, email, this.photo);
    }

    public AddressData withPhoto(String photo) {
        return new AddressData(this.id, this.firstName, this.lastName, this.address, this.mobile, this.email, photo);
    }
}
