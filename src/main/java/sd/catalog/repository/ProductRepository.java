package sd.catalog.repository;

import sd.catalog.model.Category;
import sd.catalog.model.Product;
import sd.catalog.model.Product_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductRepository extends BaseRepository<Product> {

    public List<Product> findProducts() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);

        cq.orderBy(cb.asc(root.get(Product_.price)));

        TypedQuery<Product> query = getEntityManager().createQuery(cq);

        return query.getResultList();
    }

    public List<Product> findByCategory(Category category) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);

        cq.where(cb.equal(root.get(Product_.category), category));

        TypedQuery<Product> query = getEntityManager().createQuery(cq);

        return query.getResultList();
    }

    public Product findById(Integer id) {
        return getEntityManager().find(Product.class, id);
    }

    public void removeByCategory(Category c) {

        List<Product> list = findByCategory(c);

        for(Product p : list)
            this.remove(p);
    }

}
