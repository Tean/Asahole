package com.netteans.examples.netty.codec;

import com.netteans.examples.netty.TestCaseBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPojoEncoder extends MessageToByteEncoder<TestCaseBean> {
    private static final Logger logger = LoggerFactory.getLogger(TestPojoDecoder.class);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TestCaseBean testCaseBean, ByteBuf byteBuf) throws Exception {
        String send = "{date:" + testCaseBean.getSimpleDate() + ",message:" + testCaseBean.getMessage() + ",rcvdate:" + testCaseBean.getSimpleRcvDate() + "}";
        logger.info("write to {}", send);
        byteBuf.writeBytes(send.getBytes());
    }
}
