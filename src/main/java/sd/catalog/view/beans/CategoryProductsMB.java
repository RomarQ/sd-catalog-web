package sd.catalog.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(eager = true)
@ViewScoped
public class CategoryProductsMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int id) {
        this.categoryId = id;
    }

    public String showResult() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params =
                fc.getExternalContext().getRequestParameterMap();
        categoryId = Integer.parseInt(params.get("categoryId"));
        return "/pages/categoryProducts.xhtml";
    }
}
