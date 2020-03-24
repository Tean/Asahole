package com.netteans.examples.netty;

import com.netteans.examples.netty.codec.EchoHandler;
import com.netteans.examples.netty.codec.TestPojoDecoder;
import com.netteans.examples.netty.codec.TestPojoEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.SocketAddress;
import java.util.Calendar;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class BootStrap {
    private static final Logger logger = LoggerFactory.getLogger(BootStrap.class);
    protected static final Queue<String> messageQueue = new LinkedBlockingQueue<>();

    @Value("${netty.port}")
    private int nettyPort;

    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup wokerGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, wokerGroup)
                        .channel(NioServerSocketChannel.class)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new MyServerInitializer());

                ChannelFuture channelFuture = serverBootstrap.bind(nettyPort).sync().addListener(r -> {
                    logger.info("{}", r);
                });
                channelFuture.channel().closeFuture().sync();
            } finally {
                bossGroup.shutdownGracefully();
                wokerGroup.shutdownGracefully();
            }
        };
    }
}

class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final Logger logger = LoggerFactory.getLogger(MyServerInitializer.class);

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast(new MyInboundHandler());
        pipeline.addLast(new TestPojoDecoder().addListener(testCaseBean -> {
            logger.info("{}", testCaseBean);
            TestCaseBean rTestCaseBean = new TestCaseBean();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rTestCaseBean.setRcvDate(testCaseBean.getDate());
            rTestCaseBean.setDate(Calendar.getInstance().getTime());
            rTestCaseBean.setMessage("from server");
            pipeline.channel().writeAndFlush(rTestCaseBean);
        }));
        pipeline.addLast(new EchoHandler());

        pipeline.addLast(new TestPojoEncoder());
    }
}

class MyInboundHandler implements ChannelInboundHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyInboundHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("channelUnregistered");

    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("channelInactive");
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        logger.info("channelRead:" + o);
        channelHandlerContext.channel().writeAndFlush(Calendar.getInstance().getTime() + "#" + o).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        logger.info("userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("channelWritabilityChanged");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("handlerRemoved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        logger.info("exceptionCaught {}", throwable);
        channelHandlerContext.close();
    }
}

class MyOutboundHandler implements ChannelOutboundHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyOutboundHandler.class);

    @Override
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        logger.info("bind");
    }

    @Override
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress1, ChannelPromise channelPromise) throws Exception {
        logger.info("connect");
    }

    @Override
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        logger.info("disconnect");
    }

    @Override
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        logger.info("close");
    }

    @Override
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        logger.info("deregister");
    }

    @Override
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("read");
        channelHandlerContext.read(); //required
    }

    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        logger.info("write");
    }

    @Override
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("flush");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("handlerRemoved");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        logger.info("exceptionCaught {}", throwable);
        channelHandlerContext.close();
    }
}
