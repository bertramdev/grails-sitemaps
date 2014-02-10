package com.bertramlabs.plugins.sitemaps;
import org.codehaus.groovy.grails.commons.GrailsClass;

import java.util.Map;
import java.util.List;

public interface GrailsSitemapClass extends GrailsClass {
	public String getSitemapName();

	public List getSitemapUrls();

	public String getDefaultChangeFrequency();

	public Double getDefaultPriority();
}

