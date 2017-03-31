package com.pinmost.api.web.view;

import freemarker.cache.TemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.List;

/**
 * User: mei
 * Date: 11/22/13
 * Time: 12:18
 */
public class HtmlEscapeFreeMarkerConfigurer extends FreeMarkerConfigurer {
    @Override
    protected TemplateLoader getAggregateTemplateLoader(List<TemplateLoader> templateLoaders) {
        return new HtmlEscapeTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));
    }
}
