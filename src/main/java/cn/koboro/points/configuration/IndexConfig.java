package cn.koboro.points.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 *默认进入页面
 * @author admin
 *
 */
@Configuration
public class IndexConfig extends WebMvcConfigurerAdapter {
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/").setViewName("forward:/index.jsp");
	        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	        super.addViewControllers(registry);
	    }
}
