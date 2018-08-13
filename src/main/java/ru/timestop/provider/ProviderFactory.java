package ru.timestop.provider;

import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Factory for produce provider for work with DB
 *
 * @author NikolaevAS
 */
public class ProviderFactory {

    private static EntityManagerFactory entityManager;

    private static final Logger LOG = Logger.getLogger(ProviderFactory.class.getName());

    static {
        try {
            entityManager = Persistence.createEntityManagerFactory("store");
            LOG.info("entityManager=" + entityManager);
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private ProviderFactory() {
    }

    public static void close() {
        entityManager.close();
    }

    public static CatProvider getCatProvider() {
        return new CatProviderImpl(entityManager.createEntityManager());
    }

    public static ProdProvider getProdProvider() {
        return new ProdProviderImpl(entityManager.createEntityManager());
    }
}