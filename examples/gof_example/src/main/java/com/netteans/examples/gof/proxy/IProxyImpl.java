package com.netteans.examples.gof.proxy;

public class IProxyImpl implements IProxy<String> {

    private final String status;

    public IProxyImpl(String status) {
        this.status = status;
    }

    @Override
    public String proxyMethod() {
        return "static proxy impl " + status;
    }
}
