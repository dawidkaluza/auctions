package eu.horyzont.auctions.modules.user;

import javax.persistence.*;

@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String street;

    @Column(length = 32, nullable = false)
    private String zipcode;

    @Column(length = 64, nullable = false)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    protected Address() {
    }

    public Address(String street, String zipcode, String city, User user) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.user = user;
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
