package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.util.ParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FeedEntryAggregator {

    private static Logger logger = LoggerFactory.getLogger(FeedEntryAggregator.class);

    private RestTemplate restTemplate = new RestTemplate();

    private final FeedEntryService feedEntryService;

    @Autowired
    public FeedEntryAggregator(FeedEntryService feedEntryService) {
        this.feedEntryService = feedEntryService;
    }

    @Async
    @Retryable(backoff = @Backoff(delay = 500))
    public void aggregateEntry(Map<String, String> source) {
        var response = restTemplate.getForObject(source.get("link"), String.class);
        var parser = ParserFactory.getParserInstance(response, source);
        feedEntryService.saveEntries(parser.parseForFeedEntries());
    }

    @Recover
    public void recover(Exception e, Map<String, String> source) {
        logger.error("Something goes wrong while grabbing news when getting " + source.get("link"),
                     e);
    }
}
