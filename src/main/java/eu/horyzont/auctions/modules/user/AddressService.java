package eu.horyzont.auctions.modules.user;

import eu.horyzont.auctions.web.forms.AddressForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAllByUser(User user) {
        return addressRepository.findAllByUser(user);
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void delete(Address address) {
        addressRepository.delete(address);
    }

    public Address addAddress(AddressForm form, User user) {
        Address address = new Address(form.getStreet(), form.getZipcode(), form.getCity(), user);
        addressRepository.save(address);
        return address;
    }

    public void updateAddress(Address address, AddressForm form) {
        address.setStreet(form.getStreet());
        address.setZipcode(form.getZipcode());
        address.setCity(form.getCity());
        addressRepository.save(address);
    }
}
