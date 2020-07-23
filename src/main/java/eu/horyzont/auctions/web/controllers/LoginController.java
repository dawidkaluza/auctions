package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.modules.user.UserService;
import eu.horyzont.auctions.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("form", new LoginForm());
        return "user/login/login";
    }

    @PostMapping("/login")
    public String postLogin(
        @ModelAttribute("form") @Valid LoginForm form,
        BindingResult result,
        Model model
    ) {
        Optional<User> user = userService.findByEmailAndPassword(form.getEmail(), form.getPassword());
        //TODO add to session or sth
        return "redirect:/";
    }
}
