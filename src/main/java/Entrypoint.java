

import java.util.Arrays;
import java.util.List;

import org.jboss.logging.Logger;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import temporal.workers.AnotherHelloWorldWorker;
import temporal.workers.HelloWorldWorker;
import temporal.workers.TemporalWorker;

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

            //add workers that need to be started here
            List<TemporalWorker> workers = Arrays.asList(
                new HelloWorldWorker(),
                new AnotherHelloWorldWorker()
            );

            //iterate over the list and start each worker
            for (TemporalWorker worker: workers) {
                worker.startWorker();
            }

            LOG.info("Application startup complete!");

            Quarkus.waitForExit();
            return 0;
        }
    }
    
}
