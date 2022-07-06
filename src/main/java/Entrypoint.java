


import org.jboss.logging.Logger;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Entrypoint {

    public static void main(String ... args) {
        //start application
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args) throws Exception {
            Logger LOG = Logger.getLogger(MyApp.class);
            /*
                this method is technically useless now as the workers
                now run based on the observer, but keeping it anyway
                in case we need to do other things on startup
            */
            LOG.info("Application startup complete!");

            Quarkus.waitForExit();
            return 0;
        }
    }
    
}
