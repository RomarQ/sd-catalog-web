package sd.catalog.repository;

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

    public Product finById(Integer id) {
        return getEntityManager().find(Product.class, id);
    }

}
