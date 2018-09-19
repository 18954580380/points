package cn.koboro.points.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//启动了完整的spring application，但是没有使用tomcat server类似的容器，虽然mock了http，但是代码的执行和处理真实的http request时是一样的
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmpiricalValueItemControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Test
    @Sql({"/scripts/empiricalValueItem-schema.sql", "/scripts/empiricalValueItem-data.sql"})
    public void testList() throws Exception {
        MvcResult result = mockMvc.perform(get("/empiricalValueItem/selectAll").param("pageNum","1"))
                .andExpect(view().name("empiricalValueItem/list"))
                .andDo(print())
                .andReturn();
    }
}
