package sd.catalog.service;

import sd.catalog.model.Product;
import sd.catalog.repository.ProductRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Transactional
    public void remove(Product product) {
        productRepository.remove(product);
    }

    @Transactional
    public void persist(Product product) {
        productRepository.persist(product);
    }

    @Transactional
    public void update(Product product) { productRepository.update(product); }
}
