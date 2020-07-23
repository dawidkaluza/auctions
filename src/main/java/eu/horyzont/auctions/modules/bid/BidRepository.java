package eu.horyzont.auctions.modules.bid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BidRepository extends JpaRepository<Bid, Long> {
//    @Query(
//        value = "SELECT bid FROM bid WHERE category.id IN :ids",
//        countQuery = "SELECT COUNT(bid.id) FROM bid WHERE category.id IN :ids"
//    )
//    Page<Bid> findAllByIds(@Param("ids") List<Long> ids, Pageable pageable);
}
