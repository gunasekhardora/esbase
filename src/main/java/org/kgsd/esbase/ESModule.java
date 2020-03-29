package org.kgsd.esbase;

import com.google.inject.AbstractModule;
import org.kgsd.esbase.controller.ControllerModule;
import org.kgsd.esbase.dao.DaoModule;

public class ESModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ControllerModule());
        install(new DaoModule());
    }
}
