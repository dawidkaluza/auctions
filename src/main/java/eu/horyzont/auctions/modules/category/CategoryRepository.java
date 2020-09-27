package eu.horyzont.auctions.modules.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryIsNotNull();
}
