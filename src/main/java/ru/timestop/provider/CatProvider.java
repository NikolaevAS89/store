package ru.timestop.provider;

import ru.timestop.objects.Cat;

/**
 * @author Tor
 * @version 1.0.0
 * @since 13.08.2018
 * @see ru.timestop.objects.Cat
 */
public interface CatProvider {
    /**
     * Add new category in table
     * @param newCat new Category of products
     * @return id of new category
     */
    int insertCat(Cat newCat);

    /**
     * Remove some old category with all product
     * @param oldCat old category
     */
    void deleteCat(Cat oldCat);

    /**
     * Update some category
     * @param newCat updated category
     */
    void updateCat(Cat newCat);

}
