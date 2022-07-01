package temporal.workflows.impl;

import java.time.Duration;

import io.temporal.activity.ActivityOptions;
import temporal.workflows.HelloWorldWorkflow;


public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(2))
            .build();

    // ActivityStubs enable calls to Activities as if they are local methods, but actually perform an RPC.
    // private final Format format = Workflow.newActivityStub(Format.class, options);

    @Override
    public String getGreeting(String name) {
        return name;
    }
}
