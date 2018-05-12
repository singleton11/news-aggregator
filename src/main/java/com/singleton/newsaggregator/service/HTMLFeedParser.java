package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class HTMLFeedParser implements FeedParser {

    private Document document;

    @Override
    public List<FeedEntry> parseForFeedEntries() {
        var containers = document.select("article");
        var feedEntries = new ArrayList<FeedEntry>();

        for (var container : containers) {
            var title = container.select("a.post__title_link");
            var body = container.select("div.post__text");

            var feedEntry = new FeedEntry();
            feedEntry.setLink(title.attr("href"));
            feedEntry.setTitle(title.text());
            feedEntry.setBody(body.text());

            feedEntries.add(feedEntry);
        }
        return feedEntries;
    }

    public HTMLFeedParser(Document document) {
        this.document = document;
    }
}
