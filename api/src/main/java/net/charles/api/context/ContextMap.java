package net.charles.api.context;

import java.util.HashMap;

public abstract class ContextMap<K, T extends ContextKey<K>> extends HashMap<K, T> {
    public abstract boolean add(T p);
}
