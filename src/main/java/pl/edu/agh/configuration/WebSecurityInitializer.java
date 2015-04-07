package pl.edu.agh.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public WebSecurityInitializer() {
        super(WebSecurityConfigurator.class);
    }
}
