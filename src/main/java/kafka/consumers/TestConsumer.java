package kafka.consumers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.reactive.messaging.kafka.Record;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import models.Config;
import temporal.queues.Queues;
import temporal.workflows.HelloWorldWorkflow;

@ApplicationScoped
public class TestConsumer {

    @Inject
    Config config;
    
    @Incoming("quarkus-kafka-test")
    public void consume(Record<String, String> record) {
        String value = record.value();
        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Queues.HELLO_WORLD_TASK_QUEUE)
                .build();
        HelloWorldWorkflow workflow = client.newWorkflowStub(HelloWorldWorkflow.class, options);
        WorkflowClient.start(workflow::getGreeting, value);
    }

}