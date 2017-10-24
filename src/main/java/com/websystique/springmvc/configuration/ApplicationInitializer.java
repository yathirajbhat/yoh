package com.websystique.springmvc.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.websystique.springmvc.controller.RegistrationController;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfiguration.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

    @Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    	registration.setMultipartConfig(getMultipartConfigElement());
	}

    private MultipartConfigElement getMultipartConfigElement(){
    	logger.info("Initializer -- getMultipartConfigElement ");
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}
    
    /*Set these variables for your project needs*/ 
    
	public static final String LOCATION = "/media/";
   // public static final String LOCATION = "D:\\resource\\";

	private static final long MAX_FILE_SIZE = 1024 * 1024 * 25;//25MB
	
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;//30MB

    private static final int FILE_SIZE_THRESHOLD = 0;
}
