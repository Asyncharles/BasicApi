
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.charles.api.property.Property;
import net.charles.common.property.DefaultProperty;
import net.charles.common.adapters.PropertyAdapter;
import net.charles.common.user.DefaultBasicUser;

import java.util.UUID;

public class Serializer {
    public static void main(String[] args)  {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().registerTypeAdapter(Property.class, new PropertyAdapter()).create();

        final long timestamp = System.currentTimeMillis();

        Property property = new DefaultProperty("test", timestamp, timestamp);
        property.set("Index 1", "Hey");
        property.set("Index 2", true);

        Property p = new DefaultProperty("test-second", timestamp, timestamp);
        p.set("idk", "Test value index");
        p.set("Stuff", 23);

        DefaultBasicUser user = new DefaultBasicUser(UUID.randomUUID(), "test");
        user.getProperties().put(property.getKey(), property);

        String str = gson.toJson(user);
        System.out.println(str);
        DefaultBasicUser player = gson.fromJson(str, DefaultBasicUser.class);
        System.out.println(player.getProperties().get("test").get("Index 1", String.class));
    }
}
