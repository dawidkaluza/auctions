package eu.horyzont.auctions.web.controllers.myAccount;

import eu.horyzont.auctions.core.EntityDoesntExistException;
import eu.horyzont.auctions.modules.user.Address;
import eu.horyzont.auctions.modules.user.AddressService;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.AddressForm;
import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("user/my-account/address/add")
    public String addAddressGet(Model model, Authentication authentication) {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        model.addAttribute("form", new AddressForm());
        return "user/my-account/add-address";
    }

    @PostMapping("user/my-account/address/add")
    public String addAddressPost(
        @ModelAttribute("form") @Valid AddressForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            ControllerUtils.addUserToModel(model, user);
            return "user/my-account/add-address";
        }

        addressService.addAddress(form, user);
        return "redirect:/user/my-account";
    }

    @GetMapping("user/my-account/address/{id}/edit")
    public String editAddressGet(
        @PathVariable("id") Long id,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        Address address = addressService
            .findById(id)
            .orElseThrow(() -> new EntityDoesntExistException(Address.class, id));
        model.addAttribute("form", new AddressForm(address));
        return "user/my-account/edit-address";
    }

    @PostMapping("user/my-account/address/edit")
    public String editAddressPost(
        @ModelAttribute("form") @Valid AddressForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            ControllerUtils.addUserToModel(model, user);
            return "user/my-account/edit-address";
        }

        Address address = addressService
            .findById(form.getId())
            .orElseThrow(() -> new EntityDoesntExistException(Address.class, form.getId()));
        addressService.updateAddress(address, form);
        return "redirect:/user/my-account";
    }

    @GetMapping("user/my-account/address/{id}/delete")
    public String deleteAddressGet(@PathVariable("id") Long id) {
        addressService
            .findById(id)
            .ifPresent(addressService::delete);
        return "redirect:/user/my-account";
    }
}
