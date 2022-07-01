package models;

import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class Config {

    @ConfigProperty(name = "yodawy.token")
    public String token;

    @ConfigProperty(name = "temporal.url")
    public String temporalUrl;

}
