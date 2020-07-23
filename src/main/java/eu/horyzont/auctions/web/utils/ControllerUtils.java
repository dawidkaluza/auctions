package eu.horyzont.auctions.web.utils;

import org.springframework.ui.Model;

public class ControllerUtils {
    public static void addNotification(Model model, String notification) {
        model.addAttribute("notification", notification);
    }
}
