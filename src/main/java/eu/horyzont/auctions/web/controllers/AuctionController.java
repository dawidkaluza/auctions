package eu.horyzont.auctions.web.controllers;

import eu.horyzont.auctions.modules.bid.BidService;
import eu.horyzont.auctions.modules.category.CategoryService;
import eu.horyzont.auctions.modules.item.Item;
import eu.horyzont.auctions.modules.item.ItemImage;
import eu.horyzont.auctions.modules.item.ItemImageService;
import eu.horyzont.auctions.modules.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class AuctionController {
    private final BidService bidService;
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final ItemImageService itemImageService;

    @Autowired
    public AuctionController(BidService bidService, CategoryService categoryService, ItemService itemService, ItemImageService itemImageService) {
        this.bidService = bidService;
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.itemImageService = itemImageService;
    }

    @GetMapping("auctions")
    public String getAuctions(
        @PageableDefault Pageable pageable,
        @RequestParam(value = "categories", required = false) Set<Long> categoriesIds,
        Model model
    ) {
        Page<Item> items;
        if(categoriesIds == null) {
            items = itemService.findAll(pageable);
        } else {
            items = itemService.findAllByIds(categoriesIds, pageable);
        }

        model.addAttribute("items", items);
        return "auction/auctions";
    }

    @GetMapping("auctions/item/{id}")
    public String getItem(
        @PathVariable("id") Long id,
        Model model
    ) {
        Optional<Item> optionalItem = itemService.findById(id);
        if(optionalItem.isPresent()) {
            Item item = optionalItem.get();
            model.addAttribute("item", item);
            List<ItemImage> images = itemImageService.findAllByItem(item);
            model.addAttribute("images", images);
        }

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
