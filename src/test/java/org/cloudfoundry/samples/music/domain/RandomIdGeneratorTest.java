package org.cloudfoundry.samples.music.domain;

import org.hibernate.engine.spi.SessionImplementor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.Serializable;
import java.util.UUID;

public class RandomIdGeneratorTest {

    // UUID version 4 = Randomly generated UUID
    public static final int RANDOMLY_GENERATED_UUID_VERSION = 4;

    @Mock
    private SessionImplementor session;

    @Mock
    private Object object;

    private RandomIdGenerator randomIdGenerator;


    @Before
    public void setUp() throws Exception {
        randomIdGenerator = new RandomIdGenerator();
    }

    @Test
    public void testGenerate() throws Exception {
        Serializable serializable = randomIdGenerator.generate(session, object);
        String generatedId = serializable.toString();

        // does method return a valid, randomly generated UUID
        Assert.assertEquals(RANDOMLY_GENERATED_UUID_VERSION,
                UUID.fromString(generatedId).version());
    }

    @Test
    public void testGenerateId() throws Exception {
        String generatedId = randomIdGenerator.generateId();

        // does method return a valid, randomly generated UUID
        Assert.assertEquals(RANDOMLY_GENERATED_UUID_VERSION,
                UUID.fromString(generatedId).version());
    }
}