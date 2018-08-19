package ru.timestop.provider;

import ru.timestop.objects.Category;

import java.util.List;

/**
 * @author Tor
 * @version 1.0.0
 * @see Category
 * @since 13.08.2018
 */
public interface CatProvider {

    List<Category> find();

    /**
     * Add new category in table
     *
     * @param newCategory new Category of products
     * @return id of new category
     */
    int insertCat(Category newCategory);

    /**
     * Remove some old category with all product
     *
     * @param oldCategory old category
     */
    void deleteCat(Category oldCategory);

    /**
     * Update some category
     *
     * @param newCategory updated category
     */
    void updateCat(Category newCategory);

}
