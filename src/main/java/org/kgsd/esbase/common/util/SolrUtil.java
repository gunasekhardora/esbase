package org.kgsd.esbase.common.util;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import static org.kgsd.esbase.common.Constants.SOLR_URL;

public class SolrUtil {
    private static SolrClient solrClient;

    public static SolrClient getClient() {
        if (solrClient == null) {
            solrClient = new HttpSolrClient.Builder().withBaseSolrUrl(SOLR_URL)
                    .build();
        }
        return solrClient;
    }
}
