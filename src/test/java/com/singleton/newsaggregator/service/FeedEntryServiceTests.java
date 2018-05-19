package com.singleton.newsaggregator.service;

import com.singleton.newsaggregator.domain.FeedEntry;
import com.singleton.newsaggregator.repository.FeedEntryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.password=test"})
@ContextConfiguration(classes = TestAppConfig.class)
public class FeedEntryServiceTests {
    @Autowired
    private FeedEntryService feedEntryService;

    @Autowired
    private FeedEntryRepository feedEntryRepository;

    @Test
    public void savingFeedEntriesTwiceWontRepeatedInDB() {
        var entries = new LinkedList<FeedEntry>();

        var entry = new FeedEntry();
        entry.setTitle("Hello");
        entry.setBody("World");
        entry.setLink("http://google.com");

        entries.push(entry);

        feedEntryService.saveEntries(entries);
        feedEntryService.saveEntries(entries);

        assertEquals(feedEntryRepository.count(), 1);
    }
}
