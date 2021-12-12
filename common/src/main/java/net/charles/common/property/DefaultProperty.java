package net.charles.common.property;

import net.charles.api.property.Property;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class
DefaultProperty implements Property {
    private static final Map<Class<?>, Class<?>> PRIMITIVES_MAP = new HashMap<Class<?>, Class<?>>() {{
        put(Boolean.class, boolean.class);
        put(Byte.class, byte.class);
        put(Character.class, char.class);
        put(Short.class, short.class);
        put(Integer.class, int.class);
        put(Long.class, long.class);
        put(Float.class, float.class);
        put(Double.class, double.class);
        put(Void.class, void.class);
    }};

    private final String key;
    private final long creationDate;
    private long lastModifiedDate;
    private Map<String, Object> values;

    public DefaultProperty(String key, long creationDate, long lastModifiedDate) {
        this.key = key;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.values = new HashMap<>();
    }

    @Override
    public String getKey() {
        return key;
    }

    //TODO finish the as (use gson)
    @Override
    public <T> T as(Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            //throw new ex here
            return null;
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        for(final Field declaredField : declaredFields) {

            Property.Index index = null;
            if(declaredField.isAnnotationPresent(Property.Index.class)) {
                index = declaredField.getAnnotation(Property.Index.class);
            }

            if(index != null && values.containsKey(index.value())) {
                Object value = values.get(index.value());
                Class<?> fieldType = declaredField.getType();
                Class<?> valueClass = value.getClass();
                Class<?> primitiveClass = null;

                if(valueClass.isPrimitive() && PRIMITIVES_MAP.containsKey(valueClass)) {
                    primitiveClass = PRIMITIVES_MAP.get(valueClass);
                }

                if(value.getClass() == fieldType || primitiveClass == fieldType) {
                    try {
                        declaredField.setAccessible(true);
                        declaredField.set(obj, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }

    @Override
    public void set(String index, Object obj) {
        values.put(index, obj);
        this.lastModifiedDate = System.currentTimeMillis();
    }

    @Override
    public <T> T get(String index, Class<T> clazz) throws InputMismatchException {
        return clazz.cast(values.get(index));
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public long getCreationDate() {
        return creationDate;
    }
}
