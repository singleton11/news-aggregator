package com.singleton.newsaggregator.util;

import com.singleton.newsaggregator.service.FeedParser;
import com.singleton.newsaggregator.service.HTMLFeedParser;
import com.singleton.newsaggregator.service.RSSFeedParser;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.jsoup.Jsoup;
import org.xml.sax.InputSource;

import java.io.StringReader;

public class ParserFactory {
    public static FeedParser getParserInstance(String data) {
        try {
            var feed = new SyndFeedInput().build(new InputSource(new StringReader(data)));
            return new RSSFeedParser(feed);
        } catch (FeedException | IllegalArgumentException e) {
            var document = Jsoup.parse(data);
            return new HTMLFeedParser(document);
        }
    }
}
