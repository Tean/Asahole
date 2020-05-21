package com.netteans.examples.gof.proxy;

public interface IProxy<P, R> {
    /**
     * 代理方法
     *
     * @return R
     */
    @Passion(passed = false)
    R proxyMethod(P parameter);
}
