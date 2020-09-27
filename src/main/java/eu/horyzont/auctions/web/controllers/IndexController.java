package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String indexGet(Model model, Authentication authentication) {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        return "index";
    }
}
