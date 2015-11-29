package com.jobs.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/** 
*
* https://github.com/jmchung/swagger4javaee.git
* 
* @author Jeremy Chung
*/
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();

        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.jobs.controller.JobsController.class);
    }

}
