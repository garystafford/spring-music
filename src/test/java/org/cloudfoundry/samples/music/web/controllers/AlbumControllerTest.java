package org.cloudfoundry.samples.music.web.controllers;

import org.cloudfoundry.samples.music.domain.Album;
import org.cloudfoundry.samples.music.repositories.AlbumRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = org.cloudfoundry.samples.music.config.AppInitializer.class),
        @ContextConfiguration(classes = org.cloudfoundry.samples.music.config.SpringApplicationContextInitializer.class)
})
@ActiveProfiles("mongodb")
@org.junit.Ignore
public class AlbumControllerTest {
    @Mock
    private AlbumRepository repository;

    @InjectMocks
    private AlbumController albumController;

    private MockMvc mockMvc;

    @org.junit.Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Before
    public void setup() throws ServletException {
        this.albumController = new AlbumController(repository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testAlbums() throws Exception {
        this.mockMvc.perform(get("/albums")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @org.junit.Test
    public void testGetById() throws Exception {
        this.mockMvc.perform(get("/albums/579df5ecbee8acbe920ab488")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @org.junit.Test
    public void testAdd() throws Exception {
        Album album = new Album("title", "artist", "1999", "genre");
        this.mockMvc.perform(post("/albums", album)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @org.junit.Ignore
    @org.junit.Test
    public void testUpdate() throws Exception {

    }

    @org.junit.Ignore
    @org.junit.Test
    public void testDeleteById() throws Exception {

    }
}