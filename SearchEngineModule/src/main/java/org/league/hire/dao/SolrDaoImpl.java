package org.league.hire.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.league.hire.pojo.SolrProduct;
import org.league.hire.utility.SolrServerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Myhailo on 09.07.2015.
 */
public class SolrDaoImpl implements SolrDao {
    private HttpSolrServer server = null;

    public SolrDaoImpl(String solrUrl) {
        server = (HttpSolrServer) SolrServerFactory.getInstance().createServer(solrUrl);
        configureSolr(server);
    }

    /**
     * Put product into Solr. It will be indexed immediately.
     * @param product product to put into Solr
     */
    public void put(SolrProduct product) {
        put(createSingletonSet(product));
    }

    /**
     * Put collection of products into Solr. They will be indexed immediately.
     * @param products products to put into Solr
     */
    public void put(Collection<SolrProduct> products) {
        try {
            UpdateResponse response = server.addBeans(products);
            System.out.println("Added document to solr. Time taken: " +
                    response.getElapsedTime() + ". " + response.toString());
            server.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Will be removed later. Is used for debug and training.
     * @param start starting number of the result set
     * @param rows number of records to return
     * @return list of all products
     */
    public List<SolrProduct> readAll(int start, int rows) {
        SolrQuery query = new SolrQuery();

        query.setQuery("*:*");
        query.setStart(start);
        query.setRows(rows);

        List<SolrProduct> products = null;
        try {
            QueryResponse response = server.query(query);
            products = response.getBeans(SolrProduct.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * @param category category of product
     * @param start starting number of the result set
     * @param rows number of records to return
     * @return list of products that belong to <code>category</code>
     */
    public List<SolrProduct> findByCategory(String category, int start, int rows) {
        SolrQuery query = new SolrQuery();

        query.setQuery("category:" + category);
        query.setStart(start);
        query.setRows(rows);

        List<SolrProduct> products = null;
        try {
            QueryResponse response = server.query(query);
            products = response.getBeans(SolrProduct.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * @param owner name of person(full or partial)
     * @param start starting number of the result set
     * @param rows number of records to return
     * @return list of products, that belong to <code>owner</code>
     */
    public List<SolrProduct> findByOwner(String owner, int start, int rows) {
        SolrQuery query = new SolrQuery();

        // Search by name with number of word moves allowed equal to number of words in name + 1
        String movesAllowed = "~" + (wordsNumber(owner) + 1);
        query.setQuery("owner:" + "\"" + owner + "\"" + movesAllowed);
        query.setStart(start);
        query.setRows(rows);

        List<SolrProduct> products = null;
        try {
            QueryResponse response = server.query(query);
            products = response.getBeans(SolrProduct.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return products;
    }

    private int wordsNumber(String name) {
        return name.split(" ").length;
    }

    private void configureSolr(HttpSolrServer server) {
        server.setMaxRetries(1);
        server.setConnectionTimeout(5000);
        server.setSoTimeout(1000);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);
        server.setAllowCompression(false);
    }

    private <U> Collection<U> createSingletonSet(U dao) {
        if (dao == null) {
            return Collections.emptySet();
        }
        return Collections.singleton(dao);
    }
}
