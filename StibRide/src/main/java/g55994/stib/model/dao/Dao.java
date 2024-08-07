/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55994.stib.model.dao;

import g55994.stib.model.RepositoryException;
import java.util.List;
import g55994.stib.model.dto.Dto;

/**
 * Data access object of a resource (file, database, web service).
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Data_access_object"> Wikipedia</a>
 *
 * @author jlc
 * @param <K> key of an item.
 * @param <T> item of the resource.
 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     * @throws RepositoryException if the resource can't be accessed.
     */
    List<T> selectAll() throws RepositoryException;

    /**
     * Returns an element based on its key.
     *
     * @param key key of the element to select.
     * @return an element based on its key.
     * @throws RepositoryException if the resource can't be accessed.
     */
    T select(K key) throws RepositoryException;
    
    /**
     * Inserts an element into the resource.
     *
     * @param item item to insert.
     * @return the element's key, usefull when the key is auto-generated.
     * @throws RepositoryException if the resource can't be accessed.
     */
    K insert(T item) throws RepositoryException;

    /**
     * Deletes the item of the specific key from the resource.
     *
     * @param key key of the element to delete.
     * @throws RepositoryException if the resource can't be accessed.
     */
    void delete(K key) throws RepositoryException;

    /**
     * Update an element of the resource. The search of the element is based on
     * its key.
     *
     * @param item item to updateName.
     * @throws RepositoryException if the resource can't be accessed.
     */
    void updateName(T item) throws RepositoryException;
}
