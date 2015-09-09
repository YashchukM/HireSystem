package org.league.hire.dao;

import entity.Item;
import org.league.hire.pojo.SolrProduct;

import java.util.Collection;
import java.util.List;

/**
 * Created by Myhailo on 07.07.2015.
 */
public interface SolrDao {
    public void put(int id, String name, String owner, String category);

    public List<Item> readAll(int start, int rows);

    public List<Item> findByCategory(String category, int start, int rows);
    public List<Item> findByOwner(String owner, int start, int rows);
}
