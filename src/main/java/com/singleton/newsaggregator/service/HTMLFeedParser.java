package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import org.jsoup.nodes.Document;

import java.util.List;

public class HTMLFeedParser implements FeedParser {

    private Document document;

    @Override
    public List<FeedEntry> parseForFeedEntries() {
        return null;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public HTMLFeedParser(Document document) {

        this.document = document;
    }
}
