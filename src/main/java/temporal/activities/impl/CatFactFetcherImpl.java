package temporal.activities.impl;

import http.services.CatFactService;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import models.CatFact;
import temporal.activities.CatFactFetcher;


public class CatFactFetcherImpl implements CatFactFetcher {
    
    private final ActivityCompletionClient completionClient;
    private CatFactService catFactService;
    
    public CatFactFetcherImpl(ActivityCompletionClient completionClient, CatFactService catFactService) {
        this.completionClient = completionClient;
        this.catFactService = catFactService;
    }
    
    @Override
    public CatFact getCatFact() {
        ActivityExecutionContext ctx = Activity.getExecutionContext();
        byte[] taskToken = ctx.getTaskToken();
        catFactService.get().invoke(catFact -> {
            completionClient.complete(taskToken, catFact);
        })
        .subscribe()
        .with(
            item -> System.out.println("success"),
            failure -> System.out.println("failure")
        );

        ctx.doNotCompleteOnReturn();
        return null;
    }
    
}
