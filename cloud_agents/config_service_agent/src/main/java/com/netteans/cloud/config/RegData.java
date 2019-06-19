package com.netteans.cloud.config;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RegData {
    final Map<String, Prefix> prefix = new TreeMap<>();

    public Prefix get(String pfxName) {
        return prefix.get(pfxName);
    }

    public void put(String pfxName, Prefix prefix) {
        this.prefix.put(pfxName, prefix);
    }

    public Set<String> keys() {
        return this.prefix.keySet();
    }
}

class Prefix {
    final Map<String, Context> context = new TreeMap<>();
    final Map<String, String> values = new TreeMap<>();

    public Context get(String contextName) {
        return context.get(contextName);
    }

    public void put(String contextName, Context context) {
        this.context.put(contextName, context);
    }

    public Set<String> keys() {
        return this.context.keySet();
    }


    public String getValue(String valKey) {
        return values.get(valKey);
    }

    public void putValue(String key, String value) {
        this.values.put(key, value);
    }

    public Set<String> valueKeys() {
        return this.values.keySet();
    }
}

class Context {
    final Map<String, String> data = new TreeMap<>();

    public String get(String key) {
        return data.get(key);
    }

    public void put(String key, String value) {
        data.put(key, value);
    }

    public Set<String> keys() {
        return this.data.keySet();
    }
}