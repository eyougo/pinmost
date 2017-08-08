package com.pinmost.web.controller;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;

/**
 * @author mei
 *
 */
public abstract class BaseController {
    public static final String AUTH_COOKIE_TOKEN = "pinmost_token";
    public static final String COOKIE_DOMAIN = ".pinmost.com";
    public static final String ACCOUNT_SESSION_KEY = "account";

	protected MessageSource messageSource;

	@Autowired
	@Required
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

    public TemplateHashModel getStaticModel(Class clazz){
        TemplateHashModel staticModels = BeansWrapper.getDefaultInstance().getStaticModels();
        TemplateHashModel staticModel = null;
        try {
            staticModel = (TemplateHashModel)staticModels.get(clazz.getName());
        } catch (TemplateModelException e) {

        }
        return staticModel;
    }

    public TemplateHashModel getEnumModel(Class clazz){
        TemplateHashModel enumModels = BeansWrapper.getDefaultInstance().getEnumModels();
        TemplateHashModel enumModel = null;
        try {
            enumModel = (TemplateHashModel)enumModels.get(clazz.getName());
        } catch (TemplateModelException e) {

        }
        return enumModel;
    }
}
