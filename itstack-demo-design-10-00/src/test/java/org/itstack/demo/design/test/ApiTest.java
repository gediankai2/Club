package org.itstack.demo.design.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {

        MvcResult mvcResult = null;

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/queryUserInfo?userId=1001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals("{\"code\":\"0000\",\"info\":\"success\",\"name\":\"虫虫:1001\",\"age\":19,\"address\":\"天津市南开区旮旯胡同100号\"}", mvcResult.getResponse().getContentAsString());

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/queryUserInfo?userId=99999999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals("{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\",\"name\":null,\"age\":null,\"address\":null}", mvcResult.getResponse().getContentAsString());


    }


}
