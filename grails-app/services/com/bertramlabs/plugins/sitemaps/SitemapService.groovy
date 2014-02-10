package com.bertramlabs.plugins.sitemaps
import grails.spring.BeanBuilder

class SitemapService {
	def grailsApplication

    
	def getSitemaps() {
		def sitemapClasses = grailsApplication.sitemapClasses
		return sitemapClasses.collect { it.sitemap }
	}

	def getAt(String name) {
        def sitemapClass = grailsApplication.sitemapClasses.find { it.sitemap == name } // find the one with the matching key
		if(sitemapClass) {
			instantiateHandler(sitemapClass)	
		} else {
			return null
		}
	}


    protected instantiateHandler(handlerClass) {
        createBeanBuilder().with {
            beans {
                handler(handlerClass) {
                    // autowire the handler so it can use services etc.
                    it.autowire = true
                }
            }
            createApplicationContext().getBean('handler')
        }
    }
     /**
     * Retrieves the sitemap path from the property [grails.plugin.sitemaps.mapping] which is used by the url mapping and the
     * taglib.  The property cannot contain <code>/</code>, and must be one level deep
     *
     * @return the path
     * @throws IllegalArgumentException if the path contains <code>/</code>
     */
    String getAssetMapping() {
        def path = grailsApplication.config?.grails?.plugin?.sitemaps?.mapping ?: "sitemap"
        if (path.contains("/")) {
            String message = "the property [grails.plugin.sitemaps.mapping] can only be one level" +
                    "deep.  For example, 'foo' and 'bar' would be acceptable values, but 'foo/bar' is not"
            throw new IllegalArgumentException(message)
        }

        return path
    }
}
