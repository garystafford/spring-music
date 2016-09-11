package org.cloudfoundry.samples.music.domain;

import org.hibernate.engine.spi.SessionImplementor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.Serializable;
import java.util.UUID;

public class AlbumTest {
    private String title, artist, releaseYear, genre, albumId;
    private int trackCount;
    private Album getAlbum, setAlbum;

    // UUID version 4 = Randomly generated UUID
    public static final int RANDOMLY_GENERATED_UUID_VERSION = 4;

    @Mock
    private SessionImplementor session;

    @Mock
    private Object object;

    private RandomIdGenerator randomIdGenerator;

    @Before
    public void setUp() throws Exception {
        title = "title_test";
        artist = "artist_title";
        releaseYear = "2000";
        genre = "test_genre";
        albumId = "test_albumId";
        trackCount = 10;

        randomIdGenerator = new RandomIdGenerator();
        Serializable serializable = randomIdGenerator.generate(session, object);

        getAlbum = new Album(title, artist, releaseYear, genre);
        getAlbum.setId(serializable.toString());
        getAlbum.setTrackCount(trackCount);
        getAlbum.setAlbumId(albumId);

        setAlbum = new Album();
    }

    @Test
    public void testGetId() throws Exception {
        // does method return a valid, randomly generated UUID
        Assert.assertEquals(RANDOMLY_GENERATED_UUID_VERSION,
                UUID.fromString(getAlbum.getId()).version());
    }

    @Test
    public void testGetTitle() throws Exception {
        Assert.assertEquals(title, getAlbum.getTitle());
    }

    @Test
    public void testSetTitle() throws Exception {
        setAlbum.setTitle(title);

        Assert.assertEquals(title, setAlbum.getTitle());
    }

    @Test
    public void testGetArtist() throws Exception {
        Assert.assertEquals(artist, getAlbum.getArtist());
    }

    @Test
    public void testSetArtist() throws Exception {
        setAlbum.setArtist(artist);

        Assert.assertEquals(artist, setAlbum.getArtist());
    }

    @Test
    public void testGetReleaseYear() throws Exception {
        Assert.assertEquals(releaseYear, getAlbum.getReleaseYear());
    }

    @Test
    public void testSetReleaseYear() throws Exception {
        setAlbum.setReleaseYear(releaseYear);

        Assert.assertEquals(releaseYear, setAlbum.getReleaseYear());
    }

    @Test
    public void testGetGenre() throws Exception {
        Assert.assertEquals(genre, getAlbum.getGenre());
    }

    @Test
    public void testSetGenre() throws Exception {
        setAlbum.setGenre(genre);

        Assert.assertEquals(genre, setAlbum.getGenre());
    }

    @Test
    public void testGetTrackCount() throws Exception {
        Assert.assertEquals(trackCount, getAlbum.getTrackCount());
    }

    @Test
    public void testSetTrackCount() throws Exception {
        setAlbum.setTrackCount(trackCount);

        Assert.assertEquals(trackCount, setAlbum.getTrackCount());
    }

    @Test
    public void testGetAlbumId() throws Exception {
        Assert.assertEquals(albumId, getAlbum.getAlbumId());
    }

    @Test
    public void testSetAlbumId() throws Exception {
        setAlbum.setAlbumId(albumId);

        Assert.assertEquals(albumId, setAlbum.getAlbumId());
    }
}