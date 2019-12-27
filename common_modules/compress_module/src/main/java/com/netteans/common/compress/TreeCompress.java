package com.netteans.common.compress;

import com.netteans.common.interfaces.compress.ICompress;

public class TreeCompress implements ICompress {
    @Override
    public byte[] compress(byte[] src) {
        return src;
    }

    @Override
    public byte[] decompress(byte[] cps) {
        return cps;
    }
}
