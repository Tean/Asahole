package com.netteans.agent;

import org.springframework.stereotype.Component;

@Component
public class LocalThreadCounter {

    private static ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    public Integer increase() {
        seqNum.set(seqNum.get() + 1);
        return get();
    }

    public Integer get() {
        return seqNum.get();
    }

    public void reset() {
        seqNum.remove();
        seqNum.set(0);
    }
}
