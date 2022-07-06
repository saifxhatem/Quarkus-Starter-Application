package temporal.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import models.CatFact;

@ActivityInterface
public interface CatFactFetcher {
    @ActivityMethod
    CatFact getCatFact();
}
