import com.bertramlabs.plugins.sitemaps.GrailsSitemapArtefactHandler

class SitemapsGrailsPlugin {

    def version = "0.1.0"
    def grailsVersion = "2.0 > *"
    def title = "Sitemaps Plugin" // Headline display name of the plugin
    def author          = "David Estes"
    def authorEmail     = "destes@bcap.com"
    def description     = 'TODO: IMPLEMENT'
    def documentation   = "http://bertramdev.github.io/asset-pipeline"
    def license         = "APACHE"
    def organization    = [ name: "Bertram Capital", url: "http://www.bertramcapital.com/" ]
    def issueManagement = [ system: "GITHUB", url: "http://github.com/bertramdev/grails-sitemaps/issues" ]
    def scm             = [ url: "http://github.com/bertramdev/grails-sitemaps" ]
    def pluginExcludes  = [
        "grails-app/assets/**",
        "test/dummy/**"
    ]
    def developers      = [ [name: 'Brian Wheeler'] ]
    // register the artefact handler
    def artefacts = [GrailsSitemapArtefactHandler]
     
    // watch for any changes in these directories
    def watchedResources = [
        "file:./grails-app/sitemaps/**/*Sitemap.groovy",
        "file:../../plugins/*/sitemaps/**/*Sitemap.groovy"
    ]

    def onChange = { event ->
        if (application.isArtefactOfType(GrailsSitemapArtefactHandler.TYPE, event.source)) {
            def oldClass = application.getProductHandlerClass(event.source.name)
            application.addArtefact(GrailsSitemapArtefactHandler.TYPE, event.source)
 
            // Reload subclasses
            application.productHandlerClasses.each {
                if (it.clazz != event.source && oldClass.clazz.isAssignableFrom(it.clazz)) {
                    def newClass = application.classLoader.reloadClass(it.clazz.name)
                    application.addArtefact(GrailsSitemapArtefactHandler.TYPE, newClass)
                }
            }
        }
    }
}
