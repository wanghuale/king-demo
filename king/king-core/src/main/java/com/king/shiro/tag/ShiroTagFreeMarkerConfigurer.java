package com.king.shiro.tag;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.core.io.ResourceLoader;
import freemarker.template.TemplateException;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }

}
