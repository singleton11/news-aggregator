package com.singleton.newsaggregator.repository;

import com.singleton.newsaggregator.domain.FeedEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface FeedEntryRepository extends CrudRepository<FeedEntry, Long> {
    @Override
    @RestResource(exported = false)
    <S extends FeedEntry> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends FeedEntry> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(FeedEntry feedEntry);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends FeedEntry> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
