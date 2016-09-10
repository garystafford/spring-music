package org.cloudfoundry.samples.music.web.controllers;

import org.cloudfoundry.samples.music.domain.Album;
import org.cloudfoundry.samples.music.repositories.AlbumRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AlbumControllerTest {
    @Mock
    private AlbumRepository repository;

    @InjectMocks
    private AlbumController albumController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws ServletException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
    }

    @Test
    public void testAlbums() throws Exception {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("test_findAll_title1", "test_findAll_artist1", "2001", "test_findAll_genre1"));
        albums.add(new Album("test_findAll_title2", "test_findAll_artist2", "2002", "test_findAll_genre2"));

        ObjectMapper mapper = new ObjectMapper();
        String albums_json = mapper.writeValueAsString(albums);

        when(repository.findAll()).thenReturn(albums);

        MvcResult result = this.mockMvc.perform(get("/albums")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(albums_json, result.getResponse().getContentAsString());

    }

    @Test
    public void testGetById() throws Exception {
        Album album = new Album("test_findOne_title", "test_findOne_artist", "2000", "test_findOne_genre");
        album.setId("579df5ecbee8acbe920ab488");

        ObjectMapper mapper = new ObjectMapper();
        String album_json = mapper.writeValueAsString(album);

        when(repository.findOne(any())).thenReturn(album);

        MvcResult result = this.mockMvc.perform(get("/albums/579df5ecbee8acbe920ab488")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(album_json, result.getResponse().getContentAsString());
    }

    @Test
    public void testAdd() throws Exception {
        Album album = new Album("test_add_title", "test_add_artist", "2000", "test_add_genre");
        ObjectMapper mapper = new ObjectMapper();
        String album_json = mapper.writeValueAsString(album);

        when(repository.save(any(Album.class))).thenReturn(album);

        MvcResult result = this.mockMvc.perform(post("/albums").content(album_json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(album_json, result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        Album album = new Album("test_update_title", "test_update_artist", "2000", "test_update_genre");
        album.setId("579df5ecbee8acbe920ab48c");
        ObjectMapper mapper = new ObjectMapper();
        String album_json = mapper.writeValueAsString(album);

        when(repository.save(any(Album.class))).thenReturn(album);

        // AlbumController.update is actually a HTTP.POST with Id, not an HTTP.PUT or HTTP.PATCH...
        MvcResult result = this.mockMvc.perform(post("/albums").content(album_json)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals(album_json, result.getResponse().getContentAsString());
    }

    @Test
    public void testDeleteById() throws Exception {
        MvcResult result = mockMvc.perform(delete("/albums/579df5ecbee8acbe920ab48d")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Assert.assertEquals("", result.getResponse().getContentAsString());
    }
}