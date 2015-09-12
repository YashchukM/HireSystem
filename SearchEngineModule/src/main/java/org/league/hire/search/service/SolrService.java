package org.league.hire.search.service;

import entity.Item;
import manager.ItemManager;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.league.hire.search.utility.SolrUtility;
import org.league.hire.search.pojo.SolrProduct;
import org.league.hire.search.utility.SolrServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Myhailo on 09.07.2015.
 */
@Service
@Transactional
public class SolrService {

    private HttpSolrServer server = null;
    private String solrUrl;

    @Autowired
    ItemManager itemManager;

    @PostConstruct
    public void init() {
        try {
            solrUrl = SolrUtility.getSolrUrl("http://api.solrhq.com/txt/10e243d3c8a5bd5cfda7face16e76b83/start-session/HireSystem/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        server = (HttpSolrServer) SolrServerFactory.getInstance().createServer(solrUrl);
        configureSolr(server);
    }

    /**
     * Put product into Solr. It will be indexed immediately.
     * @param product product to put into Solr
     */
    private void putSolr(SolrProduct product) {
        putSolr(createSingletonSet(product));
    }

    /**
     * Put collection of products into Solr. They will be indexed immediately.
     * @param products products to put into Solr
     */
    private void putSolr(Collection<SolrProduct> products) {
        try {
            UpdateResponse response = server.addBeans(products);
            System.out.println("Added document to solr. Time taken: " +
                    response.getElapsedTime() + ". " + response.toString());
            server.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(int id, String name, String owner, String category) {
        SolrProduct solrProduct = new SolrProduct(Integer.toString(id),
                name,
                owner,
                category);
        putSolr(solrProduct);
    }

    /**
     * Will be removed later. Is used for debug and training.
     * @param start starting number of the result set
     * @param rows number of records to return
     * @return list of all products
     */
    private List<SolrProduct> readAllSolr(int start, int rows) {
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

    public List<Item> readAll(int start, int rows) {
        List<SolrProduct> productsSolr = readAllSolr(start, rows);
        return convertSolrToItem(productsSolr);
    }

    /**
     * @param category category of product
     * @param start starting number of the result set
     * @param rows number of records to return
     * @return list of products that belong to <code>category</code>
     */
    private List<SolrProduct> findByCategorySolr(String category, int start, int rows) {
        SolrQuery query = new SolrQuery();

        query.setQuery("category_t:" + category);
        query.setStart(start);
        query.setRows(rows);

        System.out.println(query);

        List<SolrProduct> products = null;
        try {
            QueryResponse response = server.query(query);
            products = response.getBeans(SolrProduct.class);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Item> findByCategory(String category, int start, int rows) {
        List<SolrProduct> productsSolr = findByCategorySolr(category, start, rows);
        return convertSolrToItem(productsSolr);
    }

    /**
     * @param owner name of person(full or partial)
     * @param start starting number of the result set
     * @param rows number of records to return
     * @return list of products, that belong to <code>owner</code>
     */
    private List<SolrProduct> findByOwnerSolr(String owner, int start, int rows) {
        SolrQuery query = new SolrQuery();

        // Search by name with number of word moves allowed equal to number of words in name + 1
        String movesAllowed = "~" + (wordsNumber(owner) + 1);
        query.setQuery("owner_t:" + "\"" + owner + "\"" + movesAllowed);
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

    public List<Item> findByOwner(String owner, int start, int rows) {
        List<SolrProduct> productsSolr = findByOwnerSolr(owner, start, rows);
        return convertSolrToItem(productsSolr);
    }

    private int wordsNumber(String name) {
        return name.split(" ").length;
    }

    private void configureSolr(HttpSolrServer server) {
        server.setMaxRetries(1);
        server.setConnectionTimeout(5000);
        server.setSoTimeout(2000);
        server.setDefaultMaxConnectionsPerHost(100);
        server.setMaxTotalConnections(100);
        server.setFollowRedirects(false);
        server.setAllowCompression(false);
        server.setParser(new XMLResponseParser());
    }

    private <U> Collection<U> createSingletonSet(U dao) {
        if (dao == null) {
            return Collections.emptySet();
        }
        return Collections.singleton(dao);
    }

    private List<Item> convertSolrToItem(List<SolrProduct> productsSolr) {
        List<Item> products = new ArrayList<Item>(productsSolr.size());
        for (SolrProduct productSolr : productsSolr) {
            Item product = itemManager.getById(Integer.parseInt(productSolr.getId()));
            products.add(product);
        }

        return products;
    }
}
