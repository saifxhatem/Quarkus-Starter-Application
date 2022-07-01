package temporal.workflows.impl;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import temporal.activities.Capitalizer;
import temporal.workflows.HelloWorldWorkflow;


public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(2))
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.
    private final Capitalizer capitalizer = Workflow.newActivityStub(Capitalizer.class, options);

    @Override
    public String getGreeting(String name) {
        return capitalizer.capitalize(name);
    }
}
