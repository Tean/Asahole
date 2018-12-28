package com.netteans.agent.stream.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.presets.opencv_objdetect;
import org.bytedeco.javacv.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ws.schild.jave.*;

import javax.swing.*;
import java.io.File;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.List;

public class FFmpegTestCase {
    private final String mediaPath = "/Users/netteans/Downloads/John.Wick.Chapter.2.1080p.BluRay.x264.Atmos.TrueHD.7.1-HDChina/John.Wick.Chapter.2.1080p.BluRay.x264.Atmos.TrueHD.7.1-HDChina.mkv";
    private static final Logger logger = LoggerFactory.getLogger(FFmpegTestCase.class);

    @Test
    public void capture() {
        File source = new File(mediaPath);
        String length = "";
        FFMPEGLocator locator = new FFMPEGLocator() {
            @Override
            protected String getFFMPEGExecutablePath() {
                return "/usr/local/Cellar/ffmpeg/4.1_1/ffmpeg";
            }
        };
        try {
            MultimediaObject instance = new MultimediaObject(source);
            MultimediaInfo result = instance.getInfo();
            String format = result.getFormat();
            VideoAttributes video = new VideoAttributes();
            video.setCodec(format);
            video.setBitRate(result.getVideo().getBitRate());

            long ls = result.getDuration() / 1000;
            int hour = (int) (ls / 3600);
            int minute = (int) (ls % 3600) / 60;
            int second = (int) (ls - hour * 3600 - minute * 60);
            length = hour + "'" + minute + "''" + second + "'''";
            logger.info("{}", length);
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }

    @Test
    public void cam() {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        CanvasFrame canvas = null;
        try {
            grabber.start();   //开始获取摄像头数据
            canvas = new CanvasFrame("摄像头", CanvasFrame.getDefaultGamma() / grabber.getGamma());//新建一个窗口
            canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            canvas.setAlwaysOnTop(true);


            Frame preframe = grabber.grab();

            String rtmpPath = "rtmp://localhost/live360p/ss1";
            int imageWidth = preframe.imageWidth;
            int imageHeight = preframe.imageHeight;
            FrameRecorder recorder = new FFmpegFrameRecorder(rtmpPath, imageWidth, imageHeight, 1);
            recorder.setInterleaved(true);
            recorder.setVideoOption("crf", "28");
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
            recorder.setFormat("flv"); // rtmp的类型
            recorder.setFrameRate(25);
            recorder.setPixelFormat(0);
            recorder.start();

            long videoTS = 0;
            long startTime = System.currentTimeMillis();
            OpenCVFrameConverter.ToIplImage conveter = new OpenCVFrameConverter.ToIplImage();
            while (true) {
                if (!canvas.isDisplayable()) {//窗口是否关闭
                    grabber.stop();//停止抓取
                    System.exit(-1);//退出
                }

                Frame frame = grabber.grab();
                videoTS = 1000 * (System.currentTimeMillis() - startTime);
                recorder.setTimestamp(videoTS);
                recorder.record(frame);

                canvas.showImage(frame);//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
                Thread.sleep(50);//50毫秒刷新一次图像
            }
        } catch (FrameGrabber.Exception | InterruptedException e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null)
                canvas.dispose();
        }
    }

    @Test
    public void stream() {
        try {
            recordPush("rtmp://127.0.0.1/live/pushFlow", 25);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 转流器
     *
     * @param outputFile
     * @throws Exception
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     * @throws InterruptedException
     */
    public static void recordPush(String outputFile, int v_rs) throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, InterruptedException {
        Loader.load(opencv_objdetect.class);
        long startTime = 0;
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        try {
            grabber.start();
        } catch (Exception e) {
            try {
                grabber.restart();
            } catch (Exception e1) {
                throw e;
            }
        }

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        Frame grabframe = grabber.grab();
        opencv_core.IplImage grabbedImage = null;
        if (grabframe != null) {
            System.out.println("取到第一帧");
            grabbedImage = converter.convert(grabframe);
        } else {
            System.out.println("没有取到第一帧");
        }
        //如果想要保存图片,可以使用 opencv_imgcodecs.cvSaveImage("hello.jpg", grabbedImage);来保存图片
        FrameRecorder recorder;
        recorder = new FFmpegFrameRecorder(outputFile, grabframe.imageWidth, grabframe.imageHeight, 1);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // avcodec.AV_CODEC_ID_H264
        recorder.setFormat("flv");
        recorder.setFrameRate(v_rs);
        recorder.setGopSize(v_rs);
        System.out.println("准备开始推流...");
        try {
            recorder.start();
        } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
            logger.error("{}", e);
            try {
                System.out.println("录制器启动失败，正在重新启动...");
                if (recorder != null) {
                    System.out.println("尝试关闭录制器");
                    recorder.stop();
                    System.out.println("尝试重新开启录制器");
                    recorder.start();
                }

            } catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
                throw e1;
            }
        }
        System.out.println("开始推流");
        CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma() / grabber.getGamma());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        while (frame.isVisible() && (grabframe = grabber.grab()) != null) {
            System.out.println("推流...");
            frame.showImage(grabframe);
            grabbedImage = converter.convert(grabframe);
            Frame rotatedFrame = converter.convert(grabbedImage);

            if (startTime == 0) {
                startTime = System.currentTimeMillis();
            }
            recorder.setTimestamp(1000 * (System.currentTimeMillis() - startTime));//时间戳
            if (rotatedFrame != null) {
                recorder.record(rotatedFrame);
            }

            Thread.sleep(40);
        }
        frame.dispose();
        recorder.stop();
        recorder.release();
        grabber.stop();
        System.exit(2);
    }

    @Test
    public void server() {
        final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();

        try {
            final ServerBootstrap s = new ServerBootstrap();
            logger.info("{}", "init");
            s.group(eventLoopGroup, workerEventLoopGroup)
                    .channelFactory(NioServerSocketChannel::new)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {

                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            pipeline.addLast("messageDecoder", new MessageToMessageDecoder<ByteBuf>() {
//                                @Override
//                                protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//                                    logger.debug("{} -> {}", channelHandlerContext.name(), "decode");
//                                }
//                            });
//                            pipeline.addLast("messageEncoder", new MessageToMessageEncoder<ByteBuf>() {
//                                @Override
//                                protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//                                    logger.debug("{} -> {}", channelHandlerContext.name(), "encode");
//                                }
//                            });
//                            pipeline.addLast("stringDecoder", new StringDecoder(Charset.forName("utf-8")));
                            pipeline.addLast(new HttpRequestDecoder(), new HttpContentDecompressor());
                            pipeline.addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                                @Override
                                public void channelActive(ChannelHandlerContext channelHandlerContext) {
                                    logger.debug("{} -> {}", channelHandlerContext.name(), channelHandlerContext.channel().remoteAddress());
                                }

                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest request) {
                                    logger.debug("{} -> {}", channelHandlerContext.name(), request);
                                }
                            }, new SimpleChannelInboundHandler<HttpContent>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpContent httpContent) throws Exception {
                                    logger.debug("{} -> {}", channelHandlerContext.name(), httpContent  );
                                    channelHandlerContext.writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK));
                                    channelHandlerContext.close();
                                }
                            });
                            pipeline.addLast(new HttpResponseEncoder(), new HttpContentCompressor());
                        }
                    });

            logger.info("{}", "start");
            ChannelFuture f = s.bind(6666).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("{}", e);
        } finally {
            //优雅退出，释放线程池资源
            eventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }
    }
}
