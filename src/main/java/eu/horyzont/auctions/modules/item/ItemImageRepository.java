package eu.horyzont.auctions.modules.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
    List<ItemImage> findAllByItem(Item item);
}
