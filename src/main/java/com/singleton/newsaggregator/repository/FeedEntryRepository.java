package com.singleton.newsaggregator.repository;

import com.singleton.newsaggregator.domain.FeedEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface FeedEntryRepository extends PagingAndSortingRepository<FeedEntry, Long> {
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

    @Query("SELECT e FROM FeedEntry e WHERE e.title LIKE %:title%")
    Page<FeedEntry> searchByTitle(@Param("title") String title, Pageable page);
}
