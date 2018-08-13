package ru.timestop.provider;

import org.apache.log4j.Logger;
import ru.timestop.objects.Prod;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 * @author Tor
 * @version 1.0.0
 * @since 13.08.2018
 */
public class ProdProviderImpl implements ProdProvider {

    private final static Logger LOG = Logger.getLogger(ProviderFactory.class.getName());

    private EntityManager entityManager;

    ProdProviderImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Prod> find(String CatName, String ProdName, Double minPrice,
                           Double maxPrice) {
        if (CatName == null && ProdName == null && minPrice == null && maxPrice == null) {
            return Collections.EMPTY_LIST;
        }
        TypedQuery<Prod> query = (TypedQuery<Prod>) entityManager.createNamedQuery(
                "Prod.findProducts", Prod.class);
        query.setParameter("cat_name", CatName + "%");
        query.setParameter("prod_name", ProdName + "%");
        query.setParameter("min_price", (minPrice == null) ? (0)
                : (minPrice));
        query.setParameter("max_price",
                (maxPrice == null) ? (Double.MAX_VALUE) : (maxPrice));
        return query.getResultList();
    }

    @Override
    public int insertProd(Prod newProd) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newProd);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (EntityExistsException e) {
            LOG.warn(e);
            entityManager.getTransaction().rollback();
        } catch (Throwable e) {
            LOG.error(e);
            try {
                entityManager.getTransaction().rollback();
            } catch (Throwable e1) {
                // NOTHING_TODO
            }
        }
        return newProd.getId();
    }

    @Override
    public void deleteProd(Prod oldProd) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(oldProd);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (EntityExistsException e) {
            LOG.warn(e);
            entityManager.getTransaction().rollback();
        } catch (Throwable e) {
            LOG.error(e);
            try {
                entityManager.getTransaction().rollback();
            } catch (Throwable e1) {
                // NOTHING_TODO
            }
        }
    }

    @Override
    public void updateProd(Prod oldProd) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(oldProd);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (EntityExistsException e) {
            LOG.warn(e);
            entityManager.getTransaction().rollback();
        } catch (Throwable e) {
            LOG.error(e);
            try {
                entityManager.getTransaction().rollback();
            } catch (Throwable e1) {
                // NOTHING_TODO
            }
        }
    }
}
