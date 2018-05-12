package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.util.ArrayList;
import java.util.List;

public class RSSFeedParser implements FeedParser {

    private SyndFeed feed;

    @Override
    public List<FeedEntry> parseForFeedEntries() {
        var feedEntries = new ArrayList<FeedEntry>();

        for (var entry : (List<SyndEntry>) feed.getEntries()) {
            var feedEntry = new FeedEntry();
            feedEntry.setLink(entry.getLink());
            feedEntry.setTitle(entry.getTitle());
            feedEntry.setBody(entry.getDescription().getValue());

            feedEntries.add(feedEntry);
        }
        return feedEntries;
    }

    public RSSFeedParser(SyndFeed feed) {
        this.feed = feed;
    }
}
