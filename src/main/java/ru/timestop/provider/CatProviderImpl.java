package ru.timestop.provider;

import org.apache.log4j.Logger;
import ru.timestop.objects.Cat;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Tor
 * @version 1.0.0
 * @since 13.08.2018
 */
public class CatProviderImpl implements CatProvider {

    private final static Logger LOG = Logger.getLogger(ProviderFactory.class.getName());

    private EntityManager entityManager;

    CatProviderImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Object[]> find() {
        Query query = entityManager.createNamedQuery("Cat.getAll");
        return query.getResultList();
    }

    @Override
    public int insertCat(Cat newCat) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCat);
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
        return newCat.getId();
    }

    @Override
    public void deleteCat(Cat oldCat) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(oldCat);
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
    public void updateCat(Cat newCat) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(newCat);
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
