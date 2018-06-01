package sd.catalog.view.beans;

import sd.catalog.model.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CategoryEditorMB {

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
