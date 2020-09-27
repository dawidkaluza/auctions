package eu.horyzont.auctions.modules.item;

import eu.horyzont.auctions.modules.category.Category;
import eu.horyzont.auctions.modules.user.User;
import eu.horyzont.auctions.web.forms.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ItemService {
    public final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Page<Item> findAllByCategoriesIds(Set<Long> ids, Pageable pageable) {
        return itemRepository.findAllByCategoriesIds(ids, pageable);
    }

    public Item addItem(ItemForm form, User user, Category category) {
        Item item = new Item(form.getName(), form.getInitialPrice(), form.getAuctionEnd(), user, category);
        itemRepository.save(item);
        return item;
    }
}
