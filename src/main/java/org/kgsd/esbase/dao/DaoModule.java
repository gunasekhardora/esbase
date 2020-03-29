package org.kgsd.esbase.dao;

import com.google.inject.AbstractModule;
import org.kgsd.esbase.dao.manager.ManagerDao;
import org.kgsd.esbase.dao.manager.impl.SolrManagerDao;

public class DaoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ManagerDao.class).to(SolrManagerDao.class).asEagerSingleton();
    }
}
