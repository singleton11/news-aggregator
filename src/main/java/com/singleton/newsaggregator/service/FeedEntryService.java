package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import com.singleton.newsaggregator.repository.FeedEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedEntryService {
    private static Logger logger = LoggerFactory.getLogger(FeedEntryService.class);

    private FeedEntryRepository feedEntryRepository;

    @Autowired
    public FeedEntryService(FeedEntryRepository feedEntryRepository) {
        this.feedEntryRepository = feedEntryRepository;
    }

    void saveEntries(List<FeedEntry> feedEntries) {
        for (var feedEntry : feedEntries) {
            logger.info("Saving " + feedEntry.getTitle());
            try {
                feedEntryRepository.save(feedEntry);
            } catch (DataIntegrityViolationException e) {
                logger.warn("Record already exists");
            }
        }
    }
}
