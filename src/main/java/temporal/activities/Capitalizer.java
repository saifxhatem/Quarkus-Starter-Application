package temporal.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface Capitalizer {
    String capitalize(String input);
}
