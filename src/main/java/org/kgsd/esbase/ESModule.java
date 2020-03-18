package org.kgsd.esbase;

import com.google.inject.AbstractModule;
import org.kgsd.esbase.controller.ControllerModule;

public class ESModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ControllerModule());
    }
}
