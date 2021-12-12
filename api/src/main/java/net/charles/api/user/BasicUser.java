package net.charles.api.user;

import net.charles.api.context.ContextMap;
import net.charles.api.property.Property;

import java.util.UUID;

public interface BasicUser {
    public UUID getUuid();

    public String getName();

    public ContextMap<String, Property> getProperties();
}
