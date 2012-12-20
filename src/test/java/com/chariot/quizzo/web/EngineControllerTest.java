package com.chariot.quizzo.web;

import com.chariot.quizzo.engine.QuizGenerator;
import com.chariot.quizzo.engine.QuizRunStateMachine;
import flexjson.JSONDeserializer;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebMergedContextConfiguration;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@ActiveProfiles(profiles = {"development"})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml",
        "classpath:/META-INF/spring/applicationContext.xml"})
public class EngineControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @Before
    public void setUp() {
        mvc = webAppContextSetup(this.context).build();
    }

    @Test
    public void testContext() {
        Assert.assertNotNull(context);
    }

    @Test
    public void tryStartQuiz() throws Exception {
        JSONDeserializer<HashMap<String, String>> deserializer
                = new JSONDeserializer<HashMap<String, String>>();

        MvcResult result = this.mvc.perform(get("/engine/start/james"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.quiz_id").exists())
                .andReturn();

        Map<String, String> jsonPayload =
                deserializer.deserialize(
                        result.getResponse().getContentAsString());

        assertThat(jsonPayload.containsKey("quiz_id"), is(true));
        assertThat(jsonPayload.containsKey("nick_name"), is(true));
        assertThat(jsonPayload.get("nick_name"), is("james"));

        System.err.println(jsonPayload);
    }


}
