package eu.horyzont.auctions.web.utils;

import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.modules.user.UserDetailsImpl;
import eu.horyzont.auctions.web.exceptions.UserIsNotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

public class ControllerUtils {
    public static void addFieldError(BindingResult result, String formName, String fieldName, String defaultMessage) {
        result.addError(
            new FieldError(formName, fieldName, defaultMessage)
        );
    }

    public static void addUserToModelIfExists(Model model, Authentication authentication) {
        try {
            User user = getUser(authentication);
            addUserToModel(model, user);
        } catch (UserIsNotAuthenticatedException e) {
            addUserToModel(model, null);
        }
    }

    public static User getUser(Authentication authentication) {
        if(authentication == null || authentication.getPrincipal() == null) {
            throw new UserIsNotAuthenticatedException();
        }

        return ((UserDetailsImpl) authentication.getPrincipal()).getUser();
    }

    public static void addUserToModel(Model model, User user) {
        model.addAttribute("user", user);
    }

}
