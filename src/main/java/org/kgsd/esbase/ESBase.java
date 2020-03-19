package org.kgsd.esbase;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.extern.log4j.Log4j2;
import ro.pippo.controller.Controller;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.Pippo;

import java.util.Set;

import static org.kgsd.esbase.common.Constants.APP_PORT;

@Log4j2
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
            log.error("Error in initializing ESBase App, exiting...", e);
            System.out.println("Error in initializing ESBase App, exiting... " + e);
            System.exit(1);
        }
    }
}
