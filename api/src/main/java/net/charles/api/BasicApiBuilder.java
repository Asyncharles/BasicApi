package net.charles.api;

public final class BasicApiBuilder {
    protected RestClientLevel clientLevel = RestClientLevel.LOW;
    protected PropertyDataStorageLevel propertyDataStorageLevel = PropertyDataStorageLevel.MAX;

    public BasicApiBuilder() {

    }

    public BasicApiBuilder setClientLevel(RestClientLevel clientLevel) {
        this.clientLevel = clientLevel;
        return this;
    }

    public BasicApiBuilder setPropertyDataStorageLevel(PropertyDataStorageLevel propertyDataStorageLevel) {
        this.propertyDataStorageLevel = propertyDataStorageLevel;
        return this;
    }

    public PropertyDataStorageLevel getPropertyDataStorageLevel() {
        return propertyDataStorageLevel;
    }

    public BasicApi create() {
        return new BasicProvider(this).getApi();
    }

    public enum RestClientLevel {
        LOW,
        HIGH
    }

    public enum PropertyDataStorageLevel {
        MINIMUM,
        NORMAL,
        MAX;
    }
}


