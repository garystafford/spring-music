package org.cloudfoundry.samples.music.config.data;

import org.apache.commons.dbcp2.BasicDataSource;

public class AbstractLocalDataSourceConfig {

    protected BasicDataSource createBasicDataSource(String jdbcUrl, String driverClass, String userName, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
