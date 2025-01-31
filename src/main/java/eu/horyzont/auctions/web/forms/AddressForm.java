package eu.horyzont.auctions.web.forms;

import eu.horyzont.auctions.modules.user.Address;

import javax.validation.constraints.Size;

public class AddressForm {
    private Long id;

    @Size(min = 3, max = 32)
    private String street;

    @Size(min = 3, max = 16)
    private String zipcode;

    @Size(min = 3, max = 32)
    private String city;

    public AddressForm() {
    }

    public AddressForm(Address address) {
        id = address.getId();
        street = address.getStreet();
        zipcode = address.getZipcode();
        city = address.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
