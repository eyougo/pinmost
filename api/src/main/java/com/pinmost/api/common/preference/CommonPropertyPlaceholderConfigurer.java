package com.pinmost.api.common.preference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

public class CommonPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {
    private static Log LOG = LogFactory.getLog(CommonPropertyPlaceholderConfigurer.class);

    public CommonPropertyPlaceholderConfigurer() {
        super();
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props) {
        String value = PreferenceService.getCommonConfiguration()
                .getString(placeholder);
        if (value == null) {
            return super.resolvePlaceholder(placeholder, props);
        } else
            return value;
    }
}
