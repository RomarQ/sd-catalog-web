package sd.catalog.service;

import sd.catalog.model.Product;
import sd.catalog.repository.ProductRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class ProductService {

    @Inject
    private ProductRepository productRepository;

    @Transactional
    private void remove(Product product) {
        productRepository.remove(product);
    }

    @Transactional
    private void persist(Product product) {
        productRepository.persist(product);
    }
}
