package model;

public record AddressData(String firstName, String lastName, String address, String mobile, String email) {

    public AddressData() {
        this("", "", "", "", "");
    }

    public AddressData withFirsName(String firstName) {
        return new AddressData(firstName, this.lastName, this.address, this.mobile, this.email);
    }
}
