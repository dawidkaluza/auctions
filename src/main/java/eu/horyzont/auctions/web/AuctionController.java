package eu.horyzont.auctions.web;

import eu.horyzont.auctions.modules.category.Category;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;

@Controller
public class AuctionController {
    @GetMapping("auctions")
    public String getAuctions(
        @PageableDefault Pageable pageable,
        @RequestParam("categories") List<Long> categoriesIds
    ) {
        //TODO how to get list from RequestParam?
        return "auction/auctions";
    }

    @GetMapping("auctions/item/{id}")
    public String getItem(@PathVariable("id") Long id) {
        return "auction/item";
    }

    @PostMapping("auctions/item/add")
    public String postAddItem() {
        //TODO get item from form, save in db and provide result
        return "auction/auctions";
    }

    @PostMapping("auctions/item/{id}/bid/add")
    public String postAddBid(@PathVariable("id") Long id) {
        //TODO get bid from form, save in db and provide result
        return "auction/auctions";
    }

    @PostMapping("auctions/item/{id}/bid/update")
    public String postUpdateBid(@PathVariable("id") Long id) {
        //TODO get bid from form, save in db and provide result
        return "auction/auctions";
    }
}
