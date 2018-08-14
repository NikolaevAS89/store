package ru.timestop.provider;

import ru.timestop.objects.Cat;

import java.util.List;

/**
 * @author Tor
 * @version 1.0.0
 * @see ru.timestop.objects.Cat
 * @since 13.08.2018
 */
public interface CatProvider {

    List<Object[]> find();

    /**
     * Add new category in table
     *
     * @param newCat new Category of products
     * @return id of new category
     */
    int insertCat(Cat newCat);

    /**
     * Remove some old category with all product
     *
     * @param oldCat old category
     */
    void deleteCat(Cat oldCat);

    /**
     * Update some category
     *
     * @param newCat updated category
     */
    void updateCat(Cat newCat);

}
