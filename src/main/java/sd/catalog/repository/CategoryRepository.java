package sd.catalog.repository;

import sd.catalog.model.Category;
import sd.catalog.model.Category_;
import sd.catalog.model.Product;
import sd.catalog.model.Product_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoryRepository extends BaseRepository<Category> {

    @PersistenceContext(unitName = "catalog")
    private EntityManager entityManager;

    public List<Category> findAll() {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);

        Root<Category> root = cq.from(Category.class);

        cq.orderBy(cb.asc(root.get(Category_.name)));

        TypedQuery<Category> query = getEntityManager().createQuery(cq);

        List<Category> list = query.getResultList();

        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

    public Category findById(Integer id) {
        return getEntityManager().find(Category.class, id);
    }

    public Category findByName(String name) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);

        Root<Category> root = cq.from(Category.class);

        cq.where(cb.equal(root.get(Category_.name), name));

        TypedQuery<Category> query = getEntityManager().createQuery(cq);

        return query.getSingleResult();

    }

    public Long totalProductsByCategory(Category category) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Product> root = cq.from(Product.class);

        cq.select(cb.count(root));
        cq.where(cb.equal(root.get(Product_.category), category));

        TypedQuery<Long> query = getEntityManager().createQuery(cq);

        return query.getSingleResult();
    }
}
