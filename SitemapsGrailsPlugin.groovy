import com.bertramlabs.plugins.sitemaps.GrailsSitemapArtefactHandler

class SitemapsGrailsPlugin {

    def version         = "1.0.0"
    def grailsVersion   = "2.0 > *"
    def title           = "Sitemaps Plugin"
    def author          = "David Estes"
    def authorEmail     = "destes@bcap.com"
    def description     = 'Creates a standard interface for building sitemaps via a common Sitemap Artefact.'
    def documentation   = "http://github.com/bertramdev/grails-sitemaps"
    def license         = "APACHE"
    def organization    = [name: "Bertram Labs", url: "http://www.bertramlabs.com/"]
    def issueManagement = [ system: "GITHUB", url: "http://github.com/bertramdev/grails-sitemaps/issues" ]
    def scm             = [ url: "http://github.com/bertramdev/grails-sitemaps" ]
    def pluginExcludes  = [
        "grails-app/assets/**",
        "test/dummy/**"
    ]
    def developers      = [ [name: 'Brian Wheeler'] ]

    def artefacts = [GrailsSitemapArtefactHandler]

    def watchedResources = [
        "file:./grails-app/sitemaps/**/*Sitemap.groovy",
        "file:../../plugins/*/sitemaps/**/*Sitemap.groovy"
    ]

    def onChange = { event ->
        if (application.isArtefactOfType(GrailsSitemapArtefactHandler.TYPE, event.source)) {
            def oldClass = application.getProductHandlerClass(event.source.name)
            application.addArtefact(GrailsSitemapArtefactHandler.TYPE, event.source)

            // Reload subclasses
            application.sitemapClasses.each {
                if (it.clazz != event.source && oldClass.clazz.isAssignableFrom(it.clazz)) {
                    def newClass = application.classLoader.reloadClass(it.clazz.name)
                    application.addArtefact(GrailsSitemapArtefactHandler.TYPE, newClass)
                }
            }
        }
    }
}
