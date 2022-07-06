package temporal.workflows.impl;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import models.CatFact;
import temporal.activities.Capitalizer;
import temporal.activities.CatFactFetcher;
import temporal.workflows.HelloWorldWorkflow;


public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(15))
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.
    private final Capitalizer capitalizer = Workflow.newActivityStub(Capitalizer.class, options);
    private final CatFactFetcher cats = Workflow.newActivityStub(CatFactFetcher.class, options);

    @Override
    public String getGreeting(String name) {
        CatFact catFact = cats.getCatFact();
        return capitalizer.capitalize(name);
    }
}
