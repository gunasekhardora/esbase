package org.kgsd.esbase;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import ro.pippo.controller.Controller;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.Pippo;

import java.util.Set;

import static org.kgsd.esbase.common.Constants.APP_PORT;

public class ESBase extends ControllerApplication { 
    @Inject
    public ESBase(Set<Controller> controllers) {
        controllers.forEach(this::addControllers);
    }

    public static void main(String[] args) {
        System.out.println("\n-----------------------------------------");
        System.out.println("\n               ESBASE                    ");
        System.out.println("\n-----------------------------------------");

        try {
            Injector injector = Guice.createInjector(new ESModule());
            ESBase esBase = injector.getInstance(ESBase.class);
            (new Pippo(esBase)).start(APP_PORT);
        } catch (Exception e) {
            //TODO: Add log
            System.out.println("Error in initializing ESBase, exiting... " + e);
            System.exit(1);
        }
    }
}
