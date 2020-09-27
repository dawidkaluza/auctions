package eu.horyzont.auctions.web.controllers.myAccount;

import eu.horyzont.auctions.core.EntityDoesntExistException;
import eu.horyzont.auctions.modules.payment.BankAccount;
import eu.horyzont.auctions.modules.payment.BankAccountService;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.BankAccountForm;
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
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("user/my-account/bank-account/add")
    public String addBankAccountGet(Model model, Authentication authentication) {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        model.addAttribute("form", new BankAccountForm());
        return "user/my-account/add-bank-account";
    }

    @PostMapping("user/my-account/bank-account/add")
    public String addBankAccountPost(
        @ModelAttribute("form") @Valid BankAccountForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            ControllerUtils.addUserToModel(model, user);
            return "user/my-account/add-bank-account";
        }

        bankAccountService.addBankAccount(form, user);
        return "redirect:/user/my-account";
    }

    @GetMapping("user/my-account/bank-account/{id}/edit")
    public String editBankAccountGet(
        @PathVariable("id") Long id,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        BankAccount account = bankAccountService
            .findById(id)
            .orElseThrow(() -> new EntityDoesntExistException(BankAccount.class, id));
        model.addAttribute("form", new BankAccountForm(account));
        return "user/my-account/edit-bank-account";
    }

    @PostMapping("user/my-account/bank-account/edit")
    public String editBankAccountPost(
        @ModelAttribute("form") @Valid BankAccountForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            ControllerUtils.addUserToModel(model, user);
            return "user/my-account/edit-bank-account";
        }

        BankAccount account = bankAccountService
            .findById(form.getId())
            .orElseThrow(() -> new EntityDoesntExistException(BankAccount.class, form.getId()));
        bankAccountService.updateBankAccount(account, form);
        return "redirect:/user/my-account";
    }

    @GetMapping("user/my-account/bank-account/{id}/delete")
    public String deleteBankAccountGet(@PathVariable("id") Long id) {
        bankAccountService
            .findById(id)
            .ifPresent(bankAccountService::delete);
        return "redirect:/user/my-account";
    }
}
