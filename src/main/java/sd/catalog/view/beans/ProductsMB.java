package sd.catalog.view.beans;

import sd.catalog.SessionContext;
import sd.catalog.model.Category;
import sd.catalog.model.Product;
import sd.catalog.model.User;
import sd.catalog.model.UserRole;
import sd.catalog.repository.CategoryRepository;
import sd.catalog.repository.ProductRepository;
import sd.catalog.service.CategoryService;
import sd.catalog.service.ProductService;
import sd.catalog.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProductsMB {

    @Inject
    private SessionContext sessionContext;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductService productService;

    @Inject
    private CategoryRepository categoryRepository;

    private List<Product> products;

    private Product selectedProduct;

    @PostConstruct
    public void init() { loadProducts(); }

    public void loadProducts() {
        products = productRepository.findProducts();
    }

    public void loadProductsByCategory(Category c) { products = productRepository.findByCategory(c); }

    public List<Product> getProducts(String cat) {

        if(cat.equalsIgnoreCase("All"))
            loadProducts();
        else {
            Category category = categoryRepository.findByName(cat);
            loadProductsByCategory(category);
        }
        return products;
    }

    public void removeProduct(Product p) {

        User user = sessionContext.getActiveUser();

        if (!(user.getRole().equals(UserRole.ADMIN) || user.getId() != p.getSeller().getId())) {
            FacesUtils.addInfoMessage("You don't have permissions to remove this product!");
            return;
        }

        productService.remove(p);

        loadProducts();

        FacesUtils.addMessageSuccess("Product "+p.getName()+" was successfully removed!");
    }

    public void saveProduct(Product p) {

        User user = sessionContext.getActiveUser();

        if (!(user.getRole().equals(UserRole.ADMIN) || user.getId() != p.getSeller().getId())) {
            FacesUtils.addInfoMessage("You don't have permissions to update this product!");
            return;
        }

        if (p.getId() == null)
            productService.persist(p);
        else
            productService.update(p);

        loadProducts();

        FacesUtils.addMessageSaveSuccess();
    }

    public List<Product> categoryProducts(int categoryId) {

        Category category = categoryRepository.findById(categoryId);

        if ( category == null )
            return null;

        List<Product> productsByCategory = productRepository.findByCategory(category);

        if ( productsByCategory == null )
            return null;

        return productsByCategory;
    }


    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

}
