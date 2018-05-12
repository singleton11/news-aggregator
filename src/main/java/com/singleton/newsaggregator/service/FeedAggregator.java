package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.util.ParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Service
public class FeedAggregator {
    private static Logger logger = LoggerFactory.getLogger(FeedAggregator.class);

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
    @Retryable(backoff = @Backoff(delay = 500))
    protected void aggregateEntry(HashMap<String, String> source) {
        var response = restTemplate.getForObject(source.get("link"), String.class);
        var parser = ParserFactory.getParserInstance(response, source);
        feedEntryService.saveEntries(parser.parseForFeedEntries());
    }

    @Recover
    protected void recover(Exception e) {
        logger.error("Something goes wrong while grabbing news", e);
    }
}
