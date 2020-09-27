package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.modules.user.UserAlreadyExistsException;
import eu.horyzont.auctions.modules.user.UserService;
import eu.horyzont.auctions.web.forms.RegistrationForm;
import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationGet(Model model) {
        model.addAttribute("form", new RegistrationForm());
        return "user/registration/registration";
    }

    @PostMapping("/registration")
    public String registrationPost(
        @ModelAttribute("form") @Valid RegistrationForm form,
        BindingResult result
    ) {
        if(result.hasErrors()) {
            return "user/registration/registration";
        }

        try {
            userService.register(form);
        } catch (UserAlreadyExistsException e) {
            ControllerUtils.addFieldError(result, "form", "email", "Email already exists");
            return "user/registration/registration";
        }

        return "redirect:/login";
    }
}
