package com.netteans.example.netty.codec;

public interface addRecvListener<T> {
    public TestPojoDecoder addListener(RecvListener<T> listener);
}
