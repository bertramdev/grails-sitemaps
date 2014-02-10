import org.springframework.context.ApplicationContext

class SitemapUrlMappings {

	static mappings = {ApplicationContext context ->
		def path = context?.sitemapService?.sitemapMapping ?: 'sitemap'
		"/$path.xml" (controller: 'sitemap', plugin: 'sitemaps', action: 'index')
		"/$path/$id**" (
			controller: 'sitemap',
			plugin: 'sitemaps',
			action: 'show'
		)
	}
}
