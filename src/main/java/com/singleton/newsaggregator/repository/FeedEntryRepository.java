package com.singleton.newsaggregator.repository;

import com.singleton.newsaggregator.domain.FeedEntry;
import org.springframework.data.repository.CrudRepository;

public interface FeedEntryRepository extends CrudRepository<FeedEntry, Long> {
}
