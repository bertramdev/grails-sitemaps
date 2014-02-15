package com.bertramlabs.plugins.sitemaps;

import java.util.List;

import org.codehaus.groovy.grails.commons.AbstractGrailsClass;
import org.codehaus.groovy.grails.commons.GrailsClassUtils;

public class DefaultGrailsSitemapClass extends AbstractGrailsClass implements GrailsSitemapClass {

	public DefaultGrailsSitemapClass(Class<?> clazz) {
		super(clazz, GrailsSitemapArtefactHandler.SUFFIX);
	}

	public String getSitemap() {
		Object sitemapName = GrailsClassUtils.getStaticPropertyValue(getClazz(), "sitemap");
		if(sitemapName == null) {
			return null;
		}
		return sitemapName.toString();
	}

	@SuppressWarnings("rawtypes")
	public List getSitemapUrls() {
		return null;
	}

	public String getDefaultChangeFrequency() {
		return "monthly";
	}

	public Double getDefaultPriority() {
		return 1d;
	}
}
