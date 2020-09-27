package eu.horyzont.auctions.web.controllers.myAccount;

import eu.horyzont.auctions.core.EntityDoesntExistException;
import eu.horyzont.auctions.modules.payment.CreditCard;
import eu.horyzont.auctions.modules.payment.CreditCardService;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.CreditCardForm;
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
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("user/my-account/credit-card/add")
    public String addCreditCardGet(Model model, Authentication authentication) {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        model.addAttribute("form", new CreditCardForm());
        return "user/my-account/add-credit-card";
    }

    @PostMapping("user/my-account/credit-card/add")
    public String addCreditCardPost(
        @ModelAttribute("form") @Valid CreditCardForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            ControllerUtils.addUserToModel(model, user);
            return "user/my-account/add-credit-card";
        }

        creditCardService.addCreditCard(form, user);
        return "redirect:/user/my-account";
    }

    @GetMapping("user/my-account/credit-card/{id}/edit")
    public String editCreditCardGet(
        @PathVariable("id") Long id,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        CreditCard creditCard = creditCardService
            .findById(id)
            .orElseThrow(() -> new EntityDoesntExistException(CreditCard.class, id));
        model.addAttribute("form", new CreditCardForm(creditCard));
        return "user/my-account/edit-credit-card";
    }

    @PostMapping("user/my-account/credit-card/edit")
    public String editCreditCardPost(
        @ModelAttribute("form") @Valid CreditCardForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            ControllerUtils.addUserToModel(model, user);
            return "user/my-account/edit-credit-card";
        }

        CreditCard creditCard = creditCardService
            .findById(form.getId())
            .orElseThrow(() -> new EntityDoesntExistException(CreditCard.class, form.getId()));
        creditCardService.updateCreditCard(creditCard, form);
        return "redirect:/user/my-account";
    }

    @GetMapping("user/my-account/credit-card/{id}/delete")
    public String deleteCreditCardGet(@PathVariable("id") Long id) {
        creditCardService
            .findById(id)
            .ifPresent(creditCardService::delete);
        return "redirect:/user/my-account";
    }
}
