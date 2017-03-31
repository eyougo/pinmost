package com.pinmost.api.web.view;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * User: mei
 * Date: 11/22/13
 * Time: 11:43
 */
public class HtmlEscapeTemplateLoader extends ClassTemplateLoader{
    private static final String HTML_ESCAPE_PREFIX = "<#escape x as x?html>";
    private static final String HTML_ESCAPE_SUFFIX = "</#escape>";

    private final TemplateLoader delegate;

    public HtmlEscapeTemplateLoader(TemplateLoader delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object findTemplateSource(String name) throws IOException {
        return delegate.findTemplateSource(name);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return delegate.getLastModified(templateSource);
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {

        Reader reader = delegate.getReader(templateSource, encoding);
        try{
            String templateText = IOUtils.toString(reader);
            if (StringUtils.startsWithIgnoreCase(templateText, "<#ftl")){
                return new StringReader(templateText);
            }
            return new StringReader(HTML_ESCAPE_PREFIX + templateText + HTML_ESCAPE_SUFFIX);
        }finally {
            IOUtils.closeQuietly(reader);
        }
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {
        delegate.closeTemplateSource(templateSource);
    }

}
