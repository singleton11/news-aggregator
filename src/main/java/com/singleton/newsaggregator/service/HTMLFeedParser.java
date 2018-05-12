package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HTMLFeedParser implements FeedParser {

    private Document document;
    private Map<String, String> source;

    private final static String CONTAINER_KEY = "container";
    private final static String TITLE_KEY = "titleLink";
    private final static String BODY_KEY = "body";

    private final static String LINK_ATTRIBUTE_NAME = "href";

    @Override
    public List<FeedEntry> parseForFeedEntries() {
        var containers = document.select(source.get(CONTAINER_KEY));
        var feedEntries = new ArrayList<FeedEntry>();

        for (var container : containers) {
            var title = container.select(source.get(TITLE_KEY));
            var body = container.select(source.get(BODY_KEY));

            var feedEntry = new FeedEntry();
            feedEntry.setLink(title.attr(LINK_ATTRIBUTE_NAME));
            feedEntry.setTitle(title.text());
            feedEntry.setBody(body.text());

            feedEntries.add(feedEntry);
        }
        return feedEntries;
    }

    public HTMLFeedParser(Document document, Map<String, String> source) {
        this.document = document;
        this.source = source;
    }
}
