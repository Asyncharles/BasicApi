package net.charles.api.utils;

import net.charles.api.BasicApi;
import net.charles.api.BasicApiBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

public enum PreBuilds {
    MINIMUM_POWER(new BasicApiBuilder().setClientLevel(BasicApiBuilder.RestClientLevel.LOW).setPropertyDataStorageLevel(BasicApiBuilder.PropertyDataStorageLevel.MINIMUM)),
    FULL_CONFIGURATION(new BasicApiBuilder().setClientLevel(BasicApiBuilder.RestClientLevel.HIGH).setPropertyDataStorageLevel(BasicApiBuilder.PropertyDataStorageLevel.MAX));

    private BasicApiBuilder builder;
    PreBuilds(BasicApiBuilder builder) {
        this.builder = builder;
    }

    public PreBuilds applyModifications(Function<BasicApiBuilder, BasicApiBuilder> function) {
        this.builder = function.apply(builder);
        return this;
    }

    public BasicApi getNewlyApiInstance() {
        return builder.create();
    }
}
