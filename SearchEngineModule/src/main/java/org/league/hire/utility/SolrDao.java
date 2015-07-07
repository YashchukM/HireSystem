package org.league.hire.utility;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Михайло on 07.07.2015.
 */
public class SolrDao<T> {
    private HttpSolrServer server = null;

    public SolrDao(String solrUrl) {
        server = (HttpSolrServer) SolrServerFactory.getInstance().createServer(solrUrl);
        configureSolr(server);
    }

    public void put(T dao) {
        put(createSingletonSet(dao));
    }

    public void put(Collection<T> dao) {
        try {
            UpdateResponse response = server.addBeans(dao);
            System.out.println("Added document to solr. Time taken: " +
                    response.getElapsedTime() + ". " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putDoc(SolrInputDocument document) {
        putDoc(createSingletonSet(document));
    }

    public void putDoc(Collection<SolrInputDocument> documents) {
        try {
            long startTime = System.currentTimeMillis();
            UpdateRequest request = new UpdateRequest();
            request.setAction(AbstractUpdateRequest.ACTION.COMMIT, false, false);
            request.add(documents);
            UpdateResponse response = request.process(server);
            System.out.println("Added document to solr. Time taken: " +
                    response.getElapsedTime() + ". " + response.toString());
            long endTime = System.currentTimeMillis();
            System.out.println(" , time-taken=" + ((double)(endTime - startTime)) / 1000.00 + " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QueryResponse readAll() {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        QueryResponse response = null;
        try {
            response = server.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return response;
    }

    public SolrDocumentList readAllDocs() {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        //query.addSortField( "price", SolrQuery.ORDER.asc );
        QueryResponse rsp = null;
        try {
            rsp = server.query( query );
        } catch (SolrServerException e) {
            e.printStackTrace();
            return null;
        }
        SolrDocumentList docs = rsp.getResults();
        return docs;
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
