package eu.horyzont.auctions.web.controllers.myAccount;

import eu.horyzont.auctions.modules.payment.BankAccountService;
import eu.horyzont.auctions.modules.payment.CreditCardService;
import eu.horyzont.auctions.modules.user.AddressService;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountController {
    private final AddressService addressService;
    private final BankAccountService bankAccountService;
    private final CreditCardService creditCardService;

    @Autowired
    public MyAccountController(AddressService addressService, BankAccountService bankAccountService, CreditCardService creditCardService) {
        this.addressService = addressService;
        this.bankAccountService = bankAccountService;
        this.creditCardService = creditCardService;
    }

    @GetMapping("user/my-account")
    public String myAccountGet(Model model, Authentication authentication) {
        User user = ControllerUtils.getUser(authentication);
        ControllerUtils.addUserToModel(model, user);
        model.addAttribute("addresses", addressService.findAllByUser(user));
        model.addAttribute("bankAccounts", bankAccountService.findAllByUser(user));
        model.addAttribute("creditCards", creditCardService.findAllByUser(user));
        return "user/my-account/my-account";
    }
}
