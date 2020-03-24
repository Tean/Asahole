package com.netteans.examples.netty.codec;

import com.netteans.examples.netty.TestCaseBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestPojoDecoder extends ByteToMessageDecoder implements addRecvListener<TestCaseBean> {
    private RecvListener<TestCaseBean> listener;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readableBytes();
        byte[] datas = new byte[len];
        byteBuf.readBytes(datas);
        String recv = new String(datas);

        String date = recv.substring(6, recv.indexOf(",message:"));
        String msg = recv.substring(recv.indexOf(",message:") + 9, recv.indexOf(",rcvdate:"));
        String rcvDate = recv.substring(recv.indexOf(",rcvdate:") + 9, recv.length() - 1);

        TestCaseBean testCaseBean = new TestCaseBean();
        testCaseBean.parseDate(date);
        testCaseBean.setDate(Calendar.getInstance().getTime());
        testCaseBean.parseRcvDate(rcvDate);
        testCaseBean.setMessage(msg);
        if (listener != null) {
            listener.recv(testCaseBean);
        }
        list.add(testCaseBean);
    }

    @Override
    public TestPojoDecoder addListener(RecvListener<TestCaseBean> listener) {
        this.listener = listener;
        return this;
    }
}
