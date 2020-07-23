package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.modules.user.User;
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
import java.util.Optional;

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
        BindingResult result,
        Model model
    ) {
        if(result.hasErrors()) {
            return "user/registration/registration";
        }

        //TODO check repeated passwords in Form maybeee
        if(!form.getPassword().equals(form.getRepeatedPassword())) {
            result.addError(
                new ObjectError(
                    "repeatedPassword",
                    "Podane hasła różnią się"
                )
            );
            return "user/registration/registration";
        }

        //TODO check is email exists with some validator
        Optional<User> optionalUser = userService.findByEmail(form.getEmail());
        if(optionalUser.isPresent()) {
            result.addError(
                new ObjectError(
                    "email",
                    "Istnieje już konto z podanym adresem e-mail"
                )
            );
            return "user/registration/registration";
        }

        User user = new User(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPassword());
        userService.save(user);

        //TODO add notification or sth like that
        return "redirect:/login";
    }
}
