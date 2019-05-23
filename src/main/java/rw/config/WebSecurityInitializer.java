package rw.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by Chebotar_do on 23.05.2019.
 */
@Order(2)
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
