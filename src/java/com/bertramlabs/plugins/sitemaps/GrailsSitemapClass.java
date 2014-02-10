package com.bertramlabs.plugins.sitemaps;
import org.codehaus.groovy.grails.commons.InjectableGrailsClass;
import java.util.Map;
import java.util.List;

public interface GrailsSitemapClass extends InjectableGrailsClass {
	public String getSitemapName();

	public List getSitemapUrls();

	public String getDefaultChangeFrequency();

	public Double getDefaultPriority();
}

