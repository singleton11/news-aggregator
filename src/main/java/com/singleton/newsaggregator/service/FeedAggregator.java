package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.util.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Service
public class FeedAggregator {

    private final AggregatorConfig aggregatorConfig;
    private FeedEntryService feedEntryService;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public FeedAggregator(AggregatorConfig aggregatorConfig, FeedEntryService feedEntryService) {
        this.aggregatorConfig = aggregatorConfig;
        this.feedEntryService = feedEntryService;
    }

    @Scheduled(fixedDelay = 300_000, initialDelay = 0)
    public void aggregate() {
        for (var source : aggregatorConfig.getSources()) {
            aggregateEntry(source);
        }
    }

    @Async
    protected void aggregateEntry(HashMap<String, String> source) {
        var response = restTemplate.getForObject(source.get("link"), String.class);
        var parser = ParserFactory.getParserInstance(response, source);
        feedEntryService.saveEntries(parser.parseForFeedEntries());
    }
}
