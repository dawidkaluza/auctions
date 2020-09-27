package eu.horyzont.auctions.modules.bid;

import eu.horyzont.auctions.modules.item.Item;
import eu.horyzont.auctions.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface BidRepository extends JpaRepository<Bid, Long> {
//    @Query(
//        value = "SELECT bid FROM bid WHERE category.id IN :ids",
//        countQuery = "SELECT COUNT(bid.id) FROM bid WHERE category.id IN :ids"
//    )
//    Page<Bid> findAllByIds(@Param("ids") List<Long> ids, Pageable pageable);
    List<Bid> findAllByItemOrderByOfferDesc(Item item);

    Optional<Bid> findByItemAndUser(Item item, User user);
}
