package com.singleton.newsaggregator.services;

public interface SourceTypeDeterminator {
    /**
     * Determine type of the feed (HTML or RSS/Atom)
     *
     * @param data string content
     * @return true if type of data is HTML, false otherwise (RSS, Atom)
     */
    boolean determine(String data);
}
