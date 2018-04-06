package kdog.app;

import io.swagger.annotations.Api;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import kdog.controller.ItemController;
import kdog.controller.OrderController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@Api
@ApplicationPath("/api")
public class MyApplication extends Application {
	public MyApplication() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.2");
		beanConfig.setSchemes(new String[]{"http"});
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("javaEE-1.0/api");
		beanConfig.setResourcePackage("kdog");
		beanConfig.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(ItemController.class);
		resources.add(OrderController.class);
	}
}