package com.singleton.newsaggregator.util;

import com.singleton.newsaggregator.service.FeedParser;
import com.singleton.newsaggregator.service.HTMLFeedParser;
import com.singleton.newsaggregator.service.RSSFeedParser;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Map;

@Component
public class ParserFactory {

    private final Map<String, FeedParser> feedParsers;

    @Autowired
    public ParserFactory(Map<String, FeedParser> feedParsers) {
        this.feedParsers = feedParsers;
    }

    public FeedParser getParserInstance(String data, Map<String, String> source) {
        try {
            var feed = new SyndFeedInput().build(new InputSource(new StringReader(data)));
            var feedParser = (RSSFeedParser) feedParsers.get("RSSFeedParser");
            feedParser.setFeed(feed);
            return feedParser;
        } catch (FeedException | IllegalArgumentException e) {
            var document = Jsoup.parse(data);
            var feedParser = (HTMLFeedParser) feedParsers.get("HTMLFeedParser");
            feedParser.setDocument(document);
            feedParser.setSource(source);
            return feedParser;
        }
    }
}
