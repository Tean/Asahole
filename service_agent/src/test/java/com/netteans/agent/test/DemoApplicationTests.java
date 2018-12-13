package com.netteans.agent.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
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
        mockMvc.perform(get("/")) //8 模拟GET /normal
                .andDo(print())
                .andExpect(status().isOk());//9 预期返回状态为200
        String resp = mockMvc.perform(get("/get/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals("{\n" +
                "  \"name\": \"{id:1}\",\n" +
                "  \"password\": \"id demo\",\n" +
                "  \"email\": \"id@dee.mo\"\n" +
                "}", resp, JSONCompareMode.STRICT);

//				.andExpect(view().name("page"))//10 预期view的名称
//				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 预期页面转向的真正路径
//				.andExpect(model().attribute("msg", "request id is" + id));//12 预期model里的值
    }

}
