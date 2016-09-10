package org.cloudfoundry.samples.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationInfoTest {

    private String[] profiles;
    private String[] services;
    private ApplicationInfo applicationInfo;

    @Before
    public void setUp() throws Exception {
        profiles = new String[]{"test_profile_1", "test_profile_2"};
        services = new String[]{"test_service_1", "test_service_2"};
        applicationInfo = new ApplicationInfo(profiles, services);
    }

    @Test
    public void testGetProfiles() throws Exception {
        Assert.assertArrayEquals(profiles, applicationInfo.getProfiles());
    }

    @Test
    public void testGetServices() throws Exception {
        Assert.assertArrayEquals(services, applicationInfo.getServices());
    }
}