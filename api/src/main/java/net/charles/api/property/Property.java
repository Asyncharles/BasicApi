package net.charles.api.property;

import net.charles.api.context.ContextKey;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.InputMismatchException;

public interface Property extends ContextKey<String> {
    public <T> T as(Class<T> clazz) throws InputMismatchException;

    public <T> T get(String key, Class<T> clazz) throws InputMismatchException;

    public void set(String key, Object obj);

    public int size();

    public long getLastModifiedDate();

    public long getCreationDate();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Index {
        String value();
    }
}
