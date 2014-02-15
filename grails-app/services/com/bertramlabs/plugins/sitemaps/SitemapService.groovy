package com.bertramlabs.plugins.sitemaps
import grails.spring.BeanBuilder
import grails.plugin.cache.CacheEvict

class SitemapService {
	def grailsApplication

    
	def getSitemaps() {
		def sitemapClasses = grailsApplication.sitemapClasses
		return sitemapClasses.collect { it.sitemap }
	}

	def getAt(String name) {
        println "looking for class ${name} ${grailsApplication.sitemapClasses.collect{ it.sitemap}}"
        def sitemapClass = grailsApplication.sitemapClasses.find { it.sitemap == name }
		if(sitemapClass) {
            println "Found Class"
			instantiateHandler(sitemapClass)	
		} else {
			return null
		}
	}

    @CacheEvict(value='sitemap.show', allEntries=true)
    def evictCache() {
        log.info("Evicting Sitemap Cache")
    }

    protected createBeanBuilder() {
        new BeanBuilder(grailsApplication.mainContext, grailsApplication.classLoader)
    }
    protected instantiateHandler(handlerClass) {
        createBeanBuilder().with {
            beans {
                handler(handlerClass.clazz) {
                    it.autowire = true
                    // it.scope = "singleton"
                }
            }
            createApplicationContext().getBean('handler')
        }
        // grailsApplication.mainContext.getBean('sitemap')
    }

     /**
     * Retrieves the sitemap path from the property [grails.plugin.sitemaps.mapping] which is used by the url mapping and the
     * taglib.  The property cannot contain <code>/</code>, and must be one level deep
     *
     * @return the path
     * @throws IllegalArgumentException if the path contains <code>/</code>
     */
    String getSitemapMapping() {
        def path = grailsApplication.config?.grails?.plugin?.sitemaps?.mapping ?: "sitemap"
        if (path.contains("/")) {
            String message = "the property [grails.plugin.sitemaps.mapping] can only be one level" +
                    "deep.  For example, 'foo' and 'bar' would be acceptable values, but 'foo/bar' is not"
            throw new IllegalArgumentException(message)
        }

        return path
    }
}
