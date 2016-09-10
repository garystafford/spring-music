package org.cloudfoundry.samples.music.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {

    @Mock
    private Environment springEnvironment;

    @InjectMocks
    private InfoController infoController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(infoController).build();
    }

    @Test
    public void testInfo() throws Exception {
        String[] profiles = new String[]{"test_profile_1", "test_profile_2"};
//        String[] services = new String[0];
//        ArrayList<String[]> expectedRespose = new ArrayList<>();
//        expectedRespose.add(profiles);
//        expectedRespose.add(services);
//        ObjectMapper mapper = new ObjectMapper();
//        String expectedRespose_json = mapper.writeValueAsString(expectedRespose);

        when(springEnvironment.getActiveProfiles()).thenReturn(profiles);
        String expectedRespose = "{\"profiles\":[\"test_profile_1\",\"test_profile_2\"],\"services\":[]}";

        MvcResult result = this.mockMvc.perform(get("/info")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profiles[0]", is("test_profile_1")))
                .andExpect(jsonPath("$.profiles[1]", is("test_profile_2")))
                .andDo(print())
                .andReturn();

        Assert.assertEquals(expectedRespose, result.getResponse().getContentAsString());
    }

    @Test
    public void testShowEnvironment() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/env")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void testShowServiceInfo() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/service")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals("[]", result.getResponse().getContentAsString());
    }
}