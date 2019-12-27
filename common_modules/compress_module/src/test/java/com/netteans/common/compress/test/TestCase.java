package com.netteans.common.compress.test;

import com.netteans.common.compress.TreeCompress;
import com.netteans.common.interfaces.compress.ICompress;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class TestCase {
    private static final Logger log = LoggerFactory.getLogger(TestCase.class);

    @Test
    public void testTree() {
        ICompress compress = new TreeCompress();
        try {
            byte[] re = compress.compress(new byte[]{1, 1, 2, 2, 3, 3, 4, 4});
            log.info("{}", Arrays.toString(re));
        } catch (IOException e) {
            log.error("{}", "compress exception", e);
        }
    }
}
