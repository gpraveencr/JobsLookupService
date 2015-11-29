package com.jobs.rest;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * How can I configure a Swagger in Glassfish 4 without a web.xml?
 * http://stackoverflow.com/questions/19664888/how-can-i-configure-a-swagger-in-glassfish-4-without-a-web-xml
 *
 * @author lucasweble
*
* https://github.com/jmchung/swagger4javaee.git
* 
* @author Jeremy Chung
*/
//@WebServlet(name = "SwaggerJaxrsConfig", loadOnStartup = 1)
public class SwaggerJaxrsConfig extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
            SwaggerConfig swaggerConfig = new SwaggerConfig();
            ConfigFactory.setConfig(swaggerConfig);
            swaggerConfig.setBasePath("http://localhost:8080/JobsLookupService/rest");
            swaggerConfig.setApiVersion("1.0.0");
            ScannerFactory.setScanner(new DefaultJaxrsScanner());
            ClassReaders.setReader(new DefaultJaxrsApiReader());
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
    }
}
