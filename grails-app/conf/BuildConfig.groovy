grails.project.work.dir = 'target'

grails.project.dependency.resolver = "maven" // or ivy

grails.project.dependency.resolution = {

	inherits 'global'
	log 'warn'

	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}

	plugins {
		runtime ":cache:1.1.7"

		build ':release:3.0.1', ':rest-client-builder:1.0.3', {
			export = false
		}
	}
}
