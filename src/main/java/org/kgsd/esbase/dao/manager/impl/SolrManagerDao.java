package org.kgsd.esbase.dao.manager.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.response.CollectionAdminResponse;
import org.kgsd.esbase.common.util.SolrUtil;
import org.kgsd.esbase.dao.manager.ManagerDao;
import org.kgsd.esbase.exception.ManagerDaoException;

import java.io.IOException;
import java.util.List;

import static org.kgsd.esbase.common.Constants.*;

@Log4j2
public class SolrManagerDao implements ManagerDao {

    @Override
    public void createIndex(String index) throws ManagerDaoException {
        try {
            SolrClient solrClient = SolrUtil.getClient();
            CollectionAdminRequest.Create request = CollectionAdminRequest.createCollection(index, SHARD_COUNT,
                    REPLICA_COUNT);
            CollectionAdminResponse response = request.process(solrClient);
        } catch (SolrServerException | IOException e) {
            log.error(String.format("Failed to create Solr Collection %s", index), e);
            throw new ManagerDaoException(String.format("Failed to create index %s", index), e);
        }
    }

    @Override
    public boolean doesIndexExists(String index) throws ManagerDaoException {
        try {
            SolrClient solrClient = SolrUtil.getClient();
            CollectionAdminRequest.List request = new CollectionAdminRequest.List();
            CollectionAdminResponse response = request.process(solrClient);
            List<String> existingCollections = (List<String>) response.getResponse().get(EXISTING_COLLECTIONS);
            return existingCollections != null && existingCollections.contains(index);
        } catch (SolrServerException | IOException e) {
            log.error(String.format("Failed to check for Solr Collection %s", index), e);
            throw new ManagerDaoException(String.format("Failed to check for index %s", index), e);
        }
    }

    @Override
    public void deleteIndex(String index) throws ManagerDaoException {
        try {
            SolrClient solrClient = SolrUtil.getClient();
            CollectionAdminRequest.Delete request = CollectionAdminRequest.deleteCollection(index);
            CollectionAdminResponse response = request.process(solrClient);
        } catch (SolrServerException | IOException e) {
            log.error(String.format("Failed to delete Solr collection %s", index), e);
            throw new ManagerDaoException(String.format("Failed to delete index %s", index), e);
        }
    }

    @Override
    public List<String> getIndexList() throws ManagerDaoException {
        try {
            SolrClient solrClient = SolrUtil.getClient();
            CollectionAdminRequest.List request = new CollectionAdminRequest.List();
            CollectionAdminResponse response = request.process(solrClient);
            return (List<String>) response.getResponse().get(EXISTING_COLLECTIONS);
        } catch (SolrServerException | IOException e) {
            log.error("Failed to get all Solr Collections", e);
            throw new ManagerDaoException("Failed to get all Solr Collections", e);
        }
    }


}
