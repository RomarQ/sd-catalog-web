package sd.catalog.view.beans;

import sd.catalog.SessionContext;
import sd.catalog.model.Category;
import sd.catalog.repository.CategoryRepository;
import sd.catalog.service.CategoryService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class CategoryEditorMB {

    @Inject
    private SessionContext sessionContext;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CategoryService categoryService;

    private Category category;

    public void createCategory() {
        category = new Category();
        category.setName("");
        category.setDescription("");
        category.setColor("#ffffff");
    }

    public void editCategory(Category c) {
        category = c;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category c) {
        category = c;
    }

}
