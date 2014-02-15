package com.bertramlabs.plugins.sitemaps

import grails.plugin.cache.Cacheable

class SitemapController {
	def sitemapService

	def index() {
		def sitemaps = sitemapService.getSitemaps()

		render(contentType: 'application/xml') {
			sitemapindex(xmlns:"http://www.sitemaps.org/schemas/sitemap/0.9") {
				sitemaps.each { sitemapName ->
					sitemap {
						loc(g.createLink(controller: 'sitemap', plugin: 'sitemaps', action: 'show', absolute: true,
						                 params: [id: sitemapName, format: 'xml'], base: request.baseUrl))
						lastmod(new Date())
					}
				}
			}
		}
	}

	@Cacheable('sitemap.show')
	def show() {
		def sitemapClass = sitemapService[params.id]
		if(!sitemapClass) {
			render status: 404
			return
		}

		render(contentType: 'application/xml') {
			urlset(xmlns:"http://www.sitemaps.org/schemas/sitemap/0.9") {
				sitemapClass.sitemapUrls.each { sitemapUrl ->
					url {
						loc(sitemapUrl.url)
						lastmod(sitemapUrl.lastmod ?: new Date())
						changefreq(sitemapUrl.changefreq ?: sitemapClass.defaultChangeFrequency)
						priority(sitemapUrl.priority ?: sitemapClass.defaultPriority)
					}
				}
			}
		}
	}
}
