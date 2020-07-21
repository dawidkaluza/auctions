package eu.horyzont.auctions.web.forms;

import javax.validation.constraints.Size;

public class AddressForm {
    @Size(min = 3, max = 32)
    private String street;

    @Size(min = 3, max = 16)
    private String zipcode;

    @Size(min = 3, max = 32)
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
