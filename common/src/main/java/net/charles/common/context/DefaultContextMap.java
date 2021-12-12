package net.charles.common.context;

import net.charles.api.context.ContextKey;
import net.charles.api.context.ContextMap;

public class DefaultContextMap<K, T extends ContextKey<K>> extends ContextMap<K, T> {
    @Override
    public boolean add(T obj) {
        return super.putIfAbsent(obj.getKey(), obj) == null;
    }
}
