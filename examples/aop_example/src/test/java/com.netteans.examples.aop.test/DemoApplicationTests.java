package com.netteans.examples.aop.test;

import com.netteans.examples.aop.BootStrap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {BootStrap.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DemoApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    MockHttpSession session; //5 注入模拟的http session

    @Autowired
    MockHttpServletRequest request; // 模拟request

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() throws Exception {

//		String exp_str = demoService.saySomething(); // expect str
        int id = 1;
        mockMvc.perform(get("/locale")) //8 模拟GET /normal
                .andDo(print())
                .andExpect(status().isOk());//9 预期返回状态为200
        String resp = mockMvc.perform(get("/get/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals("{" +
                "  \"name\": \"{id:1}\"," +
                "  \"password\": \"id demo\"," +
                "  \"email\": \"id@dee.mo\"" +
                "}", resp, JSONCompareMode.LENIENT);

//				.andExpect(view().name("page"))//10 预期view的名称
//				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 预期页面转向的真正路径
//				.andExpect(model().attribute("msg", "request id is" + id));//12 预期model里的值
    }

    @Test
    public void selectTest() {
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        try {
            AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(threadPoolExecutor, 8);
            AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel
                    .open(asynchronousChannelGroup)
                    .bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), 7686));

            serverSocketChannel.accept(this, new ServerSocketChannelHandle(serverSocketChannel));

            TimeUnit.DAYS.sleep(1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ServerSocketChannelHandle implements CompletionHandler<AsynchronousSocketChannel, Object> {
        private final AsynchronousServerSocketChannel serverSocketChannel;
        private final Logger logger = LoggerFactory.getLogger(ServerSocketChannelHandle.class);

        public ServerSocketChannelHandle(AsynchronousServerSocketChannel serverSocketChannel) {
            this.serverSocketChannel = serverSocketChannel;
        }

        @Override
        public void completed(AsynchronousSocketChannel result, Object attachment) {
            logger.info("{}@{}", result, attachment);
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            logger.info("{}@{}", exc, attachment);
        }
    }
}
