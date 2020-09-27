package eu.horyzont.auctions.modules.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class OnApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    private final CategoryRepository categoryRepository;

    @Autowired
    public OnApplicationReadyListener(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            Category category, subCategory;

            category = new Category("Samochody", null);
            category = categoryRepository.save(category);
            subCategory = new Category("Samochody osobowe", category);
            categoryRepository.save(subCategory);
            subCategory = new Category("Samochody dostawcze", category);
            categoryRepository.save(subCategory);

            category = new Category("Komputery", null);
            category = categoryRepository.save(category);
            subCategory = new Category("Komputery stacjonarne", category);
            categoryRepository.save(subCategory);
            subCategory = new Category("Laptopy", category);
            categoryRepository.save(subCategory);
        }
    }
}
