package eu.horyzont.auctions.web.controllers.auction;

import eu.horyzont.auctions.modules.item.Item;
import eu.horyzont.auctions.modules.item.ItemService;
import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class AuctionController {
    private final ItemService itemService;

    @Autowired
    public AuctionController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("auctions")
    public String auctionsGet(
        @PageableDefault Pageable pageable,
        @RequestParam(value = "categories", required = false) Set<Long> categoriesIds,
        Model model,
        Authentication authentication
    ) {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        Page<Item> items;
        if (categoriesIds == null) {
            items = itemService.findAll(pageable);
        } else {
            items = itemService.findAllByCategoriesIds(categoriesIds, pageable);
        }

        model.addAttribute("items", items);
        return "auction/auctions";
    }
}
