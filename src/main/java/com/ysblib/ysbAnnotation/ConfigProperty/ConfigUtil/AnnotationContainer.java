package com.ysblib.ysbAnnotation.ConfigProperty.ConfigUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ysblib.ysbAnnotation.ConfigProperty.ConfigProperty;

public class AnnotationContainer {

	public AnnotationContainer(){}
	
	private ConfigLoader configLoader = new ConfigLoader();
	//어노테이션 적용 
	private <T> T invokeAnnotations(T instance) throws IllegalAccessException, IOException, URISyntaxException {
		try {
			Field[] fields = instance.getClass().getDeclaredFields();
			for( Field field : fields) {
				ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
				if( annotation != null && field.getType() == String.class) {
					field.setAccessible(true);
					String annotationValue = configLoader.loadConfigProperty(annotation.value());
					field.set(instance, annotationValue);
					//field.set(instance, annotation.value());
					System.out.println("AnnotationContainer() - invokeAnnotations(T instance) : " + annotation.value());
				}
			}
		} catch (Exception e) {throw e;}
				
		return instance;
	}
	
//	public <T> T get(Class clazz) throws IllegalAccessException, InstantiationException {
//		T instance = (T) clazz.newInstance();
//		instance = invokeAnnotations(instance);
//		
//		return instance;
//	}
	
	public <T> T get(RequestHandler handler) throws IllegalAccessException, InstantiationException, IOException, URISyntaxException {
		T instance = (T) handler;
		try {
			instance = invokeAnnotations(instance);
		} catch (Exception e) {throw e;}	
		
		return instance;
	}
	
}
