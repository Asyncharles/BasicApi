package net.charles.common.adapters;

import com.google.gson.*;
import net.charles.api.property.Property;
import net.charles.common.property.DefaultProperty;

import java.lang.reflect.Type;

public class PropertyAdapter implements JsonSerializer<Property>, JsonDeserializer<Property> {
    @Override
    public JsonElement serialize(Property property, Type type, JsonSerializationContext jsonSerializationContext) {
        return jsonSerializationContext.serialize(property);
    }

    @Override
    public Property deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return jsonDeserializationContext.deserialize(jsonElement, DefaultProperty.class);
    }
}
