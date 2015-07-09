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

    public void put(SolrProduct product) {
        put(createSingletonSet(product));
    }

    public void put(Collection<SolrProduct> products) {
        try {
            UpdateResponse response = server.addBeans(products);
            System.out.println("Added document to solr. Time taken: " +
                    response.getElapsedTime() + ". " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
