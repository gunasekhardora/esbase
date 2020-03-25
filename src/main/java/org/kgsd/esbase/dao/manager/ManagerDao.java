package org.kgsd.esbase.dao.manager;

import org.kgsd.esbase.exception.ManagerDaoException;

import java.util.List;

public interface ManagerDao {
    void createIndex(String index) throws ManagerDaoException;

    boolean doesIndexExists(String index) throws ManagerDaoException;

    void deleteIndex(String index) throws ManagerDaoException;

    List<String> getIndexList() throws ManagerDaoException;
}
