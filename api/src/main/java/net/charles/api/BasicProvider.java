package net.charles.api;

import net.charles.api.utils.PreBuilds;

public final class BasicProvider {
    private static BasicApi apiInstance = null;
    public static final String VERSION = "0.0.1";

    private final BasicApiBuilder apiBuilder;

    protected BasicProvider(final BasicApiBuilder apiBuilder) {
        this.apiBuilder = apiBuilder;
    }

    public BasicApi getApi() {
        if (BasicProvider.apiInstance == null) {
            throw new IllegalStateException("The BasicApi instance is null! Make sure to register an Api instance.");
        }
        return BasicProvider.apiInstance;
    }

    public static void register(BasicApi api) {
        BasicProvider.apiInstance = api;
    }
}
