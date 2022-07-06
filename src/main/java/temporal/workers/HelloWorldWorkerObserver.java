package temporal.workers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import http.services.CatFactService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import temporal.activities.impl.CapitalizerImpl;
import temporal.activities.impl.CatFactFetcherImpl;
import temporal.queues.Queues;
import temporal.workflows.impl.HelloWorldWorkflowImpl;

@ApplicationScoped
public class HelloWorldWorkerObserver {

    private WorkflowClient client;
    private WorkerFactory factory;

    private static final Logger LOG = Logger.getLogger(HelloWorldWorkerObserver.class);

    @Inject
    @RestClient
    CatFactService catFactService;

    void onStart(@Observes StartupEvent ev) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        client = WorkflowClient.newInstance(service);
        factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(Queues.HELLO_WORLD_TASK_QUEUE);
        ActivityCompletionClient completionClient = client.newActivityCompletionClient();

        worker.registerWorkflowImplementationTypes(HelloWorldWorkflowImpl.class);
        worker.registerActivitiesImplementations(
            new CapitalizerImpl(),
            new CatFactFetcherImpl(completionClient, catFactService)
        );

        factory.start();
        LOG.info("Started HelloWorld worker");
    }

    void onStop(@Observes ShutdownEvent ev) {
        factory.shutdown();
    }

    public WorkflowClient getClient() {
        return client;
    }
}
