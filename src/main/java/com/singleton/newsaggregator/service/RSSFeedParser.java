package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.List;

public class RSSFeedParser implements FeedParser {

    private SyndFeed feed;

    @Override
    public List<FeedEntry> parseForFeedEntries() {
        return null;
    }

    public RSSFeedParser(SyndFeed feed) {
        this.feed = feed;
    }

    public SyndFeed getFeed() {
        return feed;
    }

    public void setFeed(SyndFeed feed) {
        this.feed = feed;
    }
}
