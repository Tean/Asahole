package com.netteans.examples.netty.codec;

public interface addRecvListener<T> {
    public TestPojoDecoder addListener(RecvListener<T> listener);
}
