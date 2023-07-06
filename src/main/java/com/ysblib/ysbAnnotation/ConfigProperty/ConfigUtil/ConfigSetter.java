package com.ysblib.ysbAnnotation.ConfigProperty.ConfigUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ConfigSetter {
	public void setConfigProperties(RequestHandler handler) throws IllegalAccessException, InstantiationException, IOException, URISyntaxException {
		try {
			AnnotationContainer container = new AnnotationContainer();       
			handler = container.get(handler);
		} catch (Exception e) {
			throw e;
		}		
		//System.out.println("ConfigSetter handler hashcode: " + handler.hashCode());
	}
}
