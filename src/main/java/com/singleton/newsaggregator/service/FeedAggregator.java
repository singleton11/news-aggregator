package com.singleton.newsaggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class FeedAggregator {

    private final AggregatorConfig aggregatorConfig;

    private FeedEntryAggregator feedEntryAggregator;

    @Autowired
    public FeedAggregator(AggregatorConfig aggregatorConfig,
                          FeedEntryAggregator feedEntryAggregator) {
        this.aggregatorConfig = aggregatorConfig;
        this.feedEntryAggregator = feedEntryAggregator;
    }

    @Scheduled(fixedDelay = 300_000, initialDelay = 0)
    public void aggregate() {
        for (var source : aggregatorConfig.getSources()) {
            feedEntryAggregator.aggregateEntry(source);
        }
    }
}
