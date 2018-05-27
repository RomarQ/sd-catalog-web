package sd.catalog.view.beans;

import sd.catalog.model.Product;
import sd.catalog.repository.ProductRepository;
import sd.catalog.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class productsMB {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductService productService;

    private List<Product> products;

    private Product selectedProduct;

    @PostConstruct
    public void init() { loadProducts(); }

    public void loadProducts() {
        products = productRepository.findProducts();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }
}
