@artifact.package@class @artifact.name@ {
    static sitemap = "mysitemap"

    List getSitemapUrls() {
        return [
            [url: 'http://mysite.com/page', lastmod: new Date()],
            [url: 'http://mysite.com/page2', lastmod: new Date(), priority: 0.1]
            [url: 'http://mysite.com/page3', lastmod: new Date(), changefreq: 'weekly']
        ]
    }

    String getDefaultChangeFrequency() {
        return "monthly"
    }

    Double getDefaultPriority() {
        return 1.0
    }
}