package com.bertramlabs.plugins.sitemaps;

import java.util.List;

import org.codehaus.groovy.grails.commons.GrailsClass;

public interface GrailsSitemapClass extends GrailsClass {
	String getSitemap();

	@SuppressWarnings("rawtypes")
	List getSitemapUrls();

	String getDefaultChangeFrequency();

	Double getDefaultPriority();
}
