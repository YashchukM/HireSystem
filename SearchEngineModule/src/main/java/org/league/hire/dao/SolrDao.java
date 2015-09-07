package org.league.hire.dao;

import org.league.hire.pojo.SolrProduct;

import java.util.Collection;
import java.util.List;

/**
 * Created by Myhailo on 07.07.2015.
 */
public interface SolrDao {
    public void put(SolrProduct product);
    public void put(Collection<SolrProduct> products);

    public List<SolrProduct> readAll(int start, int rows);

    public List<SolrProduct> findByCategory(String category, int start, int row);
    public List<SolrProduct> findByOwner(String owner, int start, int row);
}
