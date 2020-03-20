package com.netteans.examples.netty.codec;

public interface RecvListener<T> {
    public void recv(T t);
}
