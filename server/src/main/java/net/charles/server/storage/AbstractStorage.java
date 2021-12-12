package net.charles.server.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractStorage {
    private static final Map<Class<? extends AbstractStorage>, AbstractStorage> storageService = new HashMap<>();

    private final String name;

    public AbstractStorage(String name) {
        this.name = name;
    }

    public static void registerServices() {
        AbstractStorage[] storages = {};

        for (AbstractStorage storage : storages) storageService.putIfAbsent(storage.getClass(), storage);
    }

    public static List<AbstractStorage> getStorageServiceList() {
        return new ArrayList<>(storageService.values());
    }

    public static <T extends AbstractStorage> T getStorageService(T... t) {
        final Class<?> clazz = t.getClass().getComponentType();
        if (storageService.containsKey(clazz)) {
            return (T) storageService.get(clazz);
        }
        return null;
    }

    public abstract void init();

    public abstract void shutdown();
}
