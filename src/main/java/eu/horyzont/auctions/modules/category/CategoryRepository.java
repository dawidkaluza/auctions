package eu.horyzont.auctions.modules.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query(
//        value = "SELECT category FROM category WHERE id IN :ids",
//        countQuery = "SELECT COUNT(category.id) FROM category WHERE id IN :ids"
//    )
//    Page<Category> findAllByIds(@Param("ids") List<Long> ids, Pageable pageable);
}
