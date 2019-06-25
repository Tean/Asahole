package com.netteans.example.netty.test;

import com.netteans.example.netty.TestCaseBean;
import com.netteans.example.netty.codec.TestPojoDecoder;
import com.netteans.example.netty.codec.TestPojoEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TestBootStrap {

    @Test
    public void test() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TestClientInitializer());
            Channel ch = b.connect("localhost", 9527).sync().channel();

            TestCaseBean testCaseBean = new TestCaseBean();
            testCaseBean.setDate(Calendar.getInstance().getTime());
            testCaseBean.setMessage("from client");
            ch.writeAndFlush(testCaseBean).addListener(
                    pa -> {
                        System.out.println("par:" + pa);
                    }
            );
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}

class TestClientInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger logger = LoggerFactory.getLogger(TestClientInitializer.class);

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //outbound client(this)->server
        pipeline.addLast(new TestPojoEncoder());

        //inbound server->client(this)
        pipeline.addLast(new TestPojoDecoder().addListener(t -> {
            logger.info("{}", t);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("close");
            pipeline.channel().close();
        }));
    }
}


