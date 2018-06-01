package sd.catalog.view.beans;

import sd.catalog.SessionContext;
import sd.catalog.model.Category;
import sd.catalog.model.Product;
import sd.catalog.repository.CategoryRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ProductEditorMB {

    @Inject
    private SessionContext sessionContext;

    @Inject
    private CategoryRepository categoryRepository;

    private Product product;

    public void createProduct() {
        product = new Product();
        product.setSeller(sessionContext.getActiveUser());
        product.setName("");
        product.setQuantity(0);
        product.setPrice(0);
    }

    public void editProduct(Product p) { product = p; }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product p) {
        product = p;
    }

    public String getCategory() {

        if (product == null)
            return "";

        if (product.getCategory() == null)
            return "";

        return product.getCategory().getName();
    }

    public void setCategory(String name) {

        Category category = categoryRepository.findByName(name);

        product.setCategory(category);
    }
}
