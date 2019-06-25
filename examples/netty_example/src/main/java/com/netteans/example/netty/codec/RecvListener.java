package com.netteans.example.netty.codec;

public interface RecvListener<T> {
    public void recv(T t);
}
