package net.charles.common.user;

import net.charles.api.context.ContextMap;
import net.charles.api.property.Property;
import net.charles.api.user.BasicUser;
import net.charles.common.context.DefaultContextMap;

import java.util.UUID;

public class DefaultBasicUser implements BasicUser {
    private final UUID uuid;
    private String name;
    private DefaultContextMap<String, Property> properties;

    public DefaultBasicUser(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.properties = new DefaultContextMap<>();
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ContextMap<String, Property> getProperties() {
        return properties;
    }

}
