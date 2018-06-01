package sd.catalog.service;

import sd.catalog.model.Category;
import sd.catalog.repository.CategoryRepository;
import sd.catalog.repository.ProductRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class CategoryService {

    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private ProductRepository productRepository;

    @Transactional
    public void persist(Category c) { categoryRepository.persist(c); }

    @Transactional
    public void remove(Category c) {
        productRepository.removeByCategory(c);
        categoryRepository.remove(c);
    }

    @Transactional
    public void update(Category c) { categoryRepository.update(c); }

}
