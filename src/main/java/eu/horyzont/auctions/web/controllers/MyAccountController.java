package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.modules.payment.BankAccount;
import eu.horyzont.auctions.modules.payment.BankAccountService;
import eu.horyzont.auctions.modules.payment.CreditCard;
import eu.horyzont.auctions.modules.payment.CreditCardService;
import eu.horyzont.auctions.modules.user.Address;
import eu.horyzont.auctions.modules.user.AddressService;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.modules.user.UserService;
import eu.horyzont.auctions.web.data.RequestResult;
import eu.horyzont.auctions.web.forms.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class MyAccountController {
    private final UserService userService;
    private final AddressService addressService;
    private final BankAccountService bankAccountService;
    private final CreditCardService creditCardService;

    @Autowired
    public MyAccountController(UserService userService, AddressService addressService, BankAccountService bankAccountService, CreditCardService creditCardService) {
        this.userService = userService;
        this.addressService = addressService;
        this.bankAccountService = bankAccountService;
        this.creditCardService = creditCardService;
    }

    @GetMapping("user/my-account")
    public String getMyAccount(Model model) {
        //TODO get id from session
        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            model.addAttribute("user", user);
            model.addAttribute("addresses", addressService.findAllByUser(user));
            model.addAttribute("bankAccounts", bankAccountService.findAllByUser(user));
            model.addAttribute("creditCards", creditCardService.findAllByUser(user));
        });

        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/address/add")
    public String getAddAddress(Model model) {
        model.addAttribute("form", new AddressForm());
        return "user/my-account/add-address";
    }

    @PostMapping("user/my-account/address/add")
    public String postAddAddress(
        @ModelAttribute("form") @Valid AddressForm form,
        BindingResult result
    ) {
        if(result.hasErrors()) {
            return "user/my-account/add-address";
        }

        //TODO get id from session
        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            addressService.save(
                new Address(
                    form.getStreet(),
                    form.getZipcode(),
                    form.getCity(),
                    user
                )
            );
        });
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/address/{id}/edit")
    public String getEditAddress(
        @PathVariable("id") Long id,
        Model model
    ) {
        AddressForm form = new AddressForm();
        Optional<Address> optionalAddress = addressService.findById(id);
        if(optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            form.setId(address.getId());
            form.setStreet(address.getStreet());
            form.setZipcode(address.getZipcode());
            form.setCity(address.getCity());
        }
        //TODO throw exception or sth like that if address doesnt exist

        model.addAttribute("form", form);
        return "user/my-account/edit-address";
    }

    @PostMapping("user/my-account/address/{id}/edit")
    public String postEditAddress(
        @PathVariable("id") Long id,
        @ModelAttribute("form") @Valid AddressForm form,
        BindingResult result,
        Model model
    ) {
        if(result.hasErrors()) {
            return "user/my-account/edit-address";
        }

        Optional<Address> optionalAddress = addressService.findById(id);
        if(!optionalAddress.isPresent()) {
            //TODO throw exception or add notification maybee
//            model.addAttribute(
//                "result",
//                new RequestResult("Nie znaleziono podanego adresu", RequestResult.Status.ERROR)
//            );
            return "user/my-account/edit-address";
        }

        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            Address address = optionalAddress.get();
            address.setStreet(form.getStreet());
            address.setZipcode(form.getZipcode());
            address.setCity(form.getCity());
            addressService.save(address);
        });
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/address/{id}/delete")
    public String getDeleteAddress(@PathVariable("id") Long id) {
        Optional<Address> optionalAddress = addressService.findById(id);
        optionalAddress.ifPresent(addressService::delete);
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/bank-account/add")
    public String getAddBankAccount(Model model) {
        model.addAttribute("form", new BankAccountForm());
        return "user/my-account/add-bank-account";
    }

    @PostMapping("user/my-account/bank-account/add")
    public String postAddBankAccount(
        @ModelAttribute("form") @Valid BankAccountForm form,
        BindingResult result
    ) {
        if(result.hasErrors()) {
            return "user/my-account/add-bank-account";
        }

        //TODO get id from session
        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            bankAccountService.save(
                new BankAccount(
                    form.getBankName(),
                    form.getSwiftCode(),
                    form.getIban(),
                    user
                )
            );
        });
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/bank-account/{id}/edit")
    public String getEditBankAccount(
        @PathVariable("id") Long id,
        Model model
    ) {
        BankAccountForm form = new BankAccountForm();
        Optional<BankAccount> optionalBankAccount = bankAccountService.findById(id);
        optionalBankAccount.ifPresent(bankAccount -> {
            form.setId(bankAccount.getId());
            form.setBankName(bankAccount.getBankName());
            form.setIban(bankAccount.getIban());
            form.setSwiftCode(bankAccount.getSwiftCode());
        });
        model.addAttribute("form", new BankAccountForm());
        return "user/my-account/edit-bank-account";
    }

    @PostMapping("user/my-account/bank-account/{id}/edit")
    public String postEditBankAccount(
        @PathVariable("id") Long id,
        @ModelAttribute("form") @Valid BankAccountForm form,
        BindingResult result,
        Model model
    ) {
        if(result.hasErrors()) {
            return "user/my-account/edit-bank-account";
        }

        Optional<BankAccount> optionalBankAccount = bankAccountService.findById(id);
        if(!optionalBankAccount.isPresent()) {
            //TODO throw exception or add notification maybee
//            model.addAttribute(
//                "result",
//                new RequestResult("Nie znaleziono podanego konta", RequestResult.Status.ERROR)
//            );
            return "user/my-account/bank-account-form";
        }

        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBankName(form.getBankName());
            bankAccount.setSwiftCode(form.getSwiftCode());
            bankAccount.setIban(form.getIban());
            bankAccountService.save(bankAccount);
        });
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/bank-account/{id}/delete")
    public String getDeleteBankAccount(@PathVariable("id") Long id) {
        Optional<BankAccount> optionalBankAccount = bankAccountService.findById(id);
        optionalBankAccount.ifPresent(bankAccountService::delete);
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/credit-card/add")
    public String getAddCreditCard(Model model) {
        model.addAttribute("form", new CreditCardForm());
        return "user/my-account/add-credit-card";
    }

    @PostMapping("user/my-account/credit-card/add")
    public String postAddCreditCard(
        @ModelAttribute("form") @Valid CreditCardForm form,
        BindingResult result
    ) {
        if(result.hasErrors()) {
            return "user/my-account/add-credit-card";
        }

        //TODO get id from session
        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            creditCardService.save(
                new CreditCard(
                    form.getNumber(),
                    form.getCvv(),
                    form.getExpireDate(),
                    user
                )
            );
        });
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/credit-card/{id}/edit")
    public String getEditCreditCard(
        @PathVariable("id") Long id,
        Model model
    ) {
        CreditCardForm form = new CreditCardForm();
        Optional<CreditCard> optionalCreditCard = creditCardService.findById(id);
        optionalCreditCard.ifPresent(creditCard -> {
            form.setId(creditCard.getId());
            form.setNumber(creditCard.getNumber());
            form.setCvv(creditCard.getCvv());
            form.setExpireDate(creditCard.getExpireDate());
        });
        model.addAttribute("form", form);
        return "user/my-account/edit-credit-card";
    }

    @PostMapping("user/my-account/credit-card/{id}/edit")
    public String postEditCreditCard(
        @PathVariable("id") Long id,
        @ModelAttribute("form") @Valid CreditCardForm form,
        BindingResult result,
        Model model
    ) {
        if(result.hasErrors()) {
            return "user/my-account/edit-credit-card";
        }

        Optional<CreditCard> optionalCreditCard = creditCardService.findById(id);
        if(!optionalCreditCard.isPresent()) {
            //TODO throw exception or add notification maybee
//            model.addAttribute(
//                "result",
//                new RequestResult("Nie znaleziono podanej karty", RequestResult.Status.ERROR)
//            );
            return "user/my-account/edit-credit-card";
        }

        Optional<User> optionalUser = userService.findById(1L);
        optionalUser.ifPresent(user -> {
            CreditCard creditCard = optionalCreditCard.get();
            creditCard.setNumber(form.getNumber());
            creditCard.setCvv(form.getCvv());
            creditCard.setExpireDate(form.getExpireDate());
            creditCard.setUser(user);
            creditCardService.save(creditCard);
        });
        return "user/my-account/my-account";
    }

    @GetMapping("user/my-account/credit-card/{id}/delete")
    public String getDeleteCreditCard(@PathVariable("id") Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardService.findById(id);
        optionalCreditCard.ifPresent(creditCardService::delete);
        return "user/my-account/my-account";
    }

}
