package com.singleton.newsaggregator.repository;

import com.singleton.newsaggregator.domain.FeedEntity;
import org.springframework.data.repository.CrudRepository;

public interface FeedEntryRepository extends CrudRepository<FeedEntity, Long> {
}
