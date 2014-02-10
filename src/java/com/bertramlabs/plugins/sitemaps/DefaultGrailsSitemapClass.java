package com.bertramlabs.plugins.sitemaps;

import org.codehaus.groovy.grails.commons.InjectableGrailsClass;
import java.util.Map;
import java.util.List;
import org.codehaus.groovy.grails.commons.AbstractGrailsClass;
import org.codehaus.groovy.grails.commons.GrailsClassUtils;

public interface DefaultGrailsSitemapClass extends AbstractGrailsClass implements GrailsSitemapClass {

	public DefaultGrailsSitemapClass(Class clazz) {
        super(clazz, GrailsSitemapArtefactHandler.SUFFIX);
    }

	public String getSitemapName() {
        Object sitemapName = GrailsClassUtils.getStaticPropertyValue(getClazz(), "sitemap");
		if(sitemapName == null) {
			return null;
		} else {
			return sitemapName.toString();
		}
	}

	public List getSitemapUrls() {
		return null;
	}

	public String getDefaultChangeFrequency() {
		return "monthly";
	}

	public Double getDefaultPriority() {
		return new Double(1.0);
	}
}

