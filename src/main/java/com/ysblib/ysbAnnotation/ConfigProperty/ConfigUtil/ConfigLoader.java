package com.ysblib.ysbAnnotation.ConfigProperty.ConfigUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {
	//String projectPath = System.getProperty("user.dir");
	//String propFile = projectPath+"/config/config.properties";

    //Annotation Key String을 매개 변수로 받는다. 
	//Key String에 해당하는 value String을 config 파일에서 읽어와 값을 할당한다. 
	public String loadConfigProperty(String annotationKey) throws IOException, URISyntaxException {
		String annotationValue = null;
		try {
			 Path path = Paths.get(ConfigLoader.class.getResource("/").toURI());
			    String resourceLoc = path + "/config/config.properties";

				Properties prop = new Properties();	
			    // 프로퍼티 파일 스트림에 담기
			    FileInputStream fis = new FileInputStream(resourceLoc);	     
			    // 프로퍼티 파일 로딩 후 변수 리스트에 값 설정 
			    prop.load(new java.io.BufferedInputStream(fis));

			    annotationValue = prop.getProperty(annotationKey);
			    
			    if(annotationValue == null)
			    	throw new RuntimeException("There is no ConfigProperty which is named " + annotationKey + "in config.properties. \n Check Field Annotation in your Lambda Handler.");
		} catch (Exception e) {throw e;}	    
	    //System.out.println("ConfigLoader - prop.getProperty() : " + annotationValue);
	    return annotationValue;
	}
    
}
