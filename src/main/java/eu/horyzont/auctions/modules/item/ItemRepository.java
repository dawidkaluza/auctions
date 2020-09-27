package eu.horyzont.auctions.modules.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(
        value = "SELECT i FROM item i WHERE i.category.id IN :ids ORDER BY i.id"
    )
    Page<Item> findAllByCategoriesIds(@Param("ids") Set<Long> ids, Pageable pageable);
}
