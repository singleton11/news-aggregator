package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;

import java.util.List;

public interface FeedParser {
    List<FeedEntry> parseForFeedEntries();
}
