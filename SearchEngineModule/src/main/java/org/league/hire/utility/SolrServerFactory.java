package org.league.hire.utility;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Myhailo on 07.07.2015.
 */
public class SolrServerFactory {
    private Map<String, SolrServer> urlToServer = new ConcurrentHashMap<String, SolrServer>();
    private static SolrServerFactory instance = new SolrServerFactory();

    public static SolrServerFactory getInstance() {
        return instance;
    }

    private SolrServerFactory() {}

    public SolrServer createServer(String solrURL) {
        if (urlToServer.containsKey(solrURL))
            return urlToServer.get(solrURL);

        SolrServer server = new HttpSolrServer(solrURL);
        urlToServer.put(solrURL, server);
        return server;
    }
}
