package ru.timestop.provider;

import ru.timestop.objects.Prod;

import java.util.List;

/**
 * @author Tor
 * @version 1.0.0
 * @see ru.timestop.objects.Prod
 * @since 13.08.2018
 */
public interface ProdProvider {

    /**
     * Add new product
     *
     * @param newProd new product
     * @return id of new product
     */
    int insertProd(Prod newProd);

    /**
     * remove old product
     *
     * @param oldProd old product
     */
    void deleteProd(Prod oldProd);

    /**
     * update product
     *
     * @param oldProd updated category
     */
    void updateProd(Prod oldProd);

    /**
     * Find all products by category, first symbols in name and price area
     *
     * @param CatName  category name
     * @param ProdName first symbols in name
     * @param minPrice lower bound of price area
     * @param maxPrice upper bound of price area
     * @return finded products
     */
    List<Prod> find(String CatName, String ProdName, Double minPrice, Double maxPrice);
}
