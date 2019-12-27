package com.netteans.common.interfaces.compress;

import java.io.IOException;

/**
 * @author netteans
 */
public interface ICompress {

    /**
     * 压缩
     * @param src 源数据
     * @return 结果数据
     * @throws IOException 读取异常
     */
    byte[] compress(byte[] src) throws IOException;

    /**
     * 反压缩
     * @param cps 压缩数据
     * @return 原始数据
     */
    byte[] decompress(byte[] cps);
}
