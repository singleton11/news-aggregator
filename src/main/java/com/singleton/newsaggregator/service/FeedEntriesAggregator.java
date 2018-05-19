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
public class FeedEntriesAggregator {

    private static Logger logger = LoggerFactory.getLogger(FeedEntriesAggregator.class);

    private RestTemplate restTemplate = new RestTemplate();

    private final FeedEntryService feedEntryService;
    private final ParserFactory parserFactory;

    @Autowired
    public FeedEntriesAggregator(FeedEntryService feedEntryService, ParserFactory parserFactory) {
        this.feedEntryService = feedEntryService;
        this.parserFactory = parserFactory;
    }

    @Async
    @Retryable(backoff = @Backoff(delay = 500))
    public void aggregateEntries(Map<String, String> source) {
        var response = restTemplate.getForObject(source.get("link"), String.class);
        var parser = parserFactory.getParserInstance(response, source);
        feedEntryService.saveEntries(parser.parseForFeedEntries());
    }

    @Recover
    public void recover(Exception e, Map<String, String> source) {
        logger.error(
                "Something goes wrong while grabbing news when getting from " + source.get("link"),
                e
        );
    }
}
