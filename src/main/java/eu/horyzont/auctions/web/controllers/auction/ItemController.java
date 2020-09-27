package eu.horyzont.auctions.web.controllers.auction;

import eu.horyzont.auctions.core.EntityDoesntExistException;
import eu.horyzont.auctions.modules.bid.BidService;
import eu.horyzont.auctions.modules.bid.NewBidMustBeGreaterException;
import eu.horyzont.auctions.modules.category.Category;
import eu.horyzont.auctions.modules.category.CategoryService;
import eu.horyzont.auctions.modules.item.Item;
import eu.horyzont.auctions.modules.item.ItemService;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.BidForm;
import eu.horyzont.auctions.web.forms.ItemForm;
import eu.horyzont.auctions.web.utils.ControllerUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {
    private final BidService bidService;
    private final CategoryService categoryService;
    private final ItemService itemService;

    public ItemController(BidService bidService, CategoryService categoryService, ItemService itemService) {
        this.bidService = bidService;
        this.categoryService = categoryService;
        this.itemService = itemService;
    }

    @GetMapping("auctions/item/add")
    public String addItemGet(Model model, Authentication authentication) {
        ControllerUtils.addUserToModelIfExists(model, authentication);
        model.addAttribute("form", new ItemForm());
        addCategories(model);
        return "auction/add-item";
    }

    @PostMapping("auctions/item/add")
    public String addItemPost(
        @ModelAttribute("form") @Valid ItemForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        User user = ControllerUtils.getUser(authentication);
        if (result.hasErrors()) {
            addCategories(model);
            return "auction/add-item";
        }

        Category category = categoryService
            .findById(form.getCategoryId())
            .orElseThrow(() -> new EntityDoesntExistException(Category.class, form.getId()));
        itemService.addItem(form, user, category);
        return "redirect:/auctions";
    }

    @GetMapping("auctions/item/{id}/view")
    public String viewItemGet(
        @PathVariable("id") Long id,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        Item item = itemService
            .findById(id)
            .orElseThrow(() -> new EntityDoesntExistException(Item.class, id));
        ControllerUtils.addUserToModelIfExists(model, authentication);
        model.addAttribute("item", item);
        model.addAttribute("bids", bidService.findBidsOrderedByOfferDesc(item));
        model.addAttribute("bidForm", new BidForm());
        return "auction/view-item";
    }

    @PostMapping("auctions/item/{id}/view")
    public String addBidPost(
        @PathVariable("id") Long id,
        @ModelAttribute("bidForm") @Valid BidForm form,
        BindingResult result,
        Model model,
        Authentication authentication
    ) throws EntityDoesntExistException {
        Item item = itemService
            .findById(id)
            .orElseThrow(() -> new EntityDoesntExistException(Item.class, id));
        User user = ControllerUtils.getUser(authentication);
        ControllerUtils.addUserToModel(model, user);
        model.addAttribute("item", item);
        if (result.hasErrors()) {
            model.addAttribute("bids", bidService.findBidsOrderedByOfferDesc(item));
            return "auction/view-item";
        }

        try {
            bidService.updateBid(form, item, user);
        } catch (NewBidMustBeGreaterException e) {
            ControllerUtils.addFieldError(result, "bidForm", "offer", "Oferta musi być większa od poprzedniej");
        }

        model.addAttribute("bids", bidService.findBidsOrderedByOfferDesc(item));
        return "auction/view-item";
    }

    private void addCategories(Model model) {
        List<Category> categories = categoryService.findAllSubCategories();
        model.addAttribute("categories", categories);
    }

    private void addViewItemData(Model model, Item item) {
        model.addAttribute("item", item);
        model.addAttribute("bids", bidService.findBidsOrderedByOfferDesc(item));
        model.addAttribute("bidForm", new BidForm());
    }
}
