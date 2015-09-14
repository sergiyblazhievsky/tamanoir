package com.jaspersoft.tamanoir.jdbc;

import com.jaspersoft.tamanoir.ConnectionException;
import com.jaspersoft.tamanoir.dto.ConnectionDescriptor;
import com.mchange.v2.c3p0.DataSources;
import org.springframework.cache.annotation.Cacheable;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by serhii.blazhyievskyi on 7/21/2015.
 */
public class JdbcDataSource {
    private final static int MAX_POOL_SIZE = 30;
    private final static int MIN_POOL_SIZE = 0;

    @Cacheable(value="connectionDescriptors", key="descriptor")
    public DataSource getInstance(ConnectionDescriptor descriptor) {
        DataSource ds_pooled;
        ConnectionDescriptor currentDescriptor = new ConnectionDescriptor(descriptor);

        DataSource ds_unpooled = null;
        Map<String, Object> overrideProps = new HashMap<String, Object>();

        overrideProps.put("maxPoolSize", MAX_POOL_SIZE);
        overrideProps.put("minPoolSize", MIN_POOL_SIZE);
        overrideProps.put("autoCommitOnClose", true);

        final Properties properties = new Properties();
        final Map<String, String> descriptorProperties = currentDescriptor.getProperties();

        if (descriptorProperties != null) {
            properties.putAll(descriptorProperties);
        }
        try {
            ds_unpooled = DataSources.unpooledDataSource(currentDescriptor.getUrl(), descriptorProperties.get("user"),
                    descriptorProperties.get("password"));
            ds_pooled = DataSources.pooledDataSource(ds_unpooled, overrideProps);
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
        return ds_pooled;
    }

}
