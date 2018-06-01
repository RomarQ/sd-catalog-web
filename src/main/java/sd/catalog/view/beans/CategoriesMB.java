package sd.catalog.view.beans;

import sd.catalog.model.Category;
import sd.catalog.repository.CategoryRepository;
import sd.catalog.service.CategoryService;
import sd.catalog.view.utils.FacesUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CategoriesMB {

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CategoryService categoryService;

    private List<Category> categories;

    @PostConstruct
    @PostUpdate
    @PostRemove
    public void init() {
        loadCategories();
    }

    public void loadCategories() {
        categories = categoryRepository.findAll();
    }

    public void removeCategory(Category c) {
        categoryService.remove(c);

        loadCategories();

        FacesUtils.addMessageSaveSuccess();
    }

    public void saveCategory(Category c) {

        if ( c.getId() == null)
            categoryService.persist(c);
        else
            categoryService.update(c);

        loadCategories();

        FacesUtils.addMessageSaveSuccess();
    }

    public long totalProducts(Category c) {
        return categoryRepository.totalProductsByCategory(c);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<String> getCategoryNames(boolean modal) {

        List<Category> categories = getCategories();

        List<String> list = new ArrayList<>();

        if (!modal)
            list.add(0, "All");

        if( categories == null ) return list;

        for(Category c : getCategories()) {
            list.add(c.getName());
        }

        return list;
    }

}
