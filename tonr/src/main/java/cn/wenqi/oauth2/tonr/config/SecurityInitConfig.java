package cn.wenqi.oauth2.tonr.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.ServletContext;

/**
 * @author wenqi
 */

public class SecurityInitConfig extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        super.beforeSpringSecurityFilterChain(servletContext);
        insertFilters(servletContext,new RequestContextFilter());
    }

    public SecurityInitConfig(){
        super();
    }
}
