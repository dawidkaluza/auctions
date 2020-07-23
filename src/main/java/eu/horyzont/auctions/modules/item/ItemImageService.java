package eu.horyzont.auctions.modules.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemImageService {
    private final ItemImageRepository itemImageRepository;

    @Autowired
    public ItemImageService(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    public List<ItemImage> findAllByItem(Item item) {
        return itemImageRepository.findAllByItem(item);
    }
}
