package sd.catalog.view.beans;

import sd.catalog.SessionBeanEJB;
import sd.catalog.customExeception.CustomMessageException;
import sd.catalog.model.Category;
import sd.catalog.model.Product;
import sd.catalog.model.User;
import sd.catalog.model.UserRole;
import sd.catalog.repository.CategoryRepository;
import sd.catalog.repository.ProductRepository;
import sd.catalog.service.ProductService;
import sd.catalog.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProductsMB {

    @Inject
    private SessionBeanEJB sessionBean;

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

    public void loadMyProducts() { products = productRepository.findByUser(sessionBean.getActiveUser()); }

    public List<Product> getProducts(String cat) {

        if(cat.equalsIgnoreCase("All"))
            loadProducts();
        else {
            Category category = categoryRepository.findByName(cat);
            loadProductsByCategory(category);
        }
        return products;
    }

    public List<Product> getMyProducts() {
       loadMyProducts();
       return products;
    }

    public void removeProduct(Product p) {

        User user = sessionBean.getActiveUser();

        if (!(user.getRole().equals(UserRole.ADMIN) || user.getId() != p.getSeller().getId()))
            throw new CustomMessageException("You don't have permissions to remove this product!");

        productService.remove(p);

        loadProducts();

        FacesUtils.addMessageSuccess("Product "+p.getName()+" was successfully removed!");
    }

    public void saveProduct(Product p) {

        User user = sessionBean.getActiveUser();

        if (!(user.getRole().equals(UserRole.ADMIN) || user.getId() == p.getSeller().getId())) {
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
