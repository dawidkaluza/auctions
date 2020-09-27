package eu.horyzont.auctions.modules.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    public final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public List<Category> findAllSubCategories() {
        List<Category> categoriesWithCategory = categoryRepository.findAllByCategoryIsNotNull();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categoriesWithCategory) {
            Category overridingCategory = category.getCategory();
            categories.remove(overridingCategory);
        }
        return categories;
    }
//    public Page<Category> findAllByIds(List<Long> ids, Pageable pageable) {
//        return categoryRepository.findAllByIds(ids, pageable);
//    }
}
