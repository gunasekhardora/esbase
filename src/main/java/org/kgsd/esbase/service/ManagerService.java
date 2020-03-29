package org.kgsd.esbase.service;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.kgsd.esbase.dao.manager.ManagerDao;
import org.kgsd.esbase.exception.ManagerDaoException;
import org.kgsd.esbase.exception.ManagerException;
import org.kgsd.esbase.model.IndexEntry;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ManagerService {
    private ManagerDao managerDao;

    @Inject
    public ManagerService(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public void createIndex(String index) throws ManagerException {
        try {
            if (!managerDao.doesIndexExists(index)) {
                managerDao.createIndex(index);
            } else {
                log.error(String.format("Index %s already exists!!", index));
                throw new ManagerException(String.format("Index %s already exists!!", index));
            }
        } catch (ManagerDaoException e) {
            throw new ManagerException(e);
        }
    }

    public void deleteIndex(String index) throws ManagerException {
        try {
            if (managerDao.doesIndexExists(index)) {
                managerDao.deleteIndex(index);
            } else {
                log.error(String.format("Index %s does not exist to be deleted!!", index));
                throw new ManagerException(String.format("Index %s does not exist to be deleted!!", index));
            }
        } catch (ManagerDaoException e) {
            throw new ManagerException(e);
        }
    }

    public List<IndexEntry> getAllIndices() throws ManagerException {
        try {
            List<String> indicesList = managerDao.getIndexList();
            List<IndexEntry> indexEntries = new ArrayList<>();
            for (String index : indicesList) {
                IndexEntry indexEntry = new IndexEntry(index);
                indexEntries.add(indexEntry);
            }
            return indexEntries;
        } catch (ManagerDaoException e) {
            log.error("Unable to get all indices", e);
            throw new ManagerException("Unable to get all indices", e);
        }
    }

}
