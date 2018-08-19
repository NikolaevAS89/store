package ru.timestop.provider;

import org.apache.log4j.Logger;
import ru.timestop.objects.Category;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public List<Category> find() {
        TypedQuery<Category> query = entityManager.createNamedQuery("Cat.getAll", Category.class);
        return query.getResultList();
    }

    @Override
    public int insertCat(Category newCategory) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newCategory);
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
        return newCategory.getId();
    }

    @Override
    public void deleteCat(Category oldCategory) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(oldCategory);
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
    public void updateCat(Category newCategory) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(newCategory);
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
