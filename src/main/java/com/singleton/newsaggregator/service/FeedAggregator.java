package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.util.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FeedAggregator {

    private final AggregatorConfig aggregatorConfig;
    private FeedEntryService feedEntryService;

    @Autowired
    public FeedAggregator(AggregatorConfig aggregatorConfig, FeedEntryService feedEntryService) {
        this.aggregatorConfig = aggregatorConfig;
        this.feedEntryService = feedEntryService;
    }

    public void aggregate() {
        for (var source : aggregatorConfig.getSources()) {
            var restTemplate = new RestTemplate();
            var response = restTemplate.getForObject(source.get("link"), String.class);
            var parser = ParserFactory.getParserInstance(response, source);
            feedEntryService.saveEntries(parser.parseForFeedEntries());
        }
    }
}
