package com.bertramlabs.plugins.sitemaps;

import org.codehaus.groovy.grails.commons.ArtefactHandlerAdapter;
 
public class GrailsSitemapArtefactHandler extends ArtefactHandlerAdapter {
 
    // the name for these artefacts in the application
    static public final String TYPE = "Sitemap"; 
 
    // the suffix of all product handler classes (i.e. how they are identified as product handlers)
    static public final String SUFFIX = "Sitemap"; 
     
    public GrailsSitemapArtefactHandler() {
        super(TYPE, GrailsSitemapClass.class, DefaultGrailsSitemapClass.class, SUFFIX);
    }
}