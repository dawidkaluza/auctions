package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.modules.user.UserAlreadyExistsException;
import eu.horyzont.auctions.modules.user.UserService;
import eu.horyzont.auctions.web.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegistration(Model model) {
        model.addAttribute("form", new RegistrationForm());
        return "user/registration/registration";
    }

    @PostMapping("/register")
    public String postRegistration(
        @ModelAttribute("form") @Valid RegistrationForm form,
        BindingResult result
    ) {
        if(result.hasErrors()) {
            return "user/registration/registration";
        }

        try {
            userService.register(form);
        } catch (UserAlreadyExistsException e) {
            return error(
                result, "email",
                "Email already exists", "user/registration/registration"
            );
        }

        return "redirect:/login";
    }

    private String error(BindingResult result, String name, String message, String template) {
        result.addError(
            new ObjectError(
                name,
                message
            )
        );
        return template;
    }
}
