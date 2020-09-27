package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    @GetMapping("error")
    public String errorGet(Model model, HttpServletRequest request) {
        int errCode = getErrorCode(request);
        String errMsg = "Undefined error";
        switch(errCode) {
            case 404: {
                errMsg = "404 - resource not found";
                break;
            }

            case 500: {
                errMsg = "500 - internal server error";
                break;
            }
        }
        model.addAttribute("errorMsg", errMsg);
        return "error";
    }

    private int getErrorCode(HttpServletRequest request) {
        return (Integer) request.getAttribute("javax.servlet.error.status_code");
    }
}
