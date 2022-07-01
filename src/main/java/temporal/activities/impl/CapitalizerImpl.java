package temporal.activities.impl;

import temporal.activities.Capitalizer;

public class CapitalizerImpl implements Capitalizer {

    @Override
    public String capitalize(String input) {
        return input.toUpperCase();
    }
    
}
