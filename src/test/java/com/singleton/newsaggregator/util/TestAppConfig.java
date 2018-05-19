package com.singleton.newsaggregator.util;

import com.singleton.newsaggregator.service.HTMLFeedParser;
import com.singleton.newsaggregator.service.RSSFeedParser;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(
        basePackageClasses = {ParserFactory.class, RSSFeedParser.class, HTMLFeedParser.class},
        lazyInit = true
)
class TestAppConfig {
}
