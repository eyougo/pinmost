package com.pinmost.web.interceptor;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.i18n.SimpleTimeZoneAwareLocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.format.datetime.standard.DateTimeContextHolder;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;

/**
 * Created by mei on 08/08/2017.
 */
public class LocaleTimeZoneChangeInterceptor extends LocaleChangeInterceptor{

    public static final String DEFAULT_TIMEZONE_PARAM_NAME = "timezone";

    private String timeZoneParamName = DEFAULT_PARAM_NAME;

    public String getTimeZoneParamName() {
        return timeZoneParamName;
    }

    public void setTimeZoneParamName(String timeZoneParamName) {
        this.timeZoneParamName = timeZoneParamName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        super.preHandle(request, response, handler);
        DateTimeContextHolder.setDateTimeContext(new DateTimeContext());
        String newTimeZone = request.getParameter(getTimeZoneParamName());
        if (newTimeZone != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver == null) {
                throw new IllegalStateException(
                        "No LocaleResolver found: not in a DispatcherServlet request?");
            }

            if (localeResolver instanceof LocaleContextResolver) {
                LocaleContext localeContext = new SimpleTimeZoneAwareLocaleContext(localeResolver.resolveLocale(request), TimeZone.getTimeZone(newTimeZone));
                ((LocaleContextResolver) localeResolver).setLocaleContext(request, response, localeContext);
            }
        }


        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        DateTimeContextHolder.resetDateTimeContext();
        super.afterCompletion(request, response, handler, ex);
    }
}
