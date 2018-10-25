package com.netteans.agent.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@SpringBootTest
public class DemoApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	MockHttpSession session; //5 注入模拟的http session

	@Autowired
	MockHttpServletRequest request; // 模拟request

	@Before
	public void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void contextLoads() throws Exception {

//		String exp_str = demoService.saySomething(); // expect str
		int id = 1;
		mockMvc.perform(get("/")) //8 模拟GET /normal
				.andExpect(status().isOk())//9 预期返回状态为200
				.andExpect(view().name("page"))//10 预期view的名称
				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 预期页面转向的真正路径
				.andExpect(model().attribute("msg", "request id is" + id));//12 预期model里的值
	}

}
